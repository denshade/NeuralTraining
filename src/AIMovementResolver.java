public class AIMovementResolver implements MovementResolver {
    private AI ai;

    public AIMovementResolver(AI ai)
    {
        this.ai = ai;
    }
    @Override
    public MovementDirection runMove(BoardContext context) throws Exception {
        return ai.predict(context);
    }
}
