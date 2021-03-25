/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1B
September 14. 2020
 */

public class Customer {

    // variables keep track of the service time needed and time waited in queue
    private int serviceTime;
    private int timeStartInQueue;

    // default constructor
    public Customer(){
        serviceTime = 0;
        timeStartInQueue = 0;
    }

    // getters and setters
    public void setServiceTime(int time){
        serviceTime = time;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getTimeStartInQueue(){
        return timeStartInQueue;
    }

    public void setTimeStartimeInQueue(int time){
        timeStartInQueue = time;
    }
}
