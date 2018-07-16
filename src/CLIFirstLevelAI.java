import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CLIFirstLevelAI
{
    public static void main(String[] args)
    {
        if (1 != args.length)
        {
            System.err.println("Usage: CLIFirstLevelAI <InputAIFile>");

            System.exit(-1);
        }

        String inputfile = args[0];
        try {
            AIDao dao = new AIDao();
            AI ai = dao.loadFromFile(inputfile);
            LevelPlayer player = new LevelPlayer();
            switch (player.runLevel(new AIMovementResolver(ai), new FirstLevel()))
            {
                case FAIL:System.out.println("FAIL"); break;
                case TIMEOUT: System.out.println("TIMEOUT"); break;
                case GOAL: System.out.println("GOAL!"); break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
