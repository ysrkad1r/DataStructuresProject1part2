package org.example;

import java.util.Random;

public class ArtificialNeuronCell{
    private double target;
    private double output;
    private double WEIGHT_OF_LEARN = 0.05;

    private Random random = new Random();

    private double[] weights = new double[3];

    /**   ----------- CONSTRUCTORS -----------   **/

    // NoArgsConstructor
    public ArtificialNeuronCell(){
        // set weights as 1 or -1 randomly before starting
        for (int i = 0; i<weights.length; i++){
            double randomForWeight = random.nextBoolean() ? 1.0 : -(1.0); //if result true return 1 else -1
            weights[i] = randomForWeight;
        }
    }

    /**   ----------- CALCULATOR MAIN METHODS -----------   **/

    // This function just predicts the rent
    public double predictRent(double numberOfRooms, double distance, double buildingYear){
        double vForSigmoid = numberOfRooms*weights[0] + distance*weights[1] + buildingYear*weights[2];

        return sigmoid(vForSigmoid);
    }

    // second calculation operation before updating weights and return output for optimizing weights
    public double sigmoid(double v){
        double output = 1/(1 + Math.pow(Math.E, -v));
        setOutput(output);
        return output;
    }

    // After predicting output, this function updates weights for better outputs by the difference of target and output and inputs
    public void trainBack(double numberOfRooms, double distance, double buildingYear, double target){
        predictRent(numberOfRooms,distance,buildingYear);

        setTarget(target);

        double[] currentInputs = new double[]{numberOfRooms,distance,buildingYear};

        for (int i = 0; i < weights.length; i++){
            // 'inputs[i]' (yanlış olan) yerine 'currentInputs[i]' (doğru olan) kullanılıyor.
            weights[i] += WEIGHT_OF_LEARN * (getTarget() - getOutput()) * currentInputs[i];
        }
    }


    /**   -----------  GETTER and SETTER METHODS  -----------   **/
    public double getWeight1() {
        return weights[0];
    }

    public void setWeight1(double weight1) {
        this.weights[0] = weight1;
    }

    public double getWeight2() {
        return weights[1];
    }

    public void setWeight2(double weight2) {
        this.weights[1] = weight2;
    }

    public double getWeight3() {
        return weights[2];
    }

    public void setWeight3(double weight3) {
        this.weights[2] = weight3;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getWeightOfLearn() {
        return WEIGHT_OF_LEARN;
    }

    public void setWeightOfLearn(double weightOfLearn) {
        this.WEIGHT_OF_LEARN = weightOfLearn;
    }

}
