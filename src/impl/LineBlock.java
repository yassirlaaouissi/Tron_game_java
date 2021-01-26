package impl;

import behavior.behaviors.Collidable;
import game.Tile;

public class LineBlock extends Tile implements Collidable {
    private int playerId;
    public LineBlock() {
        super("/resources/TailBlock.png");
        playerId = 99;
    }

    @Override
    public void handleCollision(Collidable collidable) {
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getPlayerId(){
        return this.playerId;
    }
}
