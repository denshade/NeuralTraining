import org.encog.neural.networks.BasicNetwork;

import java.util.Arrays;
import java.util.Collections;

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

    public MovementDirection predict(BoardContext context) throws Exception {
        double[] leftResult = new double[1];
        double[] bottomResult = new double[1];
        double[] rightResult = new double[1];
        double[] topResult = new double[1];
        left.compute(BoardContextToVector.convertToDouble(context), leftResult);
        right.compute(BoardContextToVector.convertToDouble(context), rightResult);
        top.compute(BoardContextToVector.convertToDouble(context), topResult);
        bottom.compute(BoardContextToVector.convertToDouble(context), bottomResult);
        System.out.println("<- " + leftResult[0] + " -> " + rightResult[0] + " V"  + bottomResult[0] + " ^" + topResult[0]);
        double bestResult = Collections.max(Arrays.asList(leftResult[0], rightResult[0], bottomResult[0], topResult[0]));
        if (leftResult[0] == bestResult) return MovementDirection.LEFT;
        if (rightResult[0] == bestResult) return MovementDirection.RIGHT;
        if (topResult[0] == bestResult) return MovementDirection.UP;
        return MovementDirection.DOWN;
    }
}
