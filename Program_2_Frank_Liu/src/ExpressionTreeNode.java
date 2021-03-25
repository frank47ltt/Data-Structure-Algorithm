/*
Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 2
September 27, 2020
*/
// tree node class
public class ExpressionTreeNode {
    // a tree node will have a string inside with two children (reference) node
    private String expression;
    private ExpressionTreeNode left;
    private ExpressionTreeNode right;

    // constructors
    ExpressionTreeNode(){
        left = null;
        right = null;
    }
    ExpressionTreeNode(String exp){
        expression = exp;
        left = null;
        right = null;
    }
    ExpressionTreeNode(String exp, ExpressionTreeNode l, ExpressionTreeNode r){
        expression = exp;
        left = l;
        right = r;
    }

    // getters and setters
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ExpressionTreeNode getLeft() {
        return left;
    }

    public void setLeft(ExpressionTreeNode left) {
        this.left = left;
    }

    public ExpressionTreeNode getRight() {
        return right;
    }

    public void setRight(ExpressionTreeNode right) {
        this.right = right;
    }

    // bool method to check is leaf
    public boolean isLeaf(){
        if (left == null && right == null){
            return true;
        }
        else{
            return false;
        }
    }
}
