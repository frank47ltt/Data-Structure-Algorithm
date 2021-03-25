/*
Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 2
September 27, 2020
*/
// tree class
public class ExpressionTree {
    // member variable is the root of the tree
    private ExpressionTreeNode root;

    // constructors
    ExpressionTree() {
        root = null;
    }

    ExpressionTree(ExpressionTreeNode node) {
        root = node;
    }

    public ExpressionTreeNode getRoot() {
        return root;
    }
    // preOrder traversal will visit node before visit it's children
    public void preOrder(){
        preOrderNode(root);
    }

    public void preOrderNode(ExpressionTreeNode node) {
        if (node != null) {
            System.out.print(node.getExpression() + "  ");
            preOrderNode(node.getLeft());
            preOrderNode(node.getRight());
        }
    }
    // postOrder traversal will visit each node only after visit its children and their subtrees
    public void postOrder(){
        postOrderNode(root);
    }

    public void postOrderNode(ExpressionTreeNode node) {
        if (node != null) {
            postOrderNode(node.getLeft());
            postOrderNode(node.getRight());
            System.out.print(node.getExpression() + "  ");
        }
    }
    // inOrder traversal will first visit left child (including all subtree), and then the node, and then the right child (including all subtree)
    public void inOrder(){
        inOrderNode(root);
    }

    public void inOrderNode(ExpressionTreeNode node) {
        if (node != null) {
            // if is leaf, no parentheses need to be printed
            if (node.isLeaf()){
                System.out.print(node.getExpression());
            }
            // else we need a parentheses
            else {
                System.out.print("(");
                inOrderNode(node.getLeft());
                System.out.print(" " + node.getExpression() + " ");
                inOrderNode(node.getRight());
                System.out.print(")");
            }

        }
    }
    // methods that help us to construct the expression tree
    // retrieve the tree we pop from the stack that contain numeric values
    // and connect the tree with it's node value
    public void insertLeft(ExpressionTree tree) {
        root.setLeft(tree.getRoot());
    }

    public void insertRight(ExpressionTree tree) {
        root.setRight(tree.getRoot());
    }
    // evaluation methods
    public double postOrderEvaluation(){
        return postOrderEvaluationNode(root);
    }

    public double postOrderEvaluationNode(ExpressionTreeNode node) {
        if (node != null) {
            // recursively called the postOrderEvaluation and do different operation with different operators
            if(node.getExpression().equals("+")){
                return postOrderEvaluationNode(node.getLeft()) + postOrderEvaluationNode(node.getRight());
            }
            else if(node.getExpression().equals("-")){
                return postOrderEvaluationNode(node.getLeft()) - postOrderEvaluationNode(node.getRight());
            }
            else if(node.getExpression().equals("*")){
                return postOrderEvaluationNode(node.getLeft()) * postOrderEvaluationNode(node.getRight());
            }
            else if(node.getExpression().equals("/")){
                return postOrderEvaluationNode(node.getLeft()) / postOrderEvaluationNode(node.getRight());
            }
            // if node is numeric value, we return it so it can be passed to previous called and make calculation
            else{
                double v = Double.parseDouble(node.getExpression());
                return v;
            }
        }
        // empty tree
        else{
            return 0;
        }
    }
}








