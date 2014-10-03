//* Description *//
// Title: Merge Sort
// Author: Tyler Reed
// Defines a Simple Divide and Conquer Comparison Sort

//* Package *//
package Sort.MergeSort;

//* Libraries *//
import java.util.ArrayList;
import java.util.List;
import Sort.AbstractSort;

//* Quick Sort Class *//
public class MergeSort<T extends Comparable<? super T>> extends AbstractSort<T>
{
	//* Class Variables *//
	// Public Variables
	public static volatile int COMPARISONS = 0;
	public static volatile boolean THREADED = false;
	public static volatile int THREAD_COUNT = 0;
	public static volatile int THREAD_MAX = 32;

	//* Constructor *//
	// Creates an Instance of Merge Sort
	public MergeSort(List<T> list)
	{
		super(list);
	}

	//* Sort Methods *//
	// Non-Static Version of Merge Sort
	public List<T> sort(boolean ascending)
	{
		return sort(list, ascending);
	}

	// Returns a Sorted Version of the specified List of Comparable Elements
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list)
	{
		return sort(list, true);
	}

	// Returns a Sorted Version of the specified List of Comparable Elements in the specified Order
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list, boolean ascending)
	{
		// Check for Trivial Case
		if(list.size() == 1)
			return list;

		// Divide the List
		List<List<T>> split = split(list, 2);

		// Check for Multi-Threading
		List<Helper<T>> helpers = THREADED ? new ArrayList<Helper<T>>(THREAD_COUNT) : null;

		for(int i = 0; i < split.size(); i++)
		{
			if(THREADED && THREAD_COUNT < THREAD_MAX)
			{
				// Recursively Sort the Partitions on separate Threads
				helpers.add(new Helper<T>(split.get(i), i, ascending));
				THREAD_COUNT++;
			}
			else
			{
				// Recursively Sort the Partitions
				split.set(i, sort(split.get(i), ascending));
			}
		}

		// Wait for Threads to Finish
		if(THREADED)
			for(Helper<T> helper : helpers)
				try
				{
					helper.thread.join();
					split.set(helper.index, helper.list);
				}
				catch(InterruptedException ex)
				{
					ex.printStackTrace();
				}

		// Merge the Sorted Sub-Lists
		List<T> merge = merge(split, ascending);

		return merge;
	}

	//* List Methods *//
	// Splits the specified Collection of Comparable Elements into the specified Number of Partitions
	private static <T extends Comparable<? super T>> List<List<T>> split(List<T> list, int count)
	{
		// Initialize the List
		int min = Math.min(count, list.size());
		ArrayList<List<T>> split = new ArrayList<List<T>>(min);

		// Determine the Size of each Partition
		int size = list.size() / count;

		// Create the Partitions
		for(int i = 0; i < min; i++)
			split.add(new ArrayList<T>(size));

		// Iterate through the List
		int i = 0;

		for(T element : list)
			split.get(i++ % count).add(element);

		return split;
	}

	// Merges the specified List of Partitioned Comparable Elements into a Sorted Collection
	private static <T extends Comparable<? super T>> List<T> merge(List<List<T>> split, boolean ascending)
	{
		// Initialize the Result
		List<T> result = new ArrayList<T>(getSize(split));

		// Loop until all Lists are Empty
		while(!isEmpty(split))
		{
			// Determine whether or not more than one List contains Elements
			if(getRemainingCount(split) > 1)
			{
				// Determine the Smallest Element within the Partitions
				T min = null;
				List<T> source = null;

				for(List<T> list : split)
					if(list.isEmpty())
						continue;
					else if(min == null)
					{
						min = list.iterator().next();
						source = list;
					}
					else
					{
						// Determine the Next Possible Element
						T next = list.iterator().next();

						// Update the Minimum Element
						COMPARISONS++;
						if(ascending ? min.compareTo(next) > 0 : min.compareTo(next) < 0)
						{
							min = next;
							source = list;
						}
					}

				// Append the Minimum Element to the Result and Remove it from the Source Collection
				result.add(min);
				source.remove(min);
			}
			else
			{
				// Append the Remaining Elements of any Empty Lists
				for(List<T> list : split)
					if(list.isEmpty())
						continue;
					else
					{
						// Add the Remaining Elements from the Final List
						result.addAll(list);
						list.clear();
						break;
					}
			}
		}

		return result;
	}

	//* Helper Thread Class *//
	private static class Helper<T extends Comparable<? super T>> implements Runnable
	{
		//* Class Variables *//
		// List Variables
		protected List<T> list;

		// Helper Variables
		protected Thread thread;
		protected int index;
		protected boolean ascending;

		//* Constructor *//
		// Creates the specified Instance of the Class
		public Helper(List<T> list, int index, boolean ascending)
		{
			this.list = list;
			this.index = index;
			this.ascending = ascending;
	
			thread = new Thread(this);
			thread.start();
		}

		// Runs the Thread
		public void run()
		{
			list = MergeSort.sort(list, ascending);
		}
	}
}
