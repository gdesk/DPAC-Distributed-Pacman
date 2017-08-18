package client.communication.model

import java.awt.Image
import java.awt.image.BufferedImage
import java.io._
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import akka.actor.{ActorSystem, Inbox, Props}
import client.communication.model.actor.{FromServerCommunication, P2PCommunication, ToServerCommunication}
import client.model._
import client.model.character.{BaseGhost, BasePacman}
import client.model.utils.BaseEatObjectStrategy
import client.utils.IOUtils
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.commons.lang3.StringEscapeUtils

import scala.concurrent.duration.Duration
import scala.util.parsing.json.JSONObject


/**
  * This class is the model of communication, used to controller. It manages the interaction with the server,
  * through the actor paradigm.
  *
  * @author Giulia Lucchi
  */

case class ToClientCommunicationImpl() extends ToClientCommunication{
  println("-- Client configuration --")
  println()

  private val player: Player = PlayerImpl
  private val config: Config = ConfigFactory.parseString(
    " akka { \n" +
      " actor { \n" +
      " provider = remote\n" +
      "}\n" +
      " remote { \n" +
      " enabled-transports = [\"akka.remote.netty.tcp\"]\n" +
      " netty.tcp { \n" +
      " hostname = \"" + player.ip +"\"\n" +
      " port = 2554\n" +
      "}\n" +
      "}\n" +
      "}\n")
  //ConfigFactory.parseFile(new File("src/main/resources/communication/configuration.conf"))
  private val system: ActorSystem = ActorSystem.create("DpacClient", config)
  private val inbox = Inbox.create(system)

  println()
  println("-- Actors Creation --")
  println()
  private val toServerCommunication = system.actorOf(Props[ToServerCommunication], "toServerCommunication")
  private val fromServerCommunication = system.actorOf(Props[FromServerCommunication], "fromServerCommunication")
  private val P2PCommunication = system actorOf(Props[P2PCommunication], "P2PCommunication")

  private val currentMatch: Match = MatchImpl

  /**
    * Send the message to actor AccessManager with the registration's data and
    * receive from server the response.
    *
    * @param name
    * @param username
    * @param email
    * @param password
    * @param confirmPassword
    * @return true  if registration ended good
    *         false otherwise
    */
  override def registration(name: String, username: String, email: String, password: String, confirmPassword: String): Boolean = {
    if (!(password equals(confirmPassword))){
      println("PASSWORD SBAGLIATA.")
      return false
    }
    val message = JSONObject(Map[String, String](
      "object" -> "newUser",
      "senderIP" -> player.ip,
      "name" -> name,
      "username" -> username,
      "email" -> email,
      "password" -> getSHA1(password)
    ))

    player.username = username
    val response= getJSONMessage(message)
    response.obj("result").asInstanceOf[Boolean]
  }

  /**
    * Send the message to actor ToServerCommunication with the login's data and
    * receive from sever the response with also the MatchResult
    *
    * @param username
    * @param password
    * @return true  if login ended good
    *         false otherwise
    */
  override def login(username: String, password: String): Boolean = {
    val message = JSONObject(Map[String, String](
      "object" -> "login",
      "senderIP" -> player.ip,
      "username" -> username,
      "password" -> getSHA1(password)
    ))

    val response = getJSONMessage(message)
    val list = response.obj("list").asInstanceOf[Option[List[Map[String, Any]]]]
    if (list.isEmpty) return false
    val allMatches = wrapperAllMatches(list.get)
    player.username = username
    player.allMatchesResults = allMatches
    true
  }

  /**
    * Send to server the username to remove the user from online users' list.
    *
    *@return true  if logout ended good
    *        false otherwise
    */
  override def logout(): Boolean = {
    val message = JSONObject(Map[String, String](
      "object" -> "logout",
      "senderIP" -> player.ip,
      "username" -> player.username
    ))

    val response = getJSONMessage(message)
    response.obj("response").asInstanceOf[Boolean]
  }

  /**
    * Receives to server the list of range to play the match.
    *
    * @return list of range to players' game
    */
  override def getRanges: List[Range] = {
    val message = JSONObject(Map[String, String](
      "object" -> "rangesRequest",
      "senderIP" ->player.ip
    ))

    val response = getJSONMessage(message)
    response.obj("list").asInstanceOf[List[Range]]
  }

  /**
    * Send to server the selected range
    *
    * @param range the range selected by player
    */
  override def selectRange(range: Range): Unit = {
    val message = JSONObject(Map[String,Any](
      "object" -> "selectedRange",
      "senderIP" -> player.ip,
      "range" -> range
    ))

    toServerCommunication ! message
  }

  /**
    * Receives from server the available character.
    *
    * @return list of all character to choose in team's creation.
    */
  override def getCharactersToChoose: Map[String, Image] = {
    val message = JSONObject(Map[String, String](
      "object" -> "characterToChooseRequest",
      "senderIP" -> player.ip
    ))

    val response = getJSONMessage(message)
    val fileMap = response.obj("map").asInstanceOf[Map[String, Array[Byte]]]
    var characterToChoose: Map[String, Image]= Map.empty
    fileMap.keySet.foreach(name =>{
      val path = "src/main/resources/characteTrs/selection/"+name+".png"
      saveImageToResources(path, fileMap(name))
      characterToChoose += ((name, new ImageIcon(path).getImage))
    })

    characterToChoose
  }

  /**
    * Send to server the character chosen. It's recall when the player choose him character.
    * The images .png are saved in the resources directory.
    *
    * @param character character chosen from single player
    * @return true  if character has been already chosen
    *         false otherwise
    */
  override def chooseCharacter(character: String): Boolean = {
    val message = JSONObject(Map[String, String](
      "object" -> "chooseCharacter",
      "senderIP" -> player.ip,
      "character" -> character))

    val response = getJSONMessage(message)
    val isAvailable = response.obj("available").asInstanceOf[Boolean]
    if (isAvailable) {
      val images = response.obj("map").asInstanceOf[Map[String,Array[Byte]]]
      images.keySet.foreach(path =>{
        saveImageToResources(path, images(path))
      })
    }
    isAvailable
  }

  /**
    * Receives from server the List of available playgrounds, saving to resources directory
    * the image.
    *
    * @return number of available playground
    */
  override def getPlaygrounds: Int = {
    val message = JSONObject(Map[String, String](
      "object" -> "playgrounds",
      "senderIP" -> player.ip))

    val response = getJSONMessage(message)
    val map = response.obj("list").asInstanceOf[Map[Int, Array[Byte]]]
    map.keySet.foreach(id =>{
      saveImageToResources("src/main/resources/playground/images/"+ id.toString+".png", map(id))
    })
    map.size
  }

  /**
    * Send to server the playground chosen. It's recall when the player choose the playground of current match.
    *
    * @param idPlayground position of playground's in the file list.
    *
    */
  override def choosePlayground(idPlayground: Int): Unit = {
    val message = JSONObject(Map[String, Any](
      "object" -> "chosenPlayground",
      "senderIP" -> player.ip,
      "playground" -> idPlayground
    ))

    val response = getJSONMessage(message)
    val playgroundFile = response.obj("playground").asInstanceOf[Array[Byte]]

    //val inputStream: InputStream = new ByteArrayInputStream(playgroundFile)
    val fos = new FileOutputStream("src/main/resources/playground/playgroundMatch.txt")
    fos.write(playgroundFile)
    fos.close()
    val playground = IOUtils.getPlaygroundFromPath("playgroundMatch")
    currentMatch.playground_=(playground)
  }

  /**
    * Send to server the match just ended.
    *
    * @param result The MatchResult with date and score of the ended match
    * @param user   id of characters.
    */
  override def matchResult(result: MatchResult, user: String): Unit = {
    val message = JSONObject(Map[String, Any](
      "object" -> "matchResult",
      "senderIP" -> player.ip,
      "date" -> result.date,
      "score" -> result.score,
      "result"-> result.result,
      "user" -> user
    ))
    toServerCommunication ! message.asInstanceOf[JSONObject]
  }

  /**
    * Receives from server all the played matches of selected username
    *
    * @param username username of player
    * @return list of all match with its result
    */
  override def getAllMatchesResults(username: String): List[MatchResult] = {
    val message = JSONObject(Map[String, String](
      "object" -> "allMatchResult",
      "senderIP" -> player.ip,
      "username" -> username
    ))
    val response = getJSONMessage(message)
    val list = response.obj("list").asInstanceOf[List[Map[String, Any]]]
    val allMatches = wrapperAllMatches(list)
    allMatches
  }

  def initializedCharatcter(): Unit ={
    val message = JSONObject(Map[String, String](
      "object" -> "initCharacter",
      "senderIP" -> player.ip
    ))

    val response = getJSONMessage(message)
    val typeCharacters = response.obj("typeCharacter").asInstanceOf[Map[String, Array[String]]]
    typeCharacters.keySet.foreach(x =>{
      val singleCharacter = typeCharacters(x)
      singleCharacter(0) match {
        case "pacman" => currentMatch.addCharactersAndPlayersIp(BasePacman(singleCharacter(1), BaseEatObjectStrategy()), x)
        case "ghost" => currentMatch.addCharactersAndPlayersIp(BaseGhost(singleCharacter(1)), x)
      }
    })
  }

  /**
    * Receives from server the characters playing in the current match
    *
    * @return list of current match's characters.
    *         The Map has the IP address of character as key and, as value, a Map with direction and Image.
    */
  override def getTeamCharacter(name: String):  Map[Direction, Image] = {
    val message = JSONObject(Map[String, String](
      "object" -> "teamCharacterRequest",
      "senderIP" -> player.ip,
      "requestIP" -> name,
      "resolution" -> "24x24"
    ))

    val response = getJSONMessage(message)

    val ImageDirectionMap = response.obj("map").asInstanceOf[Map[String, Map[String, Array[Byte]]]]
    var mapToReturn : scala.collection.mutable.Map[String, Map[Direction, Image]] = scala.collection.mutable.Map.empty
    var current: String= ""
    ImageDirectionMap.keySet.foreach(character=>{
      current = character
      mapToReturn += (character->Map.empty)
      val map = ImageDirectionMap(character)
      map.keySet.foreach(path=>{
        val s: Array[String]  = path.split(StringEscapeUtils.escapeJava("\\"))
        var direction = s(s.size-1)
        direction = direction.substring(0,direction.size-4)
        direction match{
          case "left" => mapToReturn(character) += (Direction.LEFT -> new ImageIcon(map(path)).getImage)
          case "right" => mapToReturn(character) += (Direction.RIGHT -> new ImageIcon(map(path)).getImage)
          case "up" => mapToReturn(character) += (Direction.UP -> new ImageIcon(map(path)).getImage)
          case "down" => mapToReturn(character) += (Direction.DOWN -> new ImageIcon(map(path)).getImage)
        }
      })
    })
    mapToReturn(current)
  }

  /**
    * Send to server the request to information to configure and synchronize the P2P Communication.
    * Then start the game
    *
    **/
  override def startMatch(): Unit = {
    val message = JSONObject(Map[String,String](
      "object" -> "startGame",
      "senderIP" -> player.ip
    ))
    P2PCommunication ! message.asInstanceOf[JSONObject]
  }

  /**
    * Send to server the request to invite the friend in current match
    *
    * @param username username of player to invite
    * @return boolean the invite's response
    */
  override def sendRequest(username: String): Unit = {
    val message = JSONObject(Map[String,String](
      "object" -> "addFriend",
      "senderIP" -> player.ip,
      "senderUsername" -> player.username,
      "username" -> username
    ))
    toServerCommunication ! message
  }

  /**
    * Send to server the invite's response
    *
    * @param response the invite's response to current match
    */
  override def sendResponse(response: Boolean): Unit = {
    val message = JSONObject(Map[String,Any](
      "object" -> "responseFriend",
      "senderIP" -> player.ip,
      "response" -> response
    ))

    toServerCommunication ! message
  }

  /**
    *
    * @return
    */
  override def getPlayersIp(): List[String] ={
    val message = JSONObject(Map[String,Any](
      "object" -> "playersIP",
      "senderIP" -> player.ip
    ))

    val response = getJSONMessage(message)
    val listIP = response.obj("list").asInstanceOf[List[String]]
    listIP
  }

  /**
    * Send to server the request to take the list og player's ip.
    *
    * @return list of all the players's ip
    */
  private def getJSONMessage( message: JSONObject) : JSONObject = {
    inbox.send(toServerCommunication, message)
    inbox.receive(Duration.apply(10000,TimeUnit.SECONDS)).asInstanceOf[JSONObject]
  }

  /**
    * Convert the list, that is send from server to List of MatchResult object.
    * @param list list with match information about single user
    *
    * @return list of user's result of match
    */
  private def wrapperAllMatches(list : List[Map[String, Any]]): List[MatchResult] ={
    var allMatches: List[MatchResultImpl] = List.empty
    list.foreach(map =>{
      val matchResult = new MatchResultImpl()
      map.keySet.foreach {
        case "date" => matchResult.date = map("date").asInstanceOf[Calendar]
        case "score" => matchResult.score = map("score").asInstanceOf[Int]
        case "result" => matchResult.result = map("result").asInstanceOf[Boolean]
      }
      allMatches = matchResult :: allMatches
    })
    allMatches
  }

  /**
    * Convert the bytes's array to image, that is saved in the resources directory.
    *
    * @param path path of file to save
    * @param image byte's array that represent the file
    */
  private def saveImageToResources(path: String, image: Array[Byte]): Unit ={
    val outputfile = new File(path)
    outputfile.mkdirs()
    outputfile.createNewFile()
    val inputStream: InputStream = new ByteArrayInputStream(image)
    val bufferedImage: BufferedImage = ImageIO.read(inputStream)
    ImageIO.write(bufferedImage, "png", outputfile)
  }

  /**
    * Convert the password from string to encrypted string.
    * @param data password to encrypt
    *
    * @return the encrypted password
    */
  private def getSHA1(data: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-1")
    val ha = new sun.misc.BASE64Encoder().encode(md.digest(data.getBytes))
    ha
  }
}