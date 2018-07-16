import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CLIFirstLevelAITest {
    @Test
    void main() {
        CLIFirstLevelAI flai = new CLIFirstLevelAI();
        flai.main(new String[] {"tom.ai"});
    }

}