package utilities;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>
 * Merge sort that arranges an array from largest to smallest (descending
 * order).
 * </p>
 * <p>
 * The sort never sorts ascending and reverses; it uses the supplied
 * {@link Comparable} / {@link Comparator} directly and, while merging, takes
 * the greater element first. The sort is stable and contains no timing or
 * printing code.
 * </p>
 */
public class MergeSort
{
	/** Not meant to be instantiated; all methods are static. */
	private MergeSort()
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
		if ( array.length < 2 )
		{
			return;
		}
		T[] helper = Arrays.copyOf( array, array.length );
		mergeSort( array, helper, 0, array.length - 1, comparator );
	}

	/**
	 * Recursively splits the range in half, sorts each half and merges them.
	 *
	 * @param <T>        The element type.
	 * @param array      The array being sorted.
	 * @param helper     A scratch array of the same length, used while merging.
	 * @param low        The first index of the range (inclusive).
	 * @param high       The last index of the range (inclusive).
	 * @param comparator Compares two elements.
	 */
	private static <T> void mergeSort( T[] array, T[] helper, int low, int high, Comparator<? super T> comparator )
	{
		if ( low >= high )
		{
			return;
		}
		int mid = low + ( high - low ) / 2;
		mergeSort( array, helper, low, mid, comparator );
		mergeSort( array, helper, mid + 1, high, comparator );
		merge( array, helper, low, mid, high, comparator );
	}

	/**
	 * Merges two sorted neighbouring ranges, [low..mid] and [mid+1..high], into
	 * one sorted range. The greater element is taken first (descending); when
	 * elements are equal the left one is taken so the sort stays stable.
	 *
	 * @param <T>        The element type.
	 * @param array      The array being sorted.
	 * @param helper     A scratch array of the same length.
	 * @param low        The first index of the left range.
	 * @param mid        The last index of the left range.
	 * @param high       The last index of the right range.
	 * @param comparator Compares two elements.
	 */
	private static <T> void merge( T[] array, T[] helper, int low, int mid, int high, Comparator<? super T> comparator )
	{
		System.arraycopy( array, low, helper, low, high - low + 1 );

		int left = low;
		int right = mid + 1;
		int current = low;

		while ( left <= mid && right <= high )
		{
			if ( comparator.compare( helper[left], helper[right] ) >= 0 )
			{
				array[current++] = helper[left++];
			}
			else
			{
				array[current++] = helper[right++];
			}
		}

		// any leftovers on the left side still need copying; the right side is already in place
		while ( left <= mid )
		{
			array[current++] = helper[left++];
		}
	}
}
