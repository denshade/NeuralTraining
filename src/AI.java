/**
 * Contains the neural network.
 */
public interface AI
{
    MovementDirection predict(BoardState[][] context) throws Exception;

}
