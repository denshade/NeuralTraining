public class BoardContextToVector
{
    public static double[] convertToDouble(BoardState[][] board) throws Exception {
        double[] doublesFlat = new double[board.length*board[0].length];
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                double res = 0;
                switch (board[x][y])
                {
                    case Bomb: res = -1; break;
                    case NoState: res = 0; break;
                    case Wall: res = -1; break;
                    case Speedup: res = 0.5; break;
                    case Goal: res = 1; break;
                    default: throw new Exception("Unknow board state");
                }
                doublesFlat[x * board[x].length + y] = res;
            }
        }
        return doublesFlat;
    }
}
