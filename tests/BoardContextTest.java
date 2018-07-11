import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardContextTest {

    @Test
    void markState()
    {
        BoardContext context = new BoardContext(3);
        context.markState(0,1, BoardState.Bomb);
        assertEquals(context.getStates()[2][1], BoardState.Bomb);
    }
}