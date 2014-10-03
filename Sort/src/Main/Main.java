//* Description *//
// Title: Main
// Author: Tyler Reed
// Runs the Program and Interprets the Command Line

//* Package *//
package Main;

//* Libraries *//
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import Sort.Sort;
import Sort.QuickSort.QuickSort;

//* Main Class *//
public class Main
{
	//* Main Method *//
	// Runs the Program and Interprets the Command Line
	public static void main(String[] args) throws FileNotFoundException
	{
		// Interpret the Command Line
		int seed = seed(args);
		String sortType = sortType(args);
		int length = length(args);

		// Display Command Line Parameters
		System.out.println("Recognized Parameters:");

		if(hasFlag(args, "seed")) System.out.println(" -> Seed: " + seed);
		if(hasFlag(args, "sort")) System.out.println(" -> Sort: " + sortType);
		if(hasFlag(args, "length")) System.out.println(" -> Length: " + length);

		System.out.println();

		// Determine the Output Stream
		// System.setOut(new PrintStream(new File("res/output.txt")));

		// Create a Random Generator
		Random generator = new Random();
		generator.setSeed(seed);

		// Create a Random List
		List<Integer> list = new ArrayList<Integer>(length);

		for(int i = 0; i < length; i++)
			list.add(generator.nextInt() % length);

		System.out.println("Initial Collection: " + list);

		// Initialize the Sorting Benchmark
		long time = System.currentTimeMillis();

		// Sort the List
		switch(sortType)
		{
			// Quick Sort
			case "quick":
				Sort.quickSort(list);
				break;

			// Merge Sort
			case "merge":
				Sort.mergeSort(list);
				break;

			// Default
			default:
				Collections.sort(list);
				break;
		}

		// Determine the Final Benchmark
		time = System.currentTimeMillis() - time;

		System.out.println("Final Collection: " + list);

		System.out.println("Finished in " + time + " ms (Using " + QuickSort.Helper.TOTAL + " Threads) with " + QuickSort.COMPARISONS + " Comparisons (nlgn: " + (int) Math.ceil(Math.log(length) / Math.log(2) * length) + ", nn: " + (int) Math.pow(length,  2) + ")");
	}

	//* Command Line Methods *//
	// Returns the specified Seed (0 if Unspecified)
	public static int seed(String[] args)
	{
		String seed = getFlagValue(args, "seed");

		return seed == null ? 0 : Integer.parseInt(seed);
	}

	// Returns the specified Sort Type (Collections.sort if Unspecified)
	public static String sortType(String[] args)
	{
		String type = getFlagValue(args, "sort");

		return type == null ? "default" : type;
	}

	// Returns the specified Input Size (100 if Unspecified)
	public static int length(String[] args)
	{
		String length = getFlagValue(args, "length");

		return length == null ? 100 : Integer.parseInt(length);
	}

	// Returns whether or not the specified Flag Exists
	public static boolean hasFlag(String[] args, String flag)
	{
		// Search for the Flag
		for(String arg : args)
			if(arg.substring(1).equals(flag))
				return true;

		// Flag not Found
		return false;
	}

	// Returns the Flag Value of the specified Flag
	public static String getFlagValue(String[] args, String flag)
	{
		// Search for the Flag
		for(int i = 0; i < args.length - 1; i++)
			if(args[i].substring(1).equals(flag))
				return args[i + 1];

		// Flag not Found
		return null;
	}
}
