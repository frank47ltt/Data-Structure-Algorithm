/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1A
September 14, 2020
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/*
This program used the Stack class from Java API and file stream to read
from a text file to evaluate the numeric value of a list of postfix expression
 */

public class Main {
    public static void main(String[] args) throws IOException {

        // create an instance of an Expression in main
        Expression expr = new Expression();
        // create input stream and Scanner object
        System.out.println("Open the exprs.txt file...");
        FileInputStream fis = new FileInputStream("src/exprs.txt");
        Scanner in = new Scanner(fis);

        // loop to end of the file
        while (in.hasNext()){
            String profix = in.nextLine();
            System.out.print(profix + " ");
            // call evaluate method to get answer
            double ans = expr.evaluate(profix);
            System.out.println("=  " + ans);
        }
        System.out.println("Close the exprs.txt file...");
        fis.close();
    }
}
