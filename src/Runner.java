
import engine.Engine;
import engine.GameLoader;
import game.Element;
import game.Game;
import game.Tile;
import impl.*;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.FirebaseService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Runner extends Application implements Runnable {



    public void run(){
        launch();

    }
    static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {

        try {

            Label label = new Label("Press Enter To Play");
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font ("Verdana", 20));
            label.setLayoutX(110);
            label.setLayoutY(160);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(Animation.INDEFINITE);
            fadeTransition.play();






            String musicFile = "src\\resources\\tron.mp3";     // For example
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();



            FileInputStream inputstream = new FileInputStream("src\\resources\\giphy9.gif");
            BackgroundImage back = new BackgroundImage(new Image(inputstream), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);



            Pane root = new Pane(label);

            //root.setStyle("-fx-background-color: GREY;");
            root.setBackground(new Background(back));
            Scene scene = new Scene(root,395,185);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode().equals(KeyCode.ENTER))
                    {
                        System.out.println("Enter is ingedrukt");
                        try {
                            startloginscreen(primaryStage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }});


            primaryStage.setScene(scene);

            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    //hoofdmenu
    public void startloginscreen(Stage stage) throws IOException {

        // Aanmaken van grid, hier komen alle elementen in te staan
        // hier maak je een grid aan waar al je buttons in komen te staan
        GridPane grid = new GridPane();
        //hier zeg je dat je grid in het midden van je scene moet komen te staan
        grid.setAlignment(Pos.CENTER);
        //spacing tussen de grid elementen voer je hier in
        grid.setHgap(10);
        grid.setVgap(10.0);
        //padding tussen je grid en content in je grid
        grid.setPadding(new Insets(40, 40, 40, 40));

        /* Aanmaken van elementen in je grid*/
        // hier maak je een headertext aan en geef je daar een font aan mee
        Text headerText = new Text("Welkom | TRON 2");
        headerText.setFill(Color.WHITE);
        headerText.setFont(Font.font("resources/TRON.TTF", FontWeight.NORMAL, 20));

        //hier voeg je je headerText toe aan je grid
        grid.add(headerText, 0, 0, 2, 1);

        //hier maak je de inputbox voor je username en de text boven je inputbox
        Label usernameLabel = new Label("Username");
        usernameLabel.setTextFill(Color.WHITE);
        grid.add(usernameLabel, 0, 1);
        TextField usernameBox = new TextField();
        grid.add(usernameBox, 1, 1);

        //hier maak je de inputbox voor je password en de text boven je inputbox
        Label passwordLabel = new Label("Password");
        passwordLabel.setTextFill(Color.WHITE);
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);
        //
        //hier maak je de buttons aan
        Button loginButton = new Button("Log In");
        Button exitButton = new Button("Game Afsluiten");
        Button settingsButton = new Button("Instellingen");



        //hier zet je alle button op een rijtje met een spacing van 10 tussen de knopjes in en onderin gecentreerd
        HBox knoppies = new HBox(10);
        knoppies.setAlignment(Pos.BOTTOM_CENTER);
        knoppies.getChildren().addAll(loginButton, exitButton, settingsButton);
        grid.add(knoppies, 1, 6);


        //hier voeg je een succesmessage toe die pas zichtbaar wordt als je op de loginbutton klikt
        Text succesText = new Text("Je bent succesvol ingelogd");
        succesText.setFill(Color.GREEN);
        succesText.setVisible(false);
        grid.add(succesText, 1, 7);
        Text failedText = new Text("Je gebruikersnaam of wachtwoord is ongeldig");
        failedText.setFill(Color.RED);
        failedText.setVisible(false);
        grid.add(failedText, 1, 7);

        /* KeyListeners voor de knoppen */
        //OnAction voor de loginbutton
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hier moet nog iets van een check komen of de combinatie van username+password klopt. en een redirect naar een andere scene
                succesText.setVisible(true);
                System.out.println("login knop ingedrukt");

                try {
                    startHoofdmenu(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
        //OnAction voor de exitbutton
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });

        //OnAction voor de settingsButton
        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hier moet nog een redirect komen naar een andere scene of popup
                System.out.println("Settingsbutton ingeklikt");


            }
        });

        //hier maak je de achtergrond aan
        FileInputStream inputstream = new FileInputStream("src\\resources\\background.jpg");
        BackgroundImage back = new BackgroundImage(new Image(inputstream), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        grid.setBackground(new Background(back));

        //hier maak je je scene aan
        Scene scene = new Scene(grid, 600, 500);
        stage.setResizable(false);
        stage.setMaxWidth(600);
        stage.setMaxHeight(500);
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);

    }

    private ImageView createImage() throws Exception {
        //Passing FileInputStream object as a parameter
        FileInputStream inputstream = new
                FileInputStream("res/iposeimage.png");
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    //hoofdmenu

    public void startHoofdmenu(Stage primaryStage) throws Exception {

        try{
            Button startGameButton = new Button("Start Game");
            Button highScoreButton = new Button("Highscores");

            startGameButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    startGame(primaryStage);
                }
            });

            VBox vBox = new VBox();
            vBox.getChildren().addAll(startGameButton,highScoreButton);


            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(vBox);
            Scene scene = new Scene(borderPane);

            primaryStage.setScene(scene);

            primaryStage.show();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void startGame(Stage primaryStage){
        GameLoader gameLoader = new GameLoader(10);

        HashMap<Integer, Class<? extends Tile>> tileHashMap = new HashMap<>();
        tileHashMap.put(0,FieldTile.class);
        tileHashMap.put(1, BorderTile.class);
        gameLoader.addTileConfiguration(tileHashMap);

        HashMap<Integer, Class<? extends Element>> elementHashMap = new HashMap<>();
        elementHashMap.put(0, Link.class);
        elementHashMap.put(3, LineBlock.class);
//              elementHashMap.put(0, MouseCursor.class);
        gameLoader.addElementsConfiguration(elementHashMap);

        gameLoader.addLevel(1,"/resources/level1Tiles.txt","/resources/level1Elements.txt");

        Game game = gameLoader.load();

//        game.getLevels().get(0).setFocusedElement(game.getLevels().get(0).getElements().get(0));
        game.setActiveLevel(game.getLevels().get(0));

        Engine engine = new Engine(game);
        engine.addBehavior(MoveOnMouseMove.class,new MouseMoveManager());
        engine.start(primaryStage);

        primaryStage.setMinWidth(1200);
        primaryStage.setMinHeight(900);
        primaryStage.centerOnScreen();

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
        player1.realConstructor( controls1, 0, game, false);
        player1.startPos(100, 100, "RIGHT"); //"UP", "DOWN", "RIGHT", "LEFT"

        String[] controls2 = {"W", "S", "D", "A"};
        player2.realConstructor(controls2, 1, game, false);
        player2.startPos(900, 100, "LEFT");

        String[] controls3 = {"NUMPAD8", "NUMPAD5", "NUMPAD6", "NUMPAD4"};
        player3.realConstructor( controls3, 2, game, false);
        player3.startPos(900, 650, "LEFT");

        String[] controls4 = {"U", "J", "K", "H"};
        player4.realConstructor(controls4, 3, game, false);
        player4.startPos(100, 650, "RIGHT");


    }
}
