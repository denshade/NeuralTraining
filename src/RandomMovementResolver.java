import java.util.Random;

public class RandomMovementResolver implements MovementResolver
{
    @Override
    public MovementDirection runMove(BoardState[][] context) {
        Random r = new Random();
        int optionNum = r.nextInt(MovementDirection.values().length);
        return MovementDirection.values()[optionNum];
    }
}
