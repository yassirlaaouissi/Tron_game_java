package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTile extends Tile implements Collidable {
    public BorderTile() {
        super("/resources/BorderTile.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
