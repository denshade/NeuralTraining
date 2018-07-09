import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

import java.util.List;

public class EncogAITrainer
{
    public AI train(final List<Example> examples, List<Integer> nrOfNodesPerLayer) throws Exception
    {
        if (examples.size() < 1) throw new IllegalArgumentException("At least one example should be provided");
        if (nrOfNodesPerLayer.size() < 1) throw new IllegalArgumentException("At least one layer should be provided");
        int size = examples.get(0).getBoardContext()[0].length * examples.get(0).getBoardContext().length;

        BasicNetwork moveRightNetwork = getBasicNetwork(nrOfNodesPerLayer, size);
        BasicNetwork moveLeftNetwork = getBasicNetwork(nrOfNodesPerLayer, size);
        BasicNetwork moveTopNetwork = getBasicNetwork(nrOfNodesPerLayer, size);
        BasicNetwork moveBottomNetwork = getBasicNetwork(nrOfNodesPerLayer, size);
        MLDataSet upTrainingset = createTrainingSet(examples, MovementDirection.UP);
        MLDataSet downTrainingset = createTrainingSet(examples, MovementDirection.DOWN);
        MLDataSet leftTrainingset = createTrainingSet(examples, MovementDirection.LEFT);
        MLDataSet rightTrainingset = createTrainingSet(examples, MovementDirection.RIGHT);

        trainNetwork(moveRightNetwork, rightTrainingset);
        trainNetwork(moveLeftNetwork, leftTrainingset);
        trainNetwork(moveTopNetwork, upTrainingset);
        trainNetwork(moveBottomNetwork, downTrainingset);
        return new EncogAI(moveLeftNetwork, moveRightNetwork, moveTopNetwork, moveBottomNetwork);
    }

    private void trainNetwork(BasicNetwork moveRightNetwork, MLDataSet upTrainingset) {
        final Backpropagation train = new Backpropagation(moveRightNetwork, upTrainingset);

        int epoch = 1;

        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while(train.getError() > 0.07);
        train.finishTraining();
    }

    private MLDataSet createTrainingSet(List<Example> examples, MovementDirection direction) throws Exception {
        MLDataSet trainingset = new BasicMLDataSet();
        for (Example example : examples)
        {
            if (!example.getMoveDirection().equals(direction)) {
                continue;
            }
            trainingset.add(new BasicMLData(BoardContextToVector.convertToDouble(example.getBoardContext())), new BasicMLData(new double[]{example.getScore()}));
        }
        return trainingset;
    }

    private BasicNetwork getBasicNetwork(List<Integer> nrOfNodesPerLayer, int size) {
        BasicNetwork moveRightNetwork = new BasicNetwork();
        moveRightNetwork.addLayer(new BasicLayer(null,true,size));//The input layer is the context size.
        for (int i = 0; i < nrOfNodesPerLayer.size(); i++)
        {
            moveRightNetwork.addLayer(new BasicLayer(new ActivationTANH(),true,nrOfNodesPerLayer.get(i)));
        }
        moveRightNetwork.addLayer(new BasicLayer(new ActivationSigmoid(), true, 1)); // The output layer predicts
        moveRightNetwork.getStructure().finalizeStructure();
        moveRightNetwork.reset();
        return moveRightNetwork;
    }



}
