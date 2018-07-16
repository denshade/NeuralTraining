import org.junit.jupiter.api.Test;

import java.io.CharArrayReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CLITrainMyAITest {

    @Test
    void getExamplesFromFile() throws Exception {
        List<Example> examples = CLITrainMyAI.getExamplesFromFile(new CharArrayReader("N,N,N;B,B,B;W,W,W\n0.0,0.0,0.0,0.0".toCharArray()));
        assertEquals(4, examples.size());
    }


    @Test
    void getExamplesFromFile2() throws Exception {
        List<Example> examples = CLITrainMyAI.getExamplesFromFile(new CharArrayReader("N,N,N;B,B,B;W,W,W\n0.0,0.0,0.0,0.0\nN,N,N;B,B,B;W,W,W\n0.0,0.0,0.0,0.0".toCharArray()));
        assertEquals(8, examples.size());
    }
    @Test
    void runTrainer() throws Exception {
        CLITrainMyAI.main(new String[] {"testfiles\\examples.csv", "tom.ai", "4,3,3"});
    }
}