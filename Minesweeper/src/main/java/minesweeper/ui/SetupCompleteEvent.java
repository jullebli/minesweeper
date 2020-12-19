
package minesweeper.ui;

import minesweeper.domain.Game;


public class SetupCompleteEvent {
    
    private Game game;
    
    public SetupCompleteEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
