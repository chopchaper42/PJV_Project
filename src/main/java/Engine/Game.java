package Engine;

import Engine.Entity.Entity;
import Engine.Level.Level;
import Engine.Entity.Player;
import Engine.Entity.Tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private List<Entity> entities = new ArrayList<>();
    private static Level level;
    private static Player player;
    private final Stage stage;
    private boolean W_pressed = false;
    private boolean A_pressed = false;
    private boolean S_pressed = false;
    private boolean D_pressed = false;


    public Game(Stage stage) {
        this.stage = stage;
        level = new Level(new File("./src/main/levels/level1.txt"));
    }


    public void run() {
        startGame();
        spawnPlayer();
        AnimationTimer loop = new AnimationTimer()
        {
            long lastFrame;
            @Override
            public void handle(long now)
            {
                double dt = (now - lastFrame) / 10e9;
                //receiveData();
                redraw();
                player.handleInput(W_pressed, A_pressed, S_pressed, D_pressed, dt);
                lastFrame = now;
                //sendData();
            }
        };
        loop.start();
    }
    private void redraw() {
        level.getTiles().forEach(Tile::draw);
        entities.forEach(Entity::draw);
        player.draw();
    }

    private void spawnPlayer()
    {
        Player player = new Player(level.getFirstFloorTile());
        entities.add(player);
        Game.player = player;
    }

    private void startGame() {
        Group group = new Group(Graphics.getCanvas());
        Scene scene = new Scene(group);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::press);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::release);

        stage.setScene(scene);
        stage.show();
    }

    private void release(KeyEvent event)
    {
        handle(event.getCode(), false);
    }

    private void press(KeyEvent event) {
        handle(event.getCode(), true);
    }

    private void handle(KeyCode code, boolean pressed) {
        switch (code) {
            case W -> W_pressed = pressed;
            case A -> A_pressed = pressed;
            case S -> S_pressed = pressed;
            case D -> D_pressed = pressed;
        }
    }

    public static Level getLevel()
    {
        return level;
    }
}
