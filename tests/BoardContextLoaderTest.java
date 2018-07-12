import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardContextLoaderTest {

    @Test
    void loadFromString() {
        BoardContextLoader loader = new BoardContextLoader();
        BoardContext context = loader.loadFromString("N,N,N;N,B,N;N,G,N", 3);
        assertEquals(BoardState.NoState, context.getStates()[0][0]);
        assertEquals(BoardState.NoState, context.getStates()[0][1]);
        assertEquals(BoardState.NoState, context.getStates()[0][2]);
        assertEquals(BoardState.NoState, context.getStates()[1][0]);
        assertEquals(BoardState.Bomb,    context.getStates()[1][1]);
        assertEquals(BoardState.NoState, context.getStates()[1][2]);
        assertEquals(BoardState.NoState, context.getStates()[2][0]);
        assertEquals(BoardState.Goal,    context.getStates()[2][1]);
        assertEquals(BoardState.NoState, context.getStates()[2][2]);

    }
}