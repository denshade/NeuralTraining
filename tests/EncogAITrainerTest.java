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
    void trainBombsAround() throws Exception {
        EncogAITrainer trainer = new EncogAITrainer();
        List<Example> examples = addExamples(0,1, MovementDirection.UP);
        examples.addAll(addExamples(1,0, MovementDirection.RIGHT));
        examples.addAll(addExamples(0,-1, MovementDirection.DOWN));
        examples.addAll(addExamples(-1,0, MovementDirection.LEFT));
        BoardContext downSafe = new BoardContext(3);
        downSafe.markState(0,1, BoardState.Bomb);
        downSafe.markState(1,0, BoardState.Bomb);
        downSafe.markState(-1,0, BoardState.Bomb);
        AI ai = trainer.train(examples, Arrays.asList(new Integer[]{4}));
        MovementDirection direction = ai.predict(downSafe);
        assertEquals(MovementDirection.UP, direction);
    }

    @Test
    void trainCertitudeBombsAround() throws Exception {
        EncogAITrainer trainer = new EncogAITrainer();
        List<Example> examples = addExamples(0,1, MovementDirection.UP);
        examples.addAll(addExamples(1,0, MovementDirection.RIGHT));
        examples.addAll(addExamples(0,-1, MovementDirection.DOWN));
        examples.addAll(addExamples(-1,0, MovementDirection.LEFT));
        BoardContext upSafe = new BoardContext(3);
        upSafe.markState(0,1, BoardState.Bomb);
        upSafe.markState(1,0, BoardState.Bomb);
        upSafe.markState(-1,0, BoardState.Bomb);
        examples.add(new Example(upSafe, MovementDirection.UP, 1));
        examples.add(new Example(upSafe, MovementDirection.DOWN, 0));
        examples.add(new Example(upSafe, MovementDirection.LEFT, 0));
        examples.add(new Example(upSafe, MovementDirection.RIGHT, 0));

        BoardContext downSafe = new BoardContext(3);
        downSafe.markState(0,-1, BoardState.Bomb);
        downSafe.markState(1,0, BoardState.Bomb);
        downSafe.markState(-1,0, BoardState.Bomb);
        examples.add(new Example(downSafe, MovementDirection.UP, 0));
        examples.add(new Example(downSafe, MovementDirection.DOWN, 1));
        examples.add(new Example(downSafe, MovementDirection.LEFT, 0));
        examples.add(new Example(downSafe, MovementDirection.RIGHT, 0));


        BoardContext testSafe = new BoardContext(3);
        testSafe.markState(0,-1, BoardState.Bomb);
        testSafe.markState(1,0, BoardState.Bomb);
        testSafe.markState(-1,0, BoardState.Bomb);
        testSafe.markState(0,1, BoardState.Goal);

        AI ai = trainer.train(examples, Arrays.asList(new Integer[]{4,3}));
        MovementDirection direction = ai.predict(downSafe);
        assertEquals(MovementDirection.UP, direction);
    }

    private List<Example> addExamples(int x, int y, MovementDirection direction) throws Exception {
        BoardContext c1 = new BoardContext(3);
        c1.markState(x, y, BoardState.Bomb);
        List<Example> examples = new ArrayList<>();
        for (MovementDirection curDirection : MovementDirection.values())
        {
            double score = 0.5;
            if (direction.equals(curDirection)) {
                score = 0;
            }
            examples.add(new Example(c1, direction, score));
        }
        return examples;
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