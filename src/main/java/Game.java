import Engine.Entity;
import Engine.Level;
import Engine.Player;
import Engine.Tiles.Tile;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    List<Entity> entities = new ArrayList<>();
    Point2D defaultPosition;
    private Canvas canvas;
    private List<Tile> tiles;
    private Player player;
    private Stage stage;
    private boolean W_pressed = false;
    private boolean A_pressed = false;
    private boolean S_pressed = false;
    private boolean D_pressed = false;


    public Game(Stage stage) {
        this.canvas = new Canvas();
        tiles = new Level(
                new File("./src/main/levels/level1.txt"),
                canvas
        ).getTiles();
        defaultPosition = tiles.get(0).getPosition();
        this.stage = stage;
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
                player.move(W_pressed, A_pressed, S_pressed, D_pressed, dt);
                redrawTiles();
                redrawEntities();
                lastFrame = now;
            }
        };
        loop.start();
    }

    // TODO: Move this to the player class ???
    public void movePlayer(double dt) {

    }
    private void redrawTiles() {
        tiles.forEach(tile -> {
            getGraphicsContext().drawImage(tile.getImage(),
                    tile.getX(),
                    tile.getY());
        });
    }
    private void redrawEntities() {
        entities.forEach(entity ->
        {
            getGraphicsContext().drawImage(entity.getImage(),
                    entity.getX(),
                    entity.getY());
        });
    }

    private void spawnPlayer()
    {
        Player player = new Player(defaultPosition, 100);
        entities.add(player);
        this.player = player;
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    private void startGame() {
        Group group = new Group(canvas);
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

}
