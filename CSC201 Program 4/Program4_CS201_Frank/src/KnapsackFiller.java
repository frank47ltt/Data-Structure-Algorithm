/*
Frank Liu (Tongtong)
CSC201 Fall 2020
Programming Assignment 4
Nov 24, 2020
*/

public class KnapsackFiller {
    private Thing[] items;
    private int numThing;
    private int capacity;

    // constructor
    public KnapsackFiller(int capacity, int numThing, Thing[] items){
        this.capacity = capacity;
        this.numThing = numThing;
        this.items = items;
    }

    // helper bubble sort function, sort the thing object based on their value
    public Thing[] bubbleSort(Thing[] toBeSort){
        for (int i = 0; i < numThing - 1; i++) {
            for (int j = 0; j < numThing - i - 1; j++){
                if (toBeSort[j].getValue() < toBeSort[j + 1].getValue()) {
                    // swap
                    Thing temp = toBeSort[j];
                    toBeSort[j] = toBeSort[j + 1];
                    toBeSort[j + 1] = temp;
                }
            }
        }
        return toBeSort;
    }
    // greedy algorithm: putting the things with biggest value into the bag that
    // do not exceed the capacity
    public void greedyFill(){
        // create local variables
        Answer ans = new Answer();
        // deep copy
        Thing[] tmp = new Thing[numThing];
        for(int i = 0; i < numThing; i++){
            tmp[i] = items[i];
        }
        // sort the tmp
        tmp = bubbleSort(tmp);
        for(int i = 0; i < numThing; i++){
            if((tmp[i].getWeight() + ans.getTotalWeight()) <= capacity){
                // can add the greatest value so for without exceed capacity
                ans.appendItem(tmp[i]);
                ans.incrementValue(tmp[i].getValue());
                ans.incrementWeight(tmp[i].getWeight());
            }
        }
        // print the final result
        System.out.println("\n--------------------------\n");
        System.out.println("Method calls: greedyFill");
        ans.printFinalAnswer();
    }

    // recursive call. Backtracking algorithm to find the "packing" that gets the greatest total value possible in
    // the knapsack without exceeding the weight capacity
    public Answer optimalFillHelper(boolean isTake, int index, Answer ans){
        // deep copy to avoid overwriting local variables
        Answer tmp = new Answer(ans);
        // if true, then take it, add necessary information to the answer object
        if(isTake == true){
            if((items[index].getWeight() + ans.getTotalWeight()) <= capacity){
                tmp.appendItem(items[index]);
                tmp.incrementValue(items[index].getValue());
                tmp.incrementWeight(items[index].getWeight());
            }
            else{
                // set totalValue to -1 if exceed capacity
                tmp.appendItem(items[index]);
                tmp.setTotalValue(-1);
                tmp.incrementWeight(items[index].getWeight());
            }
        }
        // return the answer if it's the leaf node
        if(index == numThing - 1){
            return tmp;
        }
        // increment index
        index++;

        // return the answer with bigger value
        Answer left = optimalFillHelper(true, index, tmp);
        Answer right = optimalFillHelper(false, index, tmp);
        if(left.getTotalValue() > right.getTotalValue()){
            return left;
        }
        else{
            return right;
        }
    }

    // parent function can will be called in the main to initialize the recursive call
    public void optimalFill(){
        Answer ansL = new Answer();
        Answer ansR = new Answer();
        Answer left = optimalFillHelper(true,0, ansL);
        Answer right = optimalFillHelper(false,0, ansR);

        // print out the final result
        if(left.getTotalValue() > right.getTotalValue()){
            System.out.println("\n--------------------------\n");
            System.out.println("Method call: optimalFill");
            left.printFinalAnswer();
        }
        else{
            System.out.println("\n--------------------------\n");
            System.out.println("Method call: optimalFill");
            right.printFinalAnswer();
        }
    }
}
