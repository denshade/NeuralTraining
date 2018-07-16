
import java.io.*;

public class AIDao
{
    public void storeAsFile(AI ai, final String filename) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(filename)));

        stream.writeObject(ai);
        stream.close();
    }
    public AI loadFromFile(final String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File(filename)));
        AI ai = (AI) stream.readObject();
        return ai;
    }

}
