/*
Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 2
September 27, 2020
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
/*
This program reads in postfix expression using file stream and implements an expression tree and
print it out using preOrder, inOrder, and postOrder methods. It also use a modified version of
postOrder to evaluate the expression tree recursively and print out the result.
 */

public class Main {

    public static void processExpr(String expr){
        // final expression tree
        ExpressionTree expressionTree = null;
        // a stack of expression tree
        Stack<ExpressionTree> stack = new Stack<ExpressionTree>();
        // use String stream to look at each element of the string
        Scanner inSS = new Scanner(expr);
        while(inSS.hasNext()){
            String toCheck = inSS.next();
            // situations where an operator is found
            if (toCheck.equals("+") || toCheck.equals("-") || toCheck.equals("*") || toCheck.equals("/")) {
                // create a node and make an expression tree by setting the node as root
                ExpressionTreeNode node = new ExpressionTreeNode(toCheck);
                ExpressionTree newTree = new ExpressionTree(node);
                // then called methods in tree class to create left and right child of the tree from what was pop out from stack
                newTree.insertRight(stack.pop());
                newTree.insertLeft(stack.pop());
                // push the new tree back
                stack.push(newTree);
            }
            // situations when element is an numeric
            else {
                // create a single node tree and push to stack
                ExpressionTreeNode node = new ExpressionTreeNode(toCheck);
                stack.push(new ExpressionTree(node));
            }
        }
        // end of the while loop means the only thing in the stack is the final expression tree
        expressionTree = stack.pop();
        // print out the content of tree using three different traversal methods
        System.out.println("Print the tree in preOrder");
        expressionTree.preOrder();
        System.out.println("\nPrint the tree in postOrder");
        expressionTree.postOrder();
        System.out.println("\nPrint the tree in inOrder");
        expressionTree.inOrder();
        System.out.println("\nThe answer for this expression is...... ");
        /* Debug:
        postOrder is the same order with text file
        inorder is the order of logical calculation
         */
        expressionTree.inOrder();
        // called postOrderEvaluation to print out the result
        System.out.println(" = " + expressionTree.postOrderEvaluation() + "\n");

    }
    public static void main(String[] args) throws IOException {
        // create input stream and Scanner object
        System.out.println("Open the exprs.txt file...");
        FileInputStream fis = new FileInputStream("src/exprs.txt");
        Scanner in = new Scanner(fis);

        // loop to end of the file
        while (in.hasNext()){
            String profix = in.nextLine();
            System.out.println(profix + " ");
            // call the processExpr method to finish this program
            processExpr(profix);
        }
        System.out.println("Close the exprs.txt file...");
        fis.close();
    }
}
