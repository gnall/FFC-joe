package washo.gmd.app.client.local.events;

import washo.gmd.app.shared.Game;

public class GameCreated {

    private Game game;

    public GameCreated() {}

    public GameCreated(Game event) {
        this.game = event;
        
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game event) {
        this.game = event;
    }
}
