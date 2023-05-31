import Engine.Entity.LivingEntity;
import Engine.Entity.MovingEntity;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

public class MovingEntityTest
{
@Test
    void checkMove()
    {
        MovingEntity movingEntity = Mockito.mock(LivingEntity.class);
        // Call the method on the mock
        movingEntity.move(0, 0);
        // Verify that the method was called
        Mockito.verify(movingEntity, Mockito.times(1)).move(0, 0);
    }

}
