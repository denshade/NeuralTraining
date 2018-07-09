import org.encog.neural.networks.BasicNetwork;

public class EncogAI implements AI
{
    private final BasicNetwork right;
    private final BasicNetwork top;
    private final BasicNetwork bottom;
    private final BasicNetwork left;

    public EncogAI(BasicNetwork left, BasicNetwork right, BasicNetwork top, BasicNetwork bottom)
    {

        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public MovementDirection predict(BoardState context)
    {
        //todo
        return MovementDirection.DOWN;
    }
}
