package Engine.Entity.Tiles;

import javafx.scene.image.Image;

import java.io.File;

public class Wall extends Tile
{
    private static final Image image = new Image(new File("./src/main/assets/wall.png").toURI().toString());

    public Wall(double x, double y) {
        super(image, x, y);
    }
}
