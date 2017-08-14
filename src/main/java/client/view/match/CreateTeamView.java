package client.view.match;

/**
 * Functional interface with which it's possiblev renders
 * the response of a player already invited to the match
 * Created by Chiara Varini on 09/08/17.
 */
public interface CreateTeamView {

    /**
     *Renders the response of a player
     * already invited to the match
     * @param response
     */
    void renderPlayerResponse(final Boolean response);

    void renderPlayerInMatch(final Integer response);
}
