/*
Frank Liu
CSC201 Fall 2020
Programming Assignment 3
Oct 30, 2020
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/*****************************************************************
 * This program utilize the ADT of hash table and collision resolution
 * of quadratic probing to read pixel from uncompressed RGB digital images,
 * hash them into the hash table, count their frequency
 * and print out the top 256 pixels with the highest frequency.

 * @ Command Line arguments order:
 * filename, width, height, initialSize
 *
 * @ Note:
 * Since the sort is BubbleSort, for graph of LadyBugs800x600.raw, it
 * may take minutes to finish sort. Just need some time.
 ******************************************************************/
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        long startTime = System.nanoTime();  // start to measure time
        // read command line inputs into variables
        String fileName = args[0];
        int w = Integer.parseInt(args[1]);
        int h = Integer.parseInt(args[2]);
        int size = Integer.parseInt(args[3]);
        // print out necessary information
        System.out.println("Method implemented for this program is: Quadratic Probing");
        System.out.println("File name of this program is " + fileName);
        System.out.println("Dimension of the file is " + w + " x " + h);
        System.out.println("The initial tableSize is " + size);
        // declaration of HashTable
        HashTable ht = new HashTable(size);
        String tmp = "src/";
        String absPath = tmp.concat(fileName);
        try {
            InputStream is = new FileInputStream(absPath);
            // create data input stream
            DataInputStream input = new DataInputStream(is);
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    RGB pixel = new RGB();
                    pixel.setR(input.readUnsignedByte());
                    pixel.setG(input.readUnsignedByte());
                    pixel.setB(input.readUnsignedByte());
                    ht.count(pixel);  // once you read the pixel, go ahead and process it
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        // calculate the time and print it out
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        // print out necessary information
        ht.printTableSize();
        ht.printCollisionAndRehash();
        System.out.println("\n---------------------------------------------------------------------");
        System.out.println("Print the table... Please Wait a few seconds... It take some time...");
        ht.sortAndPrint();
    }
}
