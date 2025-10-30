package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static String filePath = "EgitimSeti.txt";
    private static Map<Integer, double[]> trainDataSet = new HashMap<>();

    public static void main(String[] args) {
        ArtificialNeuronCell neuronCell = new ArtificialNeuronCell();

        //Neuron without building age
        ArtificialNeuronCell2 neuronCell2 = new ArtificialNeuronCell2();

        int testDataNum = 5;
        int epochNum = 25; // Set to 25 epochs as requested
        //int epochNum = 100;

        // get train datas from txt
        readDataSet(filePath);

        int trainDataCount = trainDataSet.size() - testDataNum; // This will be 15
/***
        // for epochNum times train and updating weights
        for (int k = 0; k < epochNum ; k++) {

            // Looping the train data (first 15 data)
            for (int i = 1 ; i<= trainDataCount ; i++){
                double[] inputs = trainDataSet.get(i);
                neuronCell.trainBack(inputs[0],inputs[1],inputs[2],inputs[3]);
            }

            if ((k + 1) % 5 == 0) {
                System.out.println("Epoch " + (k + 1) + " completed.");
            }
        }


         * NEURON WITHOUT BUILDING AGE
 ***/
        for (int k = 0; k < epochNum ; k++) {

            // Looping the train data (first 15 data)
            for (int i = 1 ; i<= trainDataCount ; i++){
                double[] inputs = trainDataSet.get(i);
                neuronCell2.trainBack(inputs[0],inputs[1],inputs[3]);
            }

            if ((k + 1) % 5 == 0) {
                System.out.println("Epoch " + (k + 1) + " completed.");
            }
        }



        System.out.println("Training completed (25 epochs).");
        System.out.println();


        // Calculating of MSE Error

        System.out.println("--- Training Data Error Calculation (First 15 samples) ---");
        System.out.println("Inputs (Room, Dist) | Target (Norm) | Predicted (Norm)");
        System.out.println("------------------------------------------------------------------");

        double totalSquaredError = 0.0; // Initialize for MSE calculation
/***
        // Loop over the TRAINING data (first 15) one more time
        for (int i = 1; i <= trainDataCount; i++) {
            double[] inputs = trainDataSet.get(i);

            double room = inputs[0];
            double dist = inputs[1];
            double age = inputs[2];
            double normalizedTarget = inputs[3];

            // Get prediction WITHOUT training
            double normalizedPredict = neuronCell.predictRent(room, dist, age);

            System.out.printf("(%.1f, %.3f, %.2f)      |    %.4f   |    %.4f\n",
                    room, dist, age, normalizedTarget, normalizedPredict);

            // Sum the squared error for MSE calculation
            double error = normalizedTarget - normalizedPredict;
            totalSquaredError += error * error; // square's of error
        }
         ***/



        for (int i = 1; i <= trainDataCount; i++) {
            double[] inputs = trainDataSet.get(i);

            double room = inputs[0];
            double dist = inputs[1];
            double normalizedTarget = inputs[3];

            // Get prediction WITHOUT training
            double normalizedPredict = neuronCell2.predictRent(room, dist);

            System.out.printf("(%.1f, %.3f)      |    %.4f   |    %.4f\n",
                    room, dist, normalizedTarget, normalizedPredict);

            // Sum the squared error for MSE calculation
            double error = normalizedTarget - normalizedPredict;
            totalSquaredError += error * error; // square's of error
        }


        System.out.println("------------------------------------------------------------------");

        // Calculate and print the final MSE
        double mse = totalSquaredError / trainDataCount;
        System.out.printf("\nTraining Data MSE (Mean Square Error): %.8f\n", mse);
        System.out.println(); // Added for spacing


        // Test with last 5 datas

        System.out.println("--- Test Data Results (Last 5 samples) ---");
        // Print table headers for the report
        System.out.println("Data # | Target Rent | Predicted Rent");
        System.out.println("------------------------------------------");


        // total sum variable
        double testTotalSquaredError = 0.0;
/***
        for (int i = trainDataCount + 1;  i <= trainDataSet.size(); i++){
            double[] inputs = trainDataSet.get(i);

            double numOfRoom = inputs[0];
            double distance = inputs[1];
            double buildingYear = inputs[2];

            double normalizedTarget = inputs[3];
            double normalizedPredict = neuronCell.predictRent(numOfRoom,distance,buildingYear);

            // De-normalize operation by multiply 10000
            double deNormalizedTarget = Math.round(normalizedTarget * 10000);
            double deNormalizedPredict = Math.round(normalizedPredict * 10000);

            System.out.printf("Data #%d |    %.0f     |    %.0f\n",
                    i, deNormalizedTarget, deNormalizedPredict);

            // sum squared error
            double error = normalizedTarget - normalizedPredict;
            testTotalSquaredError += error * error;
        }
         ***/



        for (int i = trainDataCount + 1;  i <= trainDataSet.size(); i++){
            double[] inputs = trainDataSet.get(i);

            double numOfRoom = inputs[0];
            double distance = inputs[1];

            double normalizedTarget = inputs[3];
            double normalizedPredict = neuronCell2.predictRent(numOfRoom,distance);

            // De-normalize operation by multiply 10000
            double deNormalizedTarget = Math.round(normalizedTarget * 10000);
            double deNormalizedPredict = Math.round(normalizedPredict * 10000);

            System.out.printf("Data #%d |    %.0f     |    %.0f\n",
                    i, deNormalizedTarget, deNormalizedPredict);

            // sum squared error
            double error = normalizedTarget - normalizedPredict;
            testTotalSquaredError += error * error;
        }


        System.out.println("------------------------------------------");

        // Calculate and print the final Test MSE
        double testMse = testTotalSquaredError / testDataNum;
        System.out.printf("\nTest Data MSE (Mean Square Error): %.8f\n", testMse);

    }


    // This method reads datas from "EgitimSeti.txt" and put them Map
    public static void readDataSet(String filePath){
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] rawTrainData = line.split(",");

                int key = Integer.parseInt(rawTrainData[0]);
                double[] trainData = new double[rawTrainData.length-1];

                for (int i = 0; i < trainData.length ; i++) {
                    trainData[i] = Double.parseDouble(rawTrainData[i+1]);
                }

                trainDataSet.put(key,trainData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}