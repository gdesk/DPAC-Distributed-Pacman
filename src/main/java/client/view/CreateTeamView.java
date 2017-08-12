package client.view;

/**
 * Created by chiaravarini on 09/08/17.
 */
public interface CreateTeamView {

    /**
     *Renders the response of a player
     * already invited to the match
     * @param response
     */
    void renderPlayerResponse(final Boolean response);
}
