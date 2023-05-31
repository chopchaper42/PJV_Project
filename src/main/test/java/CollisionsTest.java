import Engine.Entity.Entity;
import Engine.Entity.Items.Item;
import Engine.Entity.Tile.Tile;
import Utility.Collisions;
import javafx.beans.binding.When;
import javafx.geometry.Rectangle2D;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.powermock.api.mockito.PowerMockito;


public class CollisionsTest
{
    @Mock
    Rectangle2D boundaries = Mockito.mock(Rectangle2D.class);

    @Mock
    Tile tile = Mockito.mock(Tile.class);

    @Mock
    Entity entity = Mockito.mock(Entity.class);

    @Mock
    Item item = Mockito.mock(Item.class);

    @Test
    public void checkWallCollision()
    {

        // Call the method that invokes the static method
        Collisions.checkWallCollision(boundaries, new ArrayList<Tile>());
        PowerMockito.verifyStatic(Collisions.class, Mockito.times(1));
    }

    @Test
    public void checkItemCollision()
    {
        List<Item> items = new ArrayList<>();
        items.add(item);

        Mockito.when(entity.getBoundaries()).thenReturn(boundaries);

        Collisions.checkItemCollision(entity, items);
        PowerMockito.verifyStatic(Collisions.class, Mockito.times(1));
    }
}
