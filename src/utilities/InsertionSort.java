package utilities;

import java.util.Comparator;

/**
 * <p>
 * Insertion sort that arranges an array from largest to smallest (descending
 * order).
 * </p>
 * <p>
 * The sort never sorts ascending and reverses; it uses the supplied
 * {@link Comparable} / {@link Comparator} directly and places an element
 * earlier in the array when it compares as greater. The sort is stable and
 * contains no timing or printing code.
 * </p>
 */
public class InsertionSort
{
	/** Not meant to be instantiated; all methods are static. */
	private InsertionSort()
	{
	}

	/**
	 * Sorts the array in descending order using the natural ordering of its
	 * elements (their <code>compareTo()</code> method).
	 *
	 * @param <T>   The element type; must be comparable to itself.
	 * @param array The array to sort in place.
	 */
	public static <T extends Comparable<? super T>> void sort( T[] array )
	{
		sort( array, Comparator.<T>naturalOrder() );
	}

	/**
	 * Sorts the array in descending order using the given comparator.
	 *
	 * @param <T>        The element type.
	 * @param array      The array to sort in place.
	 * @param comparator Compares two elements; a positive result means the first
	 *                   element is greater than the second.
	 */
	public static <T> void sort( T[] array, Comparator<? super T> comparator )
	{
		for ( int i = 1; i < array.length; i++ )
		{
			T key = array[i];
			int j = i - 1;

			// shift smaller elements one place to the right
			while ( j >= 0 && comparator.compare( array[j], key ) < 0 )
			{
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}
}
