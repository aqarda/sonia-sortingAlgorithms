package utilities;

import java.util.Comparator;

/**
 * <p>
 * Class holding the sorting algorithms that arrange arrays in
 * descending order. Each algorithm has two versions:
 * one that uses the elements' natural ordering (Comparable) and one that uses
 * a supplied Comparator.
 * </p>
 * <p>
 * These methods only sort; they perform no timing or console output.
 * </p>
 */
public class Sorter
{
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private Sorter()
	{
	}


	/**
	 * Sorts the array in descending order using Bubble Sort and the elements
	 * natural ordering (compareTo). Adjacent values are swapped when the
	 * left element is smaller than the right, so larger values move toward
	 * the left. Stops early if a full pass makes no swaps.
	 *
	 * @param <T>   the element type, must be comparable
	 * @param the array to sort
	 * @throws IllegalArgumentException if the array is null
	 */
	public static <T extends Comparable<? super T>> void bubbleSort( T[] array )
	{
		checkArray( array );
		int n = array.length;
		for( int i = 0; i < n - 1; i++ )
		{
			boolean swapped = false;
			for( int j = 0; j < n - 1 - i; j++ )
			{
				if( array[j].compareTo( array[j + 1] ) < 0 )
				{
					swap( array, j, j + 1 );
					swapped = true;
				}
			}
			if( !swapped )
			{
				break;
			}
		}
	}

	/**
	 * Sorts the array in descending order using Bubble Sort and the given
	 * Comparator. Adjacent elements are swapped when the comparator reports
	 * the left element as smaller than the right. Stops early if a full pass
	 * makes no swaps.
	 *
	 * @param <T>        the element type
	 * @param array      the array to sort
	 * @param comparator the comparator that defines the ordering
	 * @throws IllegalArgumentException if the array or comparator is null
	 */
	public static <T> void bubbleSort( T[] array, Comparator<? super T> comparator )
	{
		checkArray( array );
		checkComparator( comparator );
		int n = array.length;
		for( int i = 0; i < n - 1; i++ )
		{
			boolean swapped = false;
			for( int j = 0; j < n - 1 - i; j++ )
			{
				if( comparator.compare( array[j], array[j + 1] ) < 0 )
				{
					swap( array, j, j + 1 );
					swapped = true;
				}
			}
			if( !swapped )
			{
				break;
			}
		}
	}


	/**
	 * Sorts the array in descending order using Selection Sort and the
	 * products natural ordering (compareTo). Each pass finds the largest
	 * element in the unsorted section and swaps it into the next position.
	 *
	 * @param <T>   the element type, which must be Comparable
	 * @param array the array to sort
	 * @throws IllegalArgumentException if the array is null
	 */
	public static <T extends Comparable<? super T>> void selectionSort( T[] array )
	{
		checkArray( array );
		int n = array.length;
		for( int i = 0; i < n - 1; i++ )
		{
			int maxIndex = i;
			for( int j = i + 1; j < n; j++ )
			{
				if( array[j].compareTo( array[maxIndex] ) > 0 )
				{
					maxIndex = j;
				}
			}
			if( maxIndex != i )
			{
				swap( array, i, maxIndex );
			}
		}
	}

	/**
	 * Sorts the array in descending order using Selection Sort and the given
	 * Comparator. Each pass finds the largest element (according to the
	 * comparator) in the unsorted section and swaps it into the next position.
	 *
	 * @param <T>        the element type
	 * @param array      the array to sort
	 * @param comparator the comparator that defines the ordering
	 * @throws IllegalArgumentException if the array or comparator is null
	 */
	public static <T> void selectionSort( T[] array, Comparator<? super T> comparator )
	{
		checkArray( array );
		checkComparator( comparator );
		int n = array.length;
		for( int i = 0; i < n - 1; i++ )
		{
			int maxIndex = i;
			for( int j = i + 1; j < n; j++ )
			{
				if( comparator.compare( array[j], array[maxIndex] ) > 0 )
				{
					maxIndex = j;
				}
			}
			if( maxIndex != i )
			{
				swap( array, i, maxIndex );
			}
		}
	}


	/**
	 * Swaps two elements in an array.
	 *
	 * @param <T>   the element type
	 * @param array the array containing the elements
	 * @param i     the index of the first element
	 * @param j     the index of the second element
	 */
	private static <T> void swap( T[] array, int i, int j )
	{
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Checks that the array is not null.
	 * @param the array to check
	 * @throws IllegalArgumentException if the array is null
	 */
	private static void checkArray( Object[] array )
	{
		if( array == null )
		{
			throw new IllegalArgumentException( "Array to sort cannot be null." );
		}
	}

	/**
	 * Checks that the comparator is not null.
	 * @param the comparator to check
	 * @throws IllegalArgumentException if the comparator is null
	 */
	private static void checkComparator( Comparator<?> comparator )
	{
		if( comparator == null )
		{
			throw new IllegalArgumentException( "Comparator cannot be null." );
		}
	}
}