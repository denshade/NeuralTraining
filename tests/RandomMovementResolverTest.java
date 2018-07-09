import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomMovementResolverTest {

    @Test
    public void testRandom()
    {
        RandomMovementResolver r =  new RandomMovementResolver();
        assertNotNull(r.runMove(null));
    }

}