/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1B
September 14. 2020
 */

import java.util.ArrayList;

// template class
public class Queue <T> {

    // private variables
    private ArrayList<T> myQueue = new ArrayList<T>();

    // methods
    public void enqueue(T obj){
        //ArrayList add method add object to the end of the list so directly use it for Queue
        myQueue.add(obj);
    }

    public T dequeue(){
        // remove from head
        // remove the first element of ArrayList
        T obj = myQueue.get(0);
        myQueue.remove(0);

        return obj;
    }
    // return if the queue is empty
    public boolean isEmpty(){
        return myQueue.isEmpty();
    }
    // return the size of the queue
    public int size(){
        return myQueue.size();
    }

}
