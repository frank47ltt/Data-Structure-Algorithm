/*
Frank Liu (Tongtong)
CSC201 Fall 2020
Programming Assignment 4
Nov 24, 2020
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
This program use the idea of backtracking to solve the knapsack problem to find the "packing"
that gets the greatest total value possible in the knapsack without exceeding the weight capacity.
The program also implement greedy algorithm for knapsack problem
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // read data from file and assign them to corresponding variables
        FileInputStream fis = new FileInputStream("src/knapsack.txt");
        Scanner in = new Scanner(fis);
        int capacity = in.nextInt();
        int numThing = in.nextInt();
        Thing[] thingList = new Thing[numThing];
        for(int i = 0; i < numThing; i++){
            thingList[i] = new Thing();
            thingList[i].setWeight(in.nextInt());
        }
        for(int j = 0; j < numThing; j++){
            thingList[j].setValue(in.nextInt());
        }
        // print out the list of input
        System.out.println("Printing data from the file.... \n");
        if(numThing < 15){
            for(int i = 0; i < numThing; i++){
                thingList[i].print();
            }
        }
        // create nf instance, pass in necessary information
        KnapsackFiller nf = new KnapsackFiller(capacity, numThing, thingList);

        // call two method to finish the problem
        nf.greedyFill();
        nf.optimalFill();
    }
}
