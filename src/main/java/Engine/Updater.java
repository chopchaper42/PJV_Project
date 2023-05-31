package Engine;

import Engine.Entity.*;
import Engine.Entity.Items.Item;
import Engine.Entity.Tile.Door;
import Engine.Level.Level;
import GUI.GUIManager;
import Logs.Logger;
import Utility.Collisions;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import network.udp.client.ClientController;
import network.udp.client.ClientControllerSingleton;
import network.udp.client.UpdatedState;

import java.util.ArrayList;
import java.util.List;

public class Updater {
    private final List<Entity> toRemove = new ArrayList<>();
    private Level level;
    private Player player;
    private UIManager uiManager;
    private GUIManager guiManager;
    private ClientController controller;

    public Updater(Level level, Player player, UIManager uiManager, GUIManager guiManager) {
        this.level = level;
        this.player = player;
        this.uiManager = uiManager;
        this.guiManager = guiManager;
    }

    public void update(double dt) {

        controller = ClientControllerSingleton.getInstance();

        UpdatedState updatedState = controller.checkUpdatesFromAnotherClient();
        if (updatedState != null)
        {
            updateAllNecessaryEntities(updatedState);
        }


        redrawEntities(level, level.canvas());

        //redraw UI
        uiManager.update();


        if (player.alive()) {
            //enemies shoot
            level.enemies().forEach(enemy -> enemy.shoot(player, level.bullets(), dt));

            // check for items in player's range
            List<Item> itemsInRange = Collisions.checkItemCollision(player, level.items());
            player.takeItems(itemsInRange);

            List<Item> itemsInFriendRange = Collisions.checkItemCollision(level.getFriends().get(0), level.items());

            toRemove.addAll(itemsInRange);
            toRemove.addAll(itemsInFriendRange);

            // check if player is in enemies vision zone
            Collisions.checkEnemiesVisionZoneIntersection(player, level.enemies());
        }

        // show vision fields
        level.enemies().forEach(enemy -> {
            enemy.move(level.tiles(), dt);
                if (GameSettings.getShowFields()) {
                    enemy.visionField().draw(level.canvas(), Color.YELLOWGREEN);
                }
        });


        // mark bullets "to remove" if they intersect with a wall
        level.bullets().forEach(bullet -> { // override draw() in bullet to move on each call
            if (Collisions.checkWallCollision(bullet, level.tiles())) {
                toRemove.add(bullet);
            }
            if (player.alive() && bullet.source() != player && player.getBoundaries().intersects(bullet.getBoundaries())) { // may be add source to bullet to except friendly fire
                player.decreaseHealth(bullet.damage());
                toRemove.add(bullet);
            }



            Enemy intersectedEntity = Collisions.checkBulletCollision(bullet, level.enemies());
            if (intersectedEntity != null) {
                Logger.log(intersectedEntity + "'s been hit. Now he has " + intersectedEntity.getHealth() + " HP.");
                if ((intersectedEntity.getHealth() - bullet.damage()) <= 0) {
                    Logger.log(intersectedEntity + " added to REMOVE");
                    toRemove.add(intersectedEntity);
                }
                intersectedEntity.decreaseHealth(bullet.damage());
                toRemove.add(bullet);
            }

            bullet.move(dt);
        });

        // remove entities to remove
        toRemove.forEach(entity -> {
            if (entity instanceof Item) {
                level.items().remove(entity);
                controller.send("item", entity.getX(), entity.getY());
            } else if (entity instanceof Bullet) {
                level.bullets().remove(entity);
            } else if (entity instanceof Enemy) {
                level.enemies().remove(entity);
            } else if (entity instanceof Player) {
                player.kill();
            }
        });

        if (level.completed()) {
            InventoryManager.saveInventory();
            Logger.log("Level completed. All enemies are dead.");
            // TODO: send to server
            guiManager.renderWin();
        }

        if (player.getHealth() <= 0) {
            Logger.log("DEAD");
            toRemove.add(player);
            guiManager.renderLose();
        }
    }

    private void redrawEntities(Level level, Canvas canvas) {
        draw(level.tiles(), canvas);
        draw(level.items(), canvas);
        draw(level.bullets(), canvas);
        draw(level.enemies(), canvas);
        draw(level.getFriends(), canvas);
    }

    private <T extends Entity> void draw(List<T> entities, Canvas canvas) {
        entities.forEach(entity -> {
            entity.draw(canvas);
        });
    }

    private void updateAllNecessaryEntities(UpdatedState updatedState)
    {

        switch (updatedState.getJsonProperty())
        {

            case "playerPosition":
//                var friend = new Friend(
//                        updatedState.getPosX(), updatedState.getPosY(), 100
//                );
                level.getFriends().get(0).setX(updatedState.getPosX());
                level.getFriends().get(0).setY(updatedState.getPosY());
//                level.setFriends(friends);
                break;

            case "bullet":
                System.out.println("bullet:");
                System.out.println("--------------------------");
                System.out.println("updatedState.getPosX():" + updatedState.getPosX() + "\n" +
                                "updatedState.getPosY()" + updatedState.getPosY() + "\n" +
                        "updatedState.getBulletSpeedComponentX()" + updatedState.getBulletSpeedComponentX() + "\n" +
                        "updatedState.getBulletSpeedComponentY()" + updatedState.getBulletSpeedComponentY());
                System.out.println("--------------------------");

                var bullet = new Bullet (
                    updatedState.getPosX(), updatedState.getPosY(),
                        updatedState.getBulletSpeedComponentX(), updatedState.getBulletSpeedComponentY()
                );
                System.out.println("Bullet X component: " + bullet.getSpeedX());
                System.out.println("Bullet Y component: " + bullet.getSpeedY());
                level.bullets().add(bullet);

                break;

            case "enemy":
                level.enemies().clear();

                var enemy = new Enemy(
                        updatedState.getPosX(), updatedState.getPosY()
                );
                level.enemies().add(enemy);
                break;

            case "fellowIsDead":
                if (level.getFriends().size() > 0) {
                    level.getFriends().get(0).kill();
                }
                break;

            /*case "item": // It'll be better to check if Friend collides with some items.

                Item itemToRemove = null;

                for (Item item : level.items())
                {
                    if (item.getX() == updatedState.getPosX() && item.getY() == updatedState.getPosY())
                    {
                        itemToRemove = item;
                    }
                }
                level.items().remove(itemToRemove);
                break;*/

            case "door":
                level.tiles().forEach(tile -> {
                    if (tile instanceof Door &&
                            tile.getX() == updatedState.getPosX() &&
                            tile.getY() == updatedState.getPosY())
                        ((Door) tile).open();
                });
                break;

            default:
                break;
        }
    }
}
