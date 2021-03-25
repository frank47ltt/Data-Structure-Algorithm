/*
Frank Liu (Tongtong)
CSC201 Fall 2020
Programming Assignment 4
Nov 24, 2020
*/

import java.util.ArrayList;

public class Answer {
    private int totalWeight;
    private int totalValue;
    private ArrayList<Thing> ans;

    public Answer(){
        totalWeight = 0;
        totalValue = 0;
        ans = new ArrayList<>();
    }
    // copy constructor to ensure deep copy
    public Answer(Answer ans){
        this(ans.getTotalWeight(),ans.getTotalValue(), ans.getAns());
    }

    // constructor
    public Answer(int totalWeight, int totalValue, ArrayList<Thing> ans) {
        this.totalWeight = totalWeight;
        this.totalValue = totalValue;
        // ensure the deep copy of arrayList object
        this.ans = new ArrayList<>();
        for(int i = 0; i < ans.size(); i++){
            Thing newThing = new Thing(ans.get(i));
            this.ans.add(newThing);
        }
    }

    // increment weight
    public void incrementWeight(int weight){
        totalWeight += weight;
    }
    // add thing object to answer
    public void appendItem(Thing thing){
        ans.add(thing);
    }
    // increment value
    public void incrementValue(int value){
        totalValue += value;
    }
    // setter and getter
    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public ArrayList<Thing> getAns() {
        return ans;
    }

    // function that print out the final answer
    // will be called in kf class
    public void printFinalAnswer(){
        if(ans.size() < 15){
            for(int i = 0; i < ans.size(); i++){
                ans.get(i).print();
            }
        }
        System.out.println("Total weight is " + totalWeight);
        System.out.println("Total value is " + totalValue);
    }

}
