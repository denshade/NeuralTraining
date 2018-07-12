public class BoardContextLoader
{
    public static BoardContext loadFromString(String context, int size)
    {
        BoardContext boardContext = new BoardContext(size);
        String[] lines = context.split(";");
        for (int y = 0; y < lines.length; y++)
        {
            String currentLine = lines[y];
            String[] symbols = currentLine.split(",");
            for (int x = 0; x < symbols.length; x++)
            {
                BoardState state;
                switch (symbols[x])
                {
                    case "B": state = BoardState.Bomb; break;
                    case "W": state = BoardState.Wall; break;
                    case "G": state = BoardState.Goal; break;
                    case "N": state = BoardState.NoState; break;
                    default: throw new IllegalArgumentException("State with name " + symbols[x] + " Unknown.");
                }
                boardContext.getStates()[y][x] = state;
            }
        }
        return boardContext;
    }
}
