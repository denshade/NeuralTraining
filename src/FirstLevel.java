public class FirstLevel implements Level
{

    BoardContext context;

    public FirstLevel()
    {
        context = BoardContextLoader.loadFromString("B,B,B;" +
                                                  "B,N,B;" +
                                                  "G,G,G", 3);
    }
    @Override
    public void tick() {
        return;
    }

    @Override
    public BoardContext getContext(int x, int y) {
        return context;
    }

    @Override
    public boolean isGoalReached(int x, int y) {
        return (context.getStates()[y][x] == BoardState.Goal);
    }

    @Override
    public boolean isGameOver(int x, int y) {
        return (context.getStates()[y][x] == BoardState.Bomb);
    }

    @Override
    public boolean isInvalid(int x, int y) {
        return x < 0 || x > 2 || y < 0 || y > 2;
    }

    @Override
    public int getStartX() {
        return 1;
    }

    @Override
    public int getStartY() {
        return 1;
    }
}
