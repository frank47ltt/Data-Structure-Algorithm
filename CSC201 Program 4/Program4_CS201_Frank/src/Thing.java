/*
Frank Liu (Tongtong)
CSC201 Fall 2020
Programming Assignment 4
Nov 24, 2020
*/

public class Thing {
    private int weight;
    private int value;

    // default constructor

    public Thing(){
        weight = 0;
        value = 0;
    }

    // copy constructor
    public Thing(Thing th){
        this(th.getWeight(),th.getValue());
    }

    public Thing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    // getter and setter
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
    // helper function to print
    public void print(){
        System.out.println("Thing has a weight of " + weight + " and a value of " + value);
    }
}
