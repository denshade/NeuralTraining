public class BoardContext
{
    private BoardState[][] states;

    private int size;

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
