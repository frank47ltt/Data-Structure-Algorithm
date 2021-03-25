/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1B
September 14. 2020
 */

public class Teller {

    // member variables
    private Customer customer;
    private boolean isFull;
    private int clock;

    // default constructor
    public Teller(){
        customer = null;
        isFull = false;
        clock = 0;
    }

    // check is Teller has cumstomer in service
    public boolean checkFull(){
        return isFull;
    }

    // arrival of customer to teller
    public void arrivalCust(Customer cust){
        customer = cust;
        isFull = true;
        clock = 0;
    }

    // increment service time for each loop
    public void timePass(){
        clock ++;
    }

    // check if the customer finish the service, if true, then make it avaiable to next customer
    public void checkFinish(){
        if(clock == customer.getServiceTime()){
            isFull = false;
            clock = 0;
            customer = null;
        }
    }
}
