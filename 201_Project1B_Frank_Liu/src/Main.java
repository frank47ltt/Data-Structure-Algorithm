/* Frank (Tongtong) Liu
CSC201 Fall 2020
Programming Assignment 1 - Program 1B
September 14. 2020
 */

import java.util.Random;

/* This program use a self-implemented Queue class based on ArrayList to simulate people
waiting in line at a bank for service at a teller. This program include three command line arguments,
which are probabilities a customer arrive, number of tellers, and maximum amount of time any customer
need for service.
Notes: When the programs end, this program will dequeue customer out of the Queue if still there's customer existing inside of the Queue, and use
their waitingTimeInQueue up to that point when calculating the average waiting time.
Notes: This program's clock time start at minute 1 and ends at minute 120
 */

public class Main {
    public static void main(String[] args) {
        // declare constant variable first
        final int MAXCLOCK = 120;

        // check if number of command line statements are correct
        if (args.length != 3){
            System.out.println("Please put probability, numTellers, and maxServices as command line input");
            System.exit(0);
        }

        // convert the each command line string to a numeric
        double probabilities = Double.parseDouble(args[0]);
        int numTellers = Integer.parseInt(args[1]);
        int maxService = Integer.parseInt(args[2]);

        // variables declaration
        int i,k,l,m;
        int clockTime;

        int sumOfQLength = 0;
        double averageQLength = 0;    // sumOfQLength / MAXCLOCK
        int maxQLength = 0;
        int timeOfMaxQLength = 0;

        int maxWaitTime = 0;
        double averageWaitTime = 0;     // sumOfWaitTime / totalNumOfCustomer
        int individualWaitTime = 0;
        int sumOfWaitTime = 0;

        int serviceTime;
        int totalNumOfCustomer = 0;

        // object rand to generate random number
        Random rand = new Random();

        // objects of tellers
        Teller[] tellers = new Teller[numTellers];
        for (i = 0; i < numTellers; i++){
            tellers[i] = new Teller();
        }

        // Queue object that stores customer object
        Queue<Customer> lineOfCustomer = new Queue<Customer>();

        // clock starts with one minutes
        for (clockTime = 1; clockTime <= MAXCLOCK; clockTime++){
            // generate a number between 0 and 1
            double randDouble = rand.nextDouble();
            // check if customer will arrive
            // arrive when ranDouble is less than or equal to probabilities
            if (randDouble < probabilities || Math.abs(randDouble - probabilities) < 0.0001){
                // customer arrives
                Customer cust = new Customer();
                totalNumOfCustomer ++;

                // generate number between 1 and maxService but include maxService
                // nextInt() is [0, maxService), I want [1, maxService + 1), which is [1, maxService], so I plus 1
                serviceTime = rand.nextInt(maxService) + 1;
                //length of time needed for service
                cust.setServiceTime(serviceTime);

                // customer wait in line
                cust.setTimeStartimeInQueue(clockTime);  //start point of waiting time
                lineOfCustomer.enqueue(cust);
                System.out.println("Customer arriving in queue. This customer will require " + serviceTime + " mimutes for service.");
            }
            // check if there's any empty Teller
            for (k = 0; k < numTellers; k++){
                if (tellers[k].checkFull() == false){
                    // tellers k is available for service
                    // check if there's customer waiting
                    if (lineOfCustomer.isEmpty() == false){
                        // dequeue a customer out and make it go to the teller
                        Customer cust = lineOfCustomer.dequeue();
                        // calculate wait time for print statements and add it to total time for the final average calculation
                        individualWaitTime = clockTime - cust.getTimeStartInQueue();
                        sumOfWaitTime = sumOfWaitTime + individualWaitTime;

                        // record maxWaitTIme
                        if (individualWaitTime > maxWaitTime){
                            maxWaitTime = individualWaitTime;
                        }
                        tellers[k].arrivalCust(cust);
                        System.out.println("Customer going to teller " + k + ". This customer had to wait in line for " + individualWaitTime + " minutes.");
                    }
                }
            }

            // increment teller's clock time to keep track of remianing time for service
            for (m = 0; m < numTellers; m++){
                // increment teller's own clock time if in service
                if (tellers[m].checkFull()){
                    tellers[m].timePass();
                }
            }

            // check if customer finish and leave
            for (l = 0; l < numTellers; l++) {
                // tellers has a customer
                if (tellers[l].checkFull()) {
                    tellers[l].checkFinish();
                }
            }

            sumOfQLength = sumOfQLength + lineOfCustomer.size();
            if (lineOfCustomer.size() >= maxQLength){
                maxQLength = lineOfCustomer.size();
                timeOfMaxQLength = clockTime;
            }
        }

        // still people inside the queue, adjust the total wait time
        while (lineOfCustomer.size() > 0){
                Customer cust = lineOfCustomer.dequeue();
                individualWaitTime = MAXCLOCK - cust.getTimeStartInQueue();
                sumOfWaitTime = sumOfWaitTime + individualWaitTime;
        }
        // calculating averages and do print out statements
        // cast to double division
        averageQLength = (double) sumOfQLength / MAXCLOCK;
        averageWaitTime = (double) sumOfWaitTime / totalNumOfCustomer;
        System.out.println("Average queue " + averageQLength);
        System.out.println("Max queue " + maxQLength + " at time " + timeOfMaxQLength);
        System.out.println("Max wait " + maxWaitTime);
        System.out.println("Average wait " + averageWaitTime);
    }
}
