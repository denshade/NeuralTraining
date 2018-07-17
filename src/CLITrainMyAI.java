import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CLITrainMyAI
{
    public static void main(String[] args)
    {
        if (3 != args.length)
        {
            System.err.println("Usage: CLITrainMyAI <Examples CSV> <OutputAIFile> <nrOfNodesPerLayer>");
            System.err.println("Where nrOfNodesPerLayer is commaseparated list of integers\n");

            System.err.println("Where CSV is: N,N,N;B,B,B;W,W,W\n"+
                    "weightLeft,weightRight,weightup,weightdown" +
                    "<repeat>");
            System.exit(-1);
        }

        String outfile = args[1];
        String nrOfLayers = args[2];
        try {
            List<Example> examples = getExamplesFromFile(new FileReader(new File(args[0])));

            EncogAITrainer trainer = new EncogAITrainer();
            List<Integer> ints = Arrays.stream(nrOfLayers.split(",")).map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            AI ai = trainer.train(examples, ints);
            AIDao dao = new AIDao();
            dao.storeAsFile(ai, outfile);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static List<Example> getExamplesFromFile(final Reader fileReader) throws Exception {
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> textLines = new ArrayList<>();
        String readLine = reader.readLine();
        List<Example> examples = new ArrayList<>();
        while (readLine != null)
        {
            textLines.add(readLine);
            readLine = reader.readLine();
        }
        reader.close();
        int currentLine = 0;
        while (currentLine < textLines.size())
        {
            String contextString = textLines.get(currentLine++);
            BoardContext context = BoardContextLoader.loadFromString(contextString,3);
            String weights = textLines.get(currentLine++);
            String[] weightsArray = weights.split(",");
            Double weightLeft = Double.parseDouble(weightsArray[0]);
            Double weightRight = Double.parseDouble(weightsArray[1]);
            Double weightUp = Double.parseDouble(weightsArray[2]);
            Double weightDown = Double.parseDouble(weightsArray[3]);
            Example exampleLeft = new Example(context, MovementDirection.LEFT, weightLeft);
            Example exampleRight = new Example(context, MovementDirection.RIGHT, weightRight);
            Example exampleUp = new Example(context, MovementDirection.UP, weightUp);
            Example exampleDown = new Example(context, MovementDirection.DOWN, weightDown);
            examples.add(exampleLeft);
            examples.add(exampleRight);
            examples.add(exampleUp);
            examples.add(exampleDown);
        }
        return examples;

    }
}
