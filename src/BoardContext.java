public class BoardContext
{
    private BoardState[][] states;

    private int size;

    public int getSize()
    {
        return size;
    }

    /**
     * This will make a board state with in the middle the actual location.
     * @param size width and height of the context. Must be an odd number.
     */
    public BoardContext(int size)
    {
        if (size % 2 == 0) throw new IllegalArgumentException("size must be odd but is " + size);
        this.size = size;
        states = new BoardState[size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                states[y][x] = BoardState.NoState;
    }

    public static BoardContext rotate(BoardContext context)
    {
        BoardContext rotatedContext = new BoardContext(context.getSize());
        BoardState[][] currentStates = context.getStates();
        BoardState[][] rotatedStates = rotatedContext.getStates();
        final int M = currentStates.length;
        final int N = currentStates[0].length;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                rotatedStates[c][M-1-r] = currentStates[r][c];
            }
        }
        return rotatedContext;
    }

    public void markState(int x, int y, BoardState state)
    {

        int xmiddle = size/2;
        int ymiddle = size/2;
        if (x + xmiddle > size) {
            throw new IllegalArgumentException(x + " > " + size);
        }
        if (y + ymiddle > size) {
            throw new IllegalArgumentException(y + " > " + size);
        }

        states[ymiddle+y][xmiddle+x] = state;
    }

    public BoardState[][] getStates() {
        return states;
    }
}
