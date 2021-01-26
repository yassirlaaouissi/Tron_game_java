package impl;


import behavior.behaviors.Collidable;
import game.Tile;
import javafx.scene.image.Image;

public class FieldTile extends Tile implements Collidable {
    private int playerId;
    private boolean changeColor = false;
    private boolean left;
    private boolean firstTime = true;

    public FieldTile() {
        super("/resources/FieldTile.png");
        playerId = 99;
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if(firstTime){
            while(!changeColor){
                System.out.println("Nog niet!");
            }
            playerLeft();
            firstTime = false;
        }
    }

    public void playerLeft(){
        this.setImage(new Image("resources/TailBlock" + this.playerId + "-10.png"));
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getPlayerId(){
        return this.playerId;
    }

}
