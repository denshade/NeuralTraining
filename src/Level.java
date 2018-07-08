import java.util.List;

public interface Level
{
    void tick();
    List<BoardState> getContext();

}
