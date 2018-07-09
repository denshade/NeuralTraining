/**
 * This contains the examples as delivered by the trainer.
 */
public class Example
{
    public Example(BoardState[][] board, MovementDirection option, boolean isPositive) {
        this.board = board;
        this.moveDirection = option;
        this.isPositive = isPositive;
    }

    private BoardState[][] board;//Context of the map. Center is the current location. The rest is items.
    private MovementDirection moveDirection;
    private boolean isPositive; //Is it a good idea to move in the moveDirection?


    public boolean isPositive() {
        return isPositive;
    }

    public BoardState[][] getBoardContext() {
        return board;
    }

    public MovementDirection getMoveDirection() {
        return moveDirection;
    }
}
