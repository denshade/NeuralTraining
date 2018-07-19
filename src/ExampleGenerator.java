import java.util.ArrayList;
import java.util.List;

public class ExampleGenerator
{
    public static List<Example> getExamples() throws Exception {
        List<Example> examples = new ArrayList<>();
        for (BoardState state00 : BoardState.values())
            for (BoardState state01 : BoardState.values())
                for (BoardState state02 : BoardState.values())
                    for (BoardState state10 : BoardState.values())
                        for (BoardState state12 : BoardState.values())
                            for (BoardState state20 : BoardState.values())
                                for (BoardState state21 : BoardState.values())
                                    for (BoardState state22 : BoardState.values())
                                    {
                                        BoardContext context = new BoardContext(3);
                                        context.markState(-1, -1, state00);
                                        context.markState(0, -1, state01);
                                        context.markState(1, -1, state02);
                                        context.markState(-1, 0, state10);
                                        context.markState(0, 0, BoardState.NoState);
                                        context.markState(1, 0, state12);
                                        context.markState(-1, 1, state20);
                                        context.markState(0, 1, state21);
                                        context.markState(1, 1, state22);

                                        examples.add(new Example(context,MovementDirection.UP, convertToFloat(state01)));
                                        examples.add(new Example(context,MovementDirection.DOWN, convertToFloat(state21)));
                                        examples.add(new Example(context,MovementDirection.LEFT, convertToFloat(state10)));
                                        examples.add(new Example(context,MovementDirection.RIGHT, convertToFloat(state12)));
                                    }


        return examples;
    }

    private static double convertToFloat(BoardState state) {
        switch (state)
        {
            case Bomb: return 0.0;
            case Goal: return 1.0;
            case Wall: return 0.5;
            case NoState: return 0.5;
            case Speedup: return 0.7;
        }
        return 0;
    }
}
