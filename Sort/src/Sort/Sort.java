//* Description *//
// Title: Sort
// Author: Tyler Reed
// Implements various Sorting Algorithms

//* Package *//
package Sort;

//* Libraries *//
import java.util.List;
import Sort.MergeSort.MergeSort;
import Sort.QuickSort.QuickSort;
import Sort.SelectionSort.SelectionSort;

//* Sort Interface *//
public final class Sort
{
	//* Class Variables *//
	// Statistic Variables
	private static int threads;
	private static int comparisons;
	private static long start;
	private static long stop;

	//* Sort Methods *//
	// Sorts the specified List using the Selection Sort Method
	public static <T extends Comparable<? super T>> List<T> selectionSort(List<T> list)
	{
		start();

		try { SelectionSort.sort(list); } catch(Exception ex) { ex.printStackTrace(); }

		stop();

		threads = 0;
		comparisons = SelectionSort.COMPARISONS;

		return list;
	}

	// Sorts the specified List using the Merge Sort Method
	public static <T extends Comparable<? super T>> List<T> mergeSort(List<T> list)
	{
		start();
		MergeSort.sort(list);
		stop();

		threads = MergeSort.THREAD_COUNT;
		comparisons = MergeSort.COMPARISONS;

		return list;
	}

	// Sorts the specified List using the Quick Sort Method
	public static <T extends Comparable<? super T>> List<T> quickSort(List<T> list)
	{
		start();
		QuickSort.sort(list);
		stop();

		threads = QuickSort.Helper.TOTAL;
		comparisons = QuickSort.COMPARISONS;

		return list;
	}

	//* Statistic Methods *//
	// Returns the Thread Count
	public static int getLastThreadCount()
	{
		return threads;
	}

	// Returns the Comparison Count
	public static int getLastComparisonCount()
	{
		return comparisons;
	}

	// Returns the Benchmark
	public static long getLastBenchmark()
	{
		return stop - start;
	}

	// Starts the Benchmark
	private static void start()
	{
		reset();
		start = System.currentTimeMillis();
	}

	// Stops the Benchmark
	private static void stop()
	{
		stop = System.currentTimeMillis();
	}

	// Resets the Statistics
	private static void reset()
	{
		threads = 0;
		comparisons = 0;
		start = 0;
		stop = 0;
	}
}
