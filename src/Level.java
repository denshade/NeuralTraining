
public interface Level
{
    void tick();
    BoardState[][] getContext(int x,int y);
    boolean isGoalReached(int x,int y);
    boolean isInvalid(int x, int y);
}
