/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1A
September 14, 2020
 */

import java.util.Scanner;
import java.util.Stack;


public class Expression {

    // member variable, but don't necessary needed for this assignment
    private String expressionFromFile;

    // good practice to always have default constructor
    public Expression(){
        expressionFromFile = "-1";
    }

    // this methods take a string of postfix expresion and evaluate it to the final numeric value
    public double evaluate(String s){
        double result = 0;
        Stack<Double> stack = new Stack<Double>();
        // use String stream to look at each element of the string
        Scanner inSS = new Scanner(s);
        while(inSS.hasNext()){
            String toCheck = inSS.next();
            // situations where an operator is found
            if (toCheck.equals("+")){
                double RHS = stack.pop();
                double LFS = stack.pop();
                double ans = LFS + RHS;
                stack.push(ans);
            }
            else if(toCheck.equals("-")){
                double RHS = stack.pop();
                double LFS = stack.pop();
                double ans = LFS - RHS;
                stack.push(ans);
            }
            else if(toCheck.equals("*")){
                double RHS = stack.pop();
                double LFS = stack.pop();
                double ans = LFS * RHS;
                stack.push(ans);
            }
            else if(toCheck.equals("/")){
                double RHS = stack.pop();
                double LFS = stack.pop();
                double ans = LFS / RHS;
                stack.push(ans);
            }
            // situations when element is an numeric
            else {
                try {
                    // if is numeric, convert the string to a double and push to the stack
                    double v = Double.parseDouble(toCheck);
                    stack.push(v);
                }
                catch (NumberFormatException nfe) {
                    System.out.println("File content not correct. Neither an operator nor operand is found");
                }
            }
        }
        // end of the while loop means the only thing in the stack is the answer
        result = stack.pop();
        return result;
    }
}
