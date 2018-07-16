
public interface Level
{
    void tick();
    BoardContext getContext(int x,int y);
    boolean isGoalReached(int x,int y);

    boolean isGameOver(int x, int y);

    boolean isInvalid(int x, int y);
}
