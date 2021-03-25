/*
Frank Liu
CSC201 Fall 2020
Programming Assignment 3
Oct 30, 2020
*/
public class RGB {
    private int r;
    private int g;
    private int b;

    // constructors
    public RGB(){
        r = -1;
        g = -1;
        b = -1;
    }
    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // getters and setters
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    // helper methods that can print
    public void print(){
        System.out.print("Red is " + r + " Green is " + g + " Blue is " + b);
    }
}
