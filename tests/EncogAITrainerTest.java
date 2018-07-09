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
        board[0][1] = BoardState.NoState;
        board[0][2] = BoardState.NoState;
        board[1][0] = BoardState.NoState;
        board[1][1] = BoardState.NoState;//Center = player.
        board[1][2] = BoardState.NoState;
        board[2][0] = BoardState.NoState;
        board[2][1] = BoardState.Bomb;
        board[2][2] = BoardState.NoState;

        List<Example> examples = new ArrayList<>();
        examples.add(new Example(board, MovementDirection.RIGHT, false));
        examples.add(new Example(board, MovementDirection.LEFT, true));
        examples.add(new Example(board, MovementDirection.UP, true));
        examples.add(new Example(board, MovementDirection.DOWN, true));

        AI ai = trainer.train(examples, Arrays.asList(new Integer[]{3}));
        ai.predict(board);
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
        double[] floats = EncogAITrainer.convertToDouble(board);
        assertNotNull(floats);
    }
}