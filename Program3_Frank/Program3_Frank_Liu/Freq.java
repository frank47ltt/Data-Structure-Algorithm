/*
Frank Liu
CSC201 Fall 2020
Programming Assignment 3
Oct 30, 2020
*/
public class Freq {
    private RGB color;
    private int freq;

    public Freq(RGB color, int freq){
        this.color = color;
        this.freq = freq;
    }

    // getters
    public RGB getColor() {
        return color;
    }


    public int getFreq() {
        return freq;
    }

    // methods that increment freq count
    public void incrCount(){
        freq++;
    }

    // methods that help to print
    public void print(){
        color.print();
        System.out.println(" has a freq of " + freq);
    }

    // methods that help to compare and sort
    public int compareTo(Freq f){
        if(freq < f.freq){
            return -1;
        }
        else if(freq == f.freq){
            return 0;
        }
        else{
            return 1;
        }
    }
}
