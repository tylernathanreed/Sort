//* Description *//
// Title: Main
// Author: Tyler Reed
// Runs the Program

//* Package *//
package Main;

//* Libraries *//
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import Sort.MergeSort.MergeSort;
import Sort.QuickSort.QuickSort;

//* Main Class *//
public class Main
{
	//* Main Method *//
	// Runs the Program
	public static void main(String[] args) throws FileNotFoundException
	{
		// Determine the Output Stream
		// System.setOut(new PrintStream(new File("output.txt")));
		// Create a Random Generator
		Random generator = new Random();
		generator.setSeed(0);

		// Determine Input Size
		int size = 5000000;

		// Create a Random List
		List<Integer> list = new ArrayList<Integer>(size);

		for(int i = 0; i < size; i++)
			list.add(generator.nextInt() % size);

		//System.out.println("Initial Collection: " + list);

		// Initialize the Sorting Benchmark
		long time = System.currentTimeMillis();

		list = QuickSort.sort(list);
		//Collections.sort(list);

		// Determine the Final Benchmark
		time = System.currentTimeMillis() - time;

		//System.out.println("Final Collection: " + list);

		System.out.println("Finished in " + time + " ms (Using " + QuickSort.Helper.TOTAL + " Threads) with " + QuickSort.COMPARISONS + " Comparisons (nlgn: " + (int) Math.ceil(Math.log(size) / Math.log(2) * size) + ", nn: " + (int) Math.pow(size,  2) + ")");
	}
}
