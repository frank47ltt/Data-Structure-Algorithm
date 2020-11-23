/************************************************
 *
 * @file knapsack.java
 * @brief This is class include functions:
 *         greedy optimal: take in the parameters read in .txt file
 *         greedyFill method: Find the "packing" that gets the greatest total value possible in
 *         the knapsack without exceeding the weight capacity.
 *         using a tree like recursive method call
 *
 * @author Jingwen Zhang
 * @date November, 23 2020
 *
 ************************************************/

public class optimal {
    private int capacity;
    private int num_things;
    private int[] value;
    private int[] weight;

    //constructor: take in the parameters read in .txt file
    optimal(int capacity, int num_things, int[] value, int[] weight){
        this.capacity = capacity;
        this.num_things = num_things;
        this.value = value;
        this.weight = weight;
    }

    int[] optimalFill(int count, int[] record){
        //if at the leaves of the tree
        if (num_things == count){
            return record;
        }else{
            //copy record to record1 and add another item
                //equivalnet as left child in a tree
                //add weight and value respectively and turn that index on
            int[] record1 = record.clone();
            record1[count+2] = 1;
            record1[0] += weight[count];
            record1[1] += value[count];

            count++;
            //compare the value and return the leaf with larger value
            if(record1[1] > record[1] && record [0] + weight[count-1] < capacity){
                record = optimalFill(count, record1);
            }else{
                record = optimalFill(count,record);
            }
        }
        return record;
    }
}
