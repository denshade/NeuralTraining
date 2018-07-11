/**
 * This contains the examples as delivered by the trainer.
 */
public class Example
{
    public Example(BoardContext board, MovementDirection option, double score) throws Exception {
        this.board = board;
        this.moveDirection = option;
        this.score = score;
        if (score > 1) throw new Exception("Invalid score");
        if (score < 0) throw new Exception("Invalid score");

    }

    private BoardContext board;//Context of the map. Center is the current location. The rest is items.
    private MovementDirection moveDirection;
    private double score; //Is it a good idea to move in the moveDirection? 0 is no, +1 is a supergood idea.

    public BoardContext getBoardContext() {
        return board;
    }

    public MovementDirection getMoveDirection() {
        return moveDirection;
    }

    public double getScore() {
        return score;
    }
}
