import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelPlayerTest {

    @Test
    void runLevel() throws Exception {
        LevelPlayer player = new LevelPlayer();
        player.runLevel(null, null);
    }
}