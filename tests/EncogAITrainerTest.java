import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncogAITrainerTest {

    @Test
    void train() throws Exception {
        EncogAITrainer trainer = new EncogAITrainer();
        BoardState[][] board = new BoardState[3][3];//
        board[0][0] = BoardState.NoState;
        board[0][1] = BoardState.Goal;
        board[0][2] = BoardState.NoState;
        board[1][0] = BoardState.NoState;
        board[1][1] = BoardState.NoState;//Center = player.
        board[1][2] = BoardState.NoState;
        board[2][0] = BoardState.NoState;
        board[2][1] = BoardState.Bomb;
        board[2][2] = BoardState.NoState;

        List<Example> examples = new ArrayList<>();
        examples.add(new Example(board, MovementDirection.RIGHT, 0));
        examples.add(new Example(board, MovementDirection.LEFT, 1));
        examples.add(new Example(board, MovementDirection.UP, 0.5));
        examples.add(new Example(board, MovementDirection.DOWN, 0.5));

        AI ai = trainer.train(examples, Arrays.asList(new Integer[]{3}));
        MovementDirection direction = ai.predict(board);
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
        double[] floats = BoardContextToVector.convertToDouble(board);
        assertNotNull(floats);
    }
}