package org.example;

import java.util.Random;

public class ArtificialNeuronCell{

    private double numberOfRooms;
    private double distance;
    private double buildingYear;

    private double target;
    private double output;
    private double WEIGHT_OF_LEARN = 0.05;

    private double sigmoidsV;

    private Random random = new Random();

    private double[] weights = new double[3];
    private double[] inputs = new double[]{ numberOfRooms, distance, buildingYear};

    /**   ----------- CONSTRUCTORS -----------   **/

    // AllArgsConstructor
    public ArtificialNeuronCell(double numberOfRooms, double distance, double buildingYear){
        setNumberOfRooms(numberOfRooms);
        setDistance(distance);
        setBuildingYear(buildingYear);
        updateInputsList();

        // set weights as 1 or -1 randomly before starting
        for (int i = 0; i<weights.length; i++){
            double randomForWeight = random.nextBoolean() ? 1.0 : -(1.0); //if result true return 1 else -1
            weights[i] = randomForWeight;
        }
    }

    // NoArgsConstructor
    public ArtificialNeuronCell(){
        setNumberOfRooms(1.0);
        setDistance(0.0);
        setBuildingYear(2000);
        updateInputsList();

        // set weights as 1 or -1 randomly before starting
        for (int i = 0; i<weights.length; i++){
            double randomForWeight = random.nextBoolean() ? 1.0 : -(1.0); //if result true return 1 else -1
            weights[i] = randomForWeight;
        }
    }

    /**
     *
     *
     *
     * FOR READIBILITY AND BETTER CLASSIFICATION OF OPERATION WRITE THESE METHODS BELOW:
     * -------------------------------
     * predictRent(inputs)
     * trainBack(inputs, target)
     *
     *
     *
     * **/


    /**   ----------- CALCULATOR MAIN METHODS -----------   **/

    // second calculation operation before updating weights and return output for optimizing weights
    public double sigmoid(double v){
        double output = 1/(1 + Math.pow(Math.E, -v));
        setOutput(output);
        return output;
    }

    // first calculation operation before sigmoid to extraxt v
    public double calculateV(double numberOfRooms, double distance, double buildingYear, double target){

        // calculate v for sigmoid function
        double vForSigmoid = numberOfRooms*weights[0] + distance*weights[1] + buildingYear*weights[2];
        // set target for update weights
        setTarget(target);
        // set output after sigmoid function
        sigmoid(vForSigmoid);
        // after setting of target and output values update weights for better results and optimizing
        updateWeights();
        // set v for sigmoid function (it may be unneccessary I will decide.)
        setSigmoidsV(vForSigmoid);

        return vForSigmoid;
    }

    // third and last operation to update weights and optimize outputs of function
    // for more accurate output values
    private void updateWeights(){
        for (int i = 0; i<weights.length; i++){
            weights[i] += WEIGHT_OF_LEARN * (getTarget() - getOutput()) * inputs[i];
        }
    }


    /**   ----------- HELPER METHODS -----------   **/

    private void updateInputsList(){
        inputs[0] = getnumberOfRooms();
        inputs[1] = getDistance();
        inputs[2] = getBuildingYear();
    }


    /**   -----------  GETTER and SETTER METHODS  -----------   **/

    public double getnumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getBuildingYear() {
        return buildingYear;
    }

    public void setBuildingYear(double buildingYear) {
        this.buildingYear = buildingYear;
    }

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

    public double getSigmoidsV() {
        return sigmoidsV;
    }

    public void setSigmoidsV(double sigmoidsV) {
        this.sigmoidsV = sigmoidsV;
    }
}
