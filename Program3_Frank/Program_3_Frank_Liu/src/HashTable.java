/*
Frank Liu
CSC201 Fall 2020
Programming Assignment 3
Oct 30, 2020
*/
public class HashTable {
    private int arraySize;
    private Freq[] ht;
    private int actualSize;
    private int numCollision;
    private int numRehash;

    // constructors
    public HashTable(){
        arraySize = 0;
        actualSize = 0;
        numCollision = 0;
        numRehash = 0;
        ht = null;
    }

    public HashTable(int size){
        arraySize = size;
        actualSize = 0;
        numCollision = 0;
        numRehash = 0;
        ht = new Freq[arraySize];
    }
    // methods that check for half full and if true, rehash the original table to a greater size
    public boolean isHalfFull(){
        if(actualSize >= (arraySize / 2)){
            return true;
        }
        else{
            return false;
        }
    }
    // check if a given input number is a prime
    // retrieved from GeeksforGeeks
    public boolean isPrime(int n) {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    // Function to return the smallest
    // prime number greater than N
    public int nextPrime(int N) {

        // Base case
        if (N <= 1)
            return 2;

        int prime = N;
        boolean found = false;

        // Loop continuously until isPrime returns
        // true for a number greater than n
        while (!found)
        {
            prime++;

            if (isPrime(prime))
                found = true;
        }

        return prime;
    }
    // main method of reading pixels into hash table, do probing when having collision
    // and rehash if it is half full
    public void count(RGB color){
        int key;
        int position;
        int i = 1;
        int probingPos;
        boolean isHalfFull = false;
        boolean isFind = false;
        // hashing function is (R+G+B) % arraySize
        key = color.getR() + color.getG() + color.getB();
        position = key % arraySize;

        // empty
        if(ht[position] == null){
            // insert
            ht[position] = new Freq(color, 1);
            actualSize++;
            isHalfFull = isHalfFull();
        }
        else{
            // not empty, compare
            if((ht[position].getColor().getR() == color.getR()) && (ht[position].getColor().getG() == color.getG()) && (ht[position].getColor().getB() == color.getB())){
                ht[position].incrCount();
            }
            else{
                // not empty, not same, start probing
                // go to i*i position away from initial
                numCollision++;
                probingPos = (position + i * i) % arraySize;
                while(ht[probingPos] != null){
                    if((ht[probingPos].getColor().getR() == color.getR()) && (ht[probingPos].getColor().getG() == color.getG()) && ht[probingPos].getColor().getB() == color.getB()){
                        ht[probingPos].incrCount();
                        isFind = true;
                        break;
                    }
                    else {
                        i++;
                        probingPos = (position + i * i) % arraySize;
                    }
                }
                if(!isFind){
                    ht[probingPos] = new Freq(color, 1);
                    actualSize++;
                    isHalfFull = isHalfFull();
                }
            }
        }
        // check for half full, if true, rehash
        if(isHalfFull){
            numRehash++;
            int origSize;
            int newProbingPos;
            origSize = arraySize;
            // expand arraySize
            arraySize = nextPrime(arraySize * 2);
            // keep track of old table as tmp
            Freq[] tmp = ht;
            ht = new Freq[arraySize];
            actualSize = 0;
            int newPosition;
            int k = 1;
            // rehash everything
            for(int j = 0; j < origSize; j++){
                k = 1;
                if(tmp[j] != null){
                    newPosition = (tmp[j].getColor().getR() + tmp[j].getColor().getG() + tmp[j].getColor().getB()) % arraySize;
                    if(ht[newPosition] == null){
                        // empty
                        ht[newPosition] = new Freq(tmp[j].getColor(), tmp[j].getFreq());  // copy the entire Freq, include count
                        actualSize++;
                    }
                    else{
                        // not empty, start probing
                        numCollision++;
                        newProbingPos = (newPosition + k * k) % arraySize;
                        while(ht[newProbingPos] != null){
                            k++;
                            newProbingPos = (newPosition + k * k) % arraySize;
                        }
                        // find empty spot
                        ht[newProbingPos] = new Freq(tmp[j].getColor(), tmp[j].getFreq());
                        actualSize++;
                    }
                }
            }
        }
    }
    // implement BubbleSort to sort the array and print out top 256 pixels
    public void sortAndPrint() {
        Freq[] arr = new Freq[actualSize];
        int i = 0;
        // read into an array without empty spot
        for (int j = 0; j < arraySize; j++) {
            if (ht[j] != null) {
                arr[i] = ht[j];
                i++;
            }
        }
        // bubble sort
        for (int k = 0; k < arr.length - 1; k++) {
            for (int m = 0; m < arr.length - k - 1; m++) {
                if (arr[m].compareTo(arr[m + 1]) == -1) {
                    Freq temp = arr[m];
                    arr[m] = arr[m + 1];
                    arr[m + 1] = temp;
                }
            }
        }
        // print top 256 if size is more than 256
        // else just print whatever top number
        int topSize;
        if(arr.length < 256){
            topSize = arr.length;
        }
        else {
            topSize = 256;
        }
        for(int n = 0; n < topSize; n++){
            arr[n].print();
        }
    }
    // methods that print essential information in main
    public void printTableSize(){
        System.out.println("Final table size is " + arraySize);
    }
    public void printCollisionAndRehash(){
        System.out.println("Number of Collision is " + numCollision + " \nNumber of rehashes is " + numRehash);
    }
}
