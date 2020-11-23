/************************************************
 *
 * @file knapsack.java
 * @brief This is class that print out the data you read in from ​knapsack.txt.​
 *        call each of your fill methods from ​main.
 * @author Jingwen Zhang
 * @date September 6, 2020
 *
 ************************************************/

import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class knapsack {

    public static void main(String[] agrs)throws Exception {

        Scanner Scnr = new Scanner(System.in);

        System.out.println("Please enter the file path of exprs.txt");
        System.out.println("Prompt: /Users/zhangjingwen/IdeaProjects/CSC201/prog4_knapsack/src/knapsack.txt");
        System.out.println();

        //String filepath = Scnr.nextLine();
        String filepath = "/Users/zhangjingwen/IdeaProjects/CSC201/prog4_knapsack/src/knapsack.txt";

        //read the file typed in by the user
        File kp = new File(filepath);
        Scanner scnr = new Scanner(kp);

        int capacity = 0;
        int num_things = 0;
        int count = 0;

        if (scnr.hasNextLine()){
            capacity = Integer.parseInt(scnr.nextLine());
            num_things = Integer.parseInt(scnr.nextLine());
        }

        int[] value = new int[num_things];
        int[] weight = new int[num_things];

        int[] record = new int[num_things + 2];
        int[] greedy_result = new int[num_things + 2];
        int[] optimal_result = new int[ num_things + 2];

        //initalize all arrays to zero
        for(int i = 0; i < record.length; i++){
            record[i] = 0;
            greedy_result[i] = 0;
            optimal_result[i] = 0;
        }

        /*
        while (scnr.hasNextLine()) {
            count++;
        }
        if (count != num_things*2){

            return;
        }
        scnr.close();
        kp = new File(filepath);
        scnr = new Scanner(kp);
        */
        //print out if the list is shorter than 15 elements
        if (num_things < 15)
            System.out.println("The weight of each item: ");

        for (int i = 0; i < num_things; i++){
            weight[i] = Integer.parseInt(scnr.nextLine());
            if (num_things < 15){
                System.out.println(weight[i]);
            }
        }

        if (num_things < 15)
            System.out.println("The value of each item: ");
        for (int i = 0; i < num_things; i++){
            value[i] = Integer.parseInt(scnr.nextLine());
            if (num_things < 15){
                System.out.println(value[i]);
            }
        }
        //call greedy method
        greedy kp1 = new greedy(capacity, num_things, value, weight);
        greedy_result = kp1.greedyFill(count, record);
        for(int i = 0; i < greedy_result.length; i++){
            System.out.print(record[i] + ", ");
        }

        System.out.println();
        System.out.println("Method: Greedy version");
        System.out.println("Knapsack's weight: " + greedy_result[0]);
        System.out.println("Knapsack's value: " + greedy_result[1]);
        System.out.print("Things in the knapsack: ");

        for(int i = 2; i < greedy_result.length; i++){
            if (greedy_result[i] > 0)
            System.out.print(i - 1 + ", ");
        }
        //call optimal method
        optimal kp2 = new optimal(capacity, num_things, value, weight);
        optimal_result = kp2.optimalFill(count, optimal_result);

        System.out.println();
        System.out.println();
        System.out.println("Method: Optimal version");
        System.out.println("Knapsack's weight: " + optimal_result[0]);
        System.out.println("Knapsack's value: " + optimal_result[1]);
        System.out.print("Things in the knapsack: ");

        for(int i = 2; i < optimal_result.length; i++){
            if (optimal_result[i] > 0)
                System.out.print(i - 1 + ", ");
        }
        scnr.close();
    }
}
