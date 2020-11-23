/************************************************
 *
 * @file knapsack.java
 * @brief This is class include functions:
 *         greedy constructor: take in the parameters read in .txt file
 *         greedyFill method: At each step, it chooses to put in the knapsack the thing with the
 *         largest value and a weight that will not exceed the knapsack's remaining capacity.
 *         and return the record of the knapsack in the following format:
 *         weight - value - array of things (0-not in knapsack, 1-in knapsack)
 *
 * @author Jingwen Zhang
 * @date November, 23 2020
 *
 ************************************************/

import java.util.*;
public class greedy {

    private int capacity;
    private int num_things;
    private value_weight[] value_weight;

    //constructor: take in the parameters read in .txt file
    greedy(int capacity, int num_things, int[] value, int[] weight){
        this.capacity = capacity;
        this.num_things = num_things;
        //create a struct to store the value and weight together
        value_weight = new value_weight[num_things];
        for(int i = 0; i < num_things; i++){
            value_weight[i] = new value_weight(value[i], weight[i],i);
        }
        //sort the struct array based on the value
        Arrays.sort(value_weight);
    }

    int[] greedyFill(int count, int[] record) {
        int i = 0;
        //if the weight in knapsack is smaller than capacity
        while (record[0] < capacity) {
            //if the weight after add the item with highest value is within capacity
            if (record[0] + value_weight[i].getWeight() < capacity) {
                record[0] += value_weight[i].getWeight();
                record[1] += value_weight[i].getValue();
                //turn the corresponding index to 1 to record
                record[value_weight[i].getIndex()+2] = 1;
            }
            //else, move to next item
            i++;
            if (i >= 4) {
                break;
            }
        }
        return record;
    }

}


