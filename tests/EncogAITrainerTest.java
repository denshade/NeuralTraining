import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncogAITrainerTest {

    @Test
    void train() throws Exception {
        EncogAITrainer trainer = new EncogAITrainer();
        BoardContext context = new BoardContext(3);
        context.markState(0,1, BoardState.Goal);
        context.markState(1,1, BoardState.Bomb);

        List<Example> examples = new ArrayList<>();
        examples.add(new Example(context, MovementDirection.RIGHT, 0));
        examples.add(new Example(context, MovementDirection.LEFT, 1));
        examples.add(new Example(context, MovementDirection.UP, 0.5));
        examples.add(new Example(context, MovementDirection.DOWN, 0.5));

        AI ai = trainer.train(examples, Arrays.asList(new Integer[]{3}));
        MovementDirection direction = ai.predict(context);
        assertEquals(direction, MovementDirection.LEFT);
    }

    @Test
    void convertToDouble() throws Exception {
        BoardState[][] board = new BoardState[2][3];
        board[0][0] = BoardState.Bomb;
        board[0][1] = BoardState.NoState;
        board[0][2] = BoardState.NoState;
        board[1][0] = BoardState.Goal;
        board[1][1] = BoardState.Speedup;
        board[1][2] = BoardState.Wall;
        BoardContext context = new BoardContext(3);
        context.markState(-1,-1, BoardState.Bomb);
        context.markState(-1,0, BoardState.Goal);
        context.markState(0,0, BoardState.Speedup);
        context.markState(+1,0, BoardState.Wall);
        double[] floats = BoardContextToVector.convertToDouble(context);
        assertNotNull(floats);
    }
}