//* Description *//
// Title: Abstract Sort
// Author: Tyler Reed
// Defines an Abstract Sorting Algorithm

//* Package *//
package Sort;

//* Libraries *//
import java.util.List;
import javax.naming.OperationNotSupportedException;

//* Abstract Sort Class *//
public abstract class AbstractSort<T extends Comparable<? super T>>
{
	//* Abstract Methods *//
	// Sorts the Class List in Ascending Order
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list) throws OperationNotSupportedException
	{
		return sort(list, true);
	}

	// Sorts the Class List in the specified Order
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list, boolean ascending) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	//* Utility Methods *//
	// Returns whether or not all Collections within the specified List are Empty
	protected static <T extends Comparable<? super T>> boolean isEmpty(List<List<T>> split)
	{
		for(List<T> list : split)
			if(!list.isEmpty())
				return false;

		return true;
	}

	// Returns the Number of Empty Collections within the specified List
	protected static <T extends Comparable<? super T>> int getRemainingCount(List<List<T>> split)
	{
		int count = 0;

		for(List<T> list : split)
			if(!list.isEmpty())
				count++;

		return count;
	}

	// Returns the Total Size of all Nested Collections
	protected static <T extends Comparable<? super T>> int getSize(List<List<T>> split)
	{
		int size = 0;

		for(List<T> list : split)
			size += list.size();

		return size;
	}
}
