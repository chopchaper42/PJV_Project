package Engine;

import Engine.Entity.Items.Type;
import Engine.Entity.Player;
import Utility.Window;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UIManager {
    private Canvas canvas;
    private Player player;

    public UIManager(Window window, Player player, Color color, Font font) {
        this.player = player;
        this.canvas = new Canvas(window.width(), window.height());
        this.canvas.getGraphicsContext2D().setFill(color);
        this.canvas.getGraphicsContext2D().setFont(font);
    }

    public void update() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().fillText("Ammo: " + player.getItemAmount(Type.AMMO), 0, 35);
        canvas.getGraphicsContext2D().fillText("HP: " + player.getHealth(), 0, 75);
        canvas.getGraphicsContext2D().fillText(String.valueOf(player.getInventory().getAmount(Type.KEY)), 0, 115);
        canvas.getGraphicsContext2D().drawImage(
                new Image(new File("./src/main/assets/key.png").toURI().toString()),
                20, 80,
                40, 40
        );

    }

    public Canvas canvas() {
        return canvas;
    }
}
