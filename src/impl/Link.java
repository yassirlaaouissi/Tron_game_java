package impl;

import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import game.Game;
import game.Tile;
import javafx.scene.image.Image;


import java.util.ArrayList;

public class Link extends Element implements Collidable, KeyBehavior {
    public static int counter = 4;
    public static boolean starten = false;
    public static int speed;

    private String[] controls = new String[4];
    private int deltaX;
    private int deltaY;
    private Game game;
    private boolean nextKey = false;
    private String lastKey;
    private boolean riding = true;
    private Element player;
    private int playerId;
    private boolean collidebaar = true;
    private FieldTile oldFieldTile;
    private boolean doorEigenStaart;

    public Link() {
        super("/resources/CycleBlock.png");
    }

    public void realConstructor(String[] controls, int playerId, Game game, boolean magDoorEigenStaart) {
        this.game = game;
        this.controls = controls; //UP, DOWN, RIGHT, LEFT
        this.player = game.getActiveLevel().getElements().get(playerId);
        this.playerId = playerId;
        player.setImage(new Image("resources/Cycle" + playerId + "Block.png"));
        this.doorEigenStaart = magDoorEigenStaart;
    }

    //Miminaal 80,80 en maximaal 950, 725 ish && "UP"/"DOWN"/"RIGHT"/"LEFT"
    public void startPos(double posX, int posY, String side) {
        this.lastKey = side;
        player.setX(posX);
        player.setY(posY);
    }


    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
        if(arrayList.contains("SPACE")) {
            if(!starten) {
                starten = true;
            }else{
                starten = false;
            }
        }
        if (!this.riding || starten) {
            return;
        } //Als hij niet meer mag bewegen zorgt dit ervoor dat geen keyinput meer werkt
        if (arrayList.size() == 0) {
            this.nextKey = true;
        }

        if (this.nextKey) {
            if (arrayList.contains(this.controls[0]) && lastKey != "DOWN") {
                this.nextKey = false;
                this.lastKey = "UP";
            } else if (arrayList.contains(this.controls[1]) && lastKey != "UP") {
                this.nextKey = false;
                this.lastKey = "DOWN";
            } else if (arrayList.contains(this.controls[2]) && lastKey != "LEFT") {
                this.nextKey = false;
                this.lastKey = "RIGHT";
            } else if (arrayList.contains(this.controls[3]) && lastKey != "RIGHT") {
                this.nextKey = false;
                this.lastKey = "LEFT";
            }

        }
        if (this.lastKey.equals("UP")) {
            super.setY(super.getY() - speed);
        } else if (this.lastKey.equals("DOWN")) {
            super.setY(super.getY() + speed);
        } else if (this.lastKey.equals("RIGHT")) {
            super.setX(super.getX() + speed);
        } else if (this.lastKey.equals("LEFT")) {
            super.setX(super.getX() - speed);
        }
    }

    @Override
    public void handleCollision(Collidable collidable) {
        if (!collidebaar || collidable == null) {
            return;
        }
        if (collidable instanceof BorderTile) {
            goDie();
        }
        if (collidable instanceof Link) {
            Link player = (Link) collidable;
            if (player.getPlayerId() != this.playerId) {
                goDie();
            }
        }
        if (collidable instanceof FieldTile) {
            FieldTile tile = (FieldTile) collidable;

            if ((super.getX() == tile.getX()) && (super.getY() == tile.getY())) {
                oldFieldTile = tile;
                tile.setPlayerId(this.playerId);

            }
            if (oldFieldTile != null) {
                if ((super.getX() == tile.getX()) && (super.getY() == tile.getY())) {
                    oldFieldTile = tile;

                }
                oldFieldTile.playerLeft();
            }

            if (tile.getPlayerId() != this.playerId && tile.getPlayerId() != 99) {
                goDie();
            }
//            else if (!doorEigenStaart) {
//                if (tile.getPlayerId() == this.playerId) {
//                    goDie();
//                }
//            }
        }
    }


    private void goDie() {
        riding = false;
        player.setImage(new Image("resources/CycleBlock.png"));
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaX(int getal) {
        this.deltaX = getal;
    }

    public void setDeltaY(int getal) {
        this.deltaY = getal;
    }
}
