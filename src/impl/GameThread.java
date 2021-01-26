package impl;

import engine.Engine;
import engine.GameLoader;
import game.Element;
import game.Game;
import game.Tile;

import java.util.HashMap;

public class GameThread implements Runnable {
    private Engine engine;
    public void run() {
        GameLoader gameLoader = new GameLoader(10);

        HashMap<Integer, Class<? extends Tile>> tileHashMap = new HashMap<>();
        tileHashMap.put(0, FieldTile.class);
        tileHashMap.put(1, BorderTile.class);
        gameLoader.addTileConfiguration(tileHashMap);

        HashMap<Integer, Class<? extends Element>> elementHashMap = new HashMap<>();
        elementHashMap.put(0, Link.class);
        elementHashMap.put(3, LineBlock.class);
//              elementHashMap.put(0, MouseCursor.class);
        gameLoader.addElementsConfiguration(elementHashMap);

        gameLoader.addLevel(1, "/resources/level1Tiles.txt", "/resources/level1Elements.txt");

        Game game = gameLoader.load();

//        game.getLevels().get(0).setFocusedElement(game.getLevels().get(0).getElements().get(0));
        game.setActiveLevel(game.getLevels().get(0));

        Engine engine = new Engine(game);
        engine.addBehavior(MoveOnMouseMove.class, new MouseMoveManager());


        Link.counter = 4;
        Link.speed = 10;

        Link player1 = new Link();
        Link player2 = new Link();
        Link player3 = new Link();
        Link player4 = new Link();

        game.getActiveLevel().getElements().add(player1);
        game.getActiveLevel().getElements().add(player2);
        game.getActiveLevel().getElements().add(player3);
        game.getActiveLevel().getElements().add(player4);

        String[] controls1 = {"UP", "DOWN", "RIGHT", "LEFT"};
        player1.realConstructor(controls1, 0, game, false);
        player1.startPos(100, 100, "RIGHT"); //"UP", "DOWN", "RIGHT", "LEFT"

        String[] controls2 = {"W", "S", "D", "A"};
        player2.realConstructor(controls2, 1, game, false);
        player2.startPos(900, 100, "LEFT");

        String[] controls3 = {"NUMPAD8", "NUMPAD5", "NUMPAD6", "NUMPAD4"};
        player3.realConstructor(controls3, 2, game, false);
        player3.startPos(900, 650, "LEFT");

        String[] controls4 = {"U", "J", "K", "H"};
        player4.realConstructor(controls4, 3, game, false);
        player4.startPos(100, 650, "RIGHT");

    }

    public Engine getEngine(){
        return this.engine;
    }
}