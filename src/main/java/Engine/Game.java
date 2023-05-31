package Engine;

import Engine.Level.Level;
import Engine.Entity.Player;
import GUI.GUIManager;
import Logs.Logger;
import Utility.Window;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import network.udp.client.ClientControllerSingleton;
//import network.udp.client.ClientController;
//import network.udp.client.ClientControllerSingleton;

import java.io.File;


/**
 * Contains the game logic.
 */
public class Game
{
    private final Window window;
    private final Level level;
    private final String levelFile;
    private final Stage stage;
    private final Player player;
    private final InputManager inputManager;
    private final UIManager uiManager;
    private final GUIManager guiManager;
    private final Updater updater;
    private boolean running = true;


    /**
     * Constructs a new Game object with a stage and a level
     */
    public Game(Window window, GUIManager guiManager, Stage stage, File level, Inventory inventory) {
        this.window = window;
        this.stage = stage;
        this.level = new Level(window, level);
        this.player = new Player(
                this.window,
                this.level.initialPlayerPosition().getX(),
                this.level.initialPlayerPosition().getY(),
                inventory
        );
        this.inputManager = new InputManager(this.player, this.level);
        this.uiManager = new UIManager(
                this.window,
                this.player,
                Color.YELLOWGREEN,
                new Font("Verdana", 40)
        );
        this.guiManager = guiManager;
        this.updater = new Updater(this.level, this.player, this.uiManager, this.guiManager);
        this.levelFile = level.getName();
    }


    /**
     * Runs the game
     */
    public void run() {
        startGame();
        Logger.log(player.getInventory().toString());

        AnimationTimer loop = new AnimationTimer()
        {
            long lastFrame;
            @Override
            public void handle(long now)
            {
                double dt = (now - lastFrame) / 10e9;
                updater.update(dt);
                inputManager.handleInput(dt);
                lastFrame = now;

                if (!player.alive() || level.completed()) {
                    this.stop();

                    ClientControllerSingleton.getInstance().send("fellowIsDead", 0, 0);

                    Logger.log("Game ended.");
                }

//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
            }
        };

        Thread socketThread = new Thread(() -> {
            ClientControllerSingleton.getInstance().run();
        });

        socketThread.start();
        loop.start();
    }

    private void startGame() {
        Group group = new Group(level.canvas(), player.canvas(), uiManager.canvas());
        Scene scene = new Scene(group);

        addEventListeners(scene);

        player.draw(player.getImage());

        stage.setScene(scene);
        stage.show();
    }

    private void addEventListeners(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, inputManager::press);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, inputManager::release);
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            player.shoot(event, level.bullets());
        });
    }

    public String levelFile()
    {
        return levelFile;
    }
}
