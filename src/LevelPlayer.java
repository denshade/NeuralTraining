/**
 * This plays an entire level with a specific AI.
 */
public class LevelPlayer
{

    public long MAXTICK = 10000;
    public LevelOutcome runLevel(MovementResolver resolver, Level level) throws Exception {
        int x = 0, y = 0;
        int tick = 0;
        while(tick < MAXTICK)
        {

            MovementDirection option = resolver.runMove(level.getContext(x,y));
            switch(option)
            {
                case UP:  y--;break;
                case DOWN:  y++;break;
                case LEFT:  x--;break;
                case RIGHT:  x++;break;
                default:
                    throw new Exception("Invalid move " +option);
            }
            if (level.isGoalReached(x,y))
            {
                return LevelOutcome.GOAL;
            }
            if (level.isInvalid(x,y)) {
                return LevelOutcome.FAIL;
            }
            level.tick();
            tick++;
        }
        return LevelOutcome.TIMEOUT;

    }

}
