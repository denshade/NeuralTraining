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

    @Test
    void testRotate()
    {
        BoardContext context = new BoardContext(3);
        context.markState(-1,-1, BoardState.Bomb);
        BoardContext rotatedContext = BoardContext.rotate(context);
        assertEquals(rotatedContext.getStates()[0][2], BoardState.Bomb);
        BoardContext rotatedContext2 = BoardContext.rotate(rotatedContext);
        assertEquals(rotatedContext2.getStates()[2][2], BoardState.Bomb);
        BoardContext rotatedContext3 = BoardContext.rotate(rotatedContext2);
        assertEquals(rotatedContext3.getStates()[2][0], BoardState.Bomb);

    }

}