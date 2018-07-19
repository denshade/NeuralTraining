import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CLIAllExamplesMyAI
{
    public static void main(String[] args)
    {
        if (2 != args.length)
        {
            System.err.println("Usage: CLITrainMyAI <OutputAIFile> <nrOfNodesPerLayer>");
            System.err.println("Where nrOfNodesPerLayer is commaseparated list of integers\n");

            System.exit(-1);
        }

        String outfile = args[0];
        String nrOfLayers = args[1];
        try {
            List<Example> examples = ExampleGenerator.getExamples();
            System.out.println(examples.size());
            EncogAITrainer trainer = new EncogAITrainer();
            List<Integer> ints = Arrays.stream(nrOfLayers.split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            AI ai = trainer.train(examples, ints);
            AIDao dao = new AIDao();
            dao.storeAsFile(ai, outfile);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
