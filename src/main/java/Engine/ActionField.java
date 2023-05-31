package Engine;

import Engine.Entity.Entity;
import Engine.Entity.Tile.Tile;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class ActionField {
    private Entity entity;
    private double radiusInPixels;
    private Rectangle2D bounds;

    /**
     * Creates an action field
     *
     * @param radiusInTiles the radius of the action field in tiles
     * @param entity the entity, that will have this action field
     */
    public ActionField(double radiusInTiles, Entity entity) {
        this.entity = entity;
        this.radiusInPixels = radiusInTiles * Tile.TILE_SIZE;
        updateToMatchCoordinates();
    }

    public Rectangle2D bounds() {
        return bounds;
    }

    /**
     * Updates field's coordinates to match entity's coordinates
     */
    public void updateToMatchCoordinates() {
        Point2D entityCenter = entity.center();
        this.bounds = new Rectangle2D(
                entityCenter.getX() - radiusInPixels,
                entityCenter.getY() - radiusInPixels,
                2 * radiusInPixels + entity.width(),
                2 * radiusInPixels + entity.height()
        );
    }

    /**
     * Draws an action field
     *
     * @param canvas canvas
     * @param color color
     */
    public void draw(Canvas canvas, Color color) {
        canvas.getGraphicsContext2D().setStroke(color);
        canvas.getGraphicsContext2D().strokeRect(
                bounds.getMinX(),
                bounds.getMinY(),
                bounds.getWidth(),
                bounds.getHeight()
        );
    }

}
