/**
 * Asks the AI what move to do and executes it.
 */
public interface MovementResolver {

    MovementDirection runMove(BoardState[][] context);

}
