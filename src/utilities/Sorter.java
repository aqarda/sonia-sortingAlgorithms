package utilities;

import java.util.Comparator;
import products.Product;
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
	 * Sorts the products from largest to smallest using bubble sort and a comparator.
	 *
	 * @param array      the products to sort
	 * @param comparator decides how two products are compared
	 * @throws IllegalArgumentException if the array or comparator is null
	 */
	public static void bubbleSort( Product[] array, Comparator<Product> comparator )
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
	 * Sorts using products natural ordering and bubblesort
	 * @param array      the array to sort
	 * @throws IllegalArgumentException if the array is null
	 */
	public static void bubbleSort( Product[] array )
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
	 * Sorts using the products natural ordering and selection sort
	 * @param array the array to sort
	 * @throws IllegalArgumentException if the array is null
	 */
	public static void selectionSort( Product[] array )
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
	 * Sorts the products from largest to smallest using selection sort and a comparator.
	 * @param array      the array to sort
	 * @param comparator the comparator that defines the ordering
	 * @throws IllegalArgumentException if the array or comparator is null
	 */
	public static void selectionSort( Product[] array, Comparator<Product> comparator )
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
	 * @param array the array containing the elements
	 * @param i     the index of the first element
	 * @param j     the index of the second element
	 */
	private static void swap( Product[] array, int i, int j )
	{
		Product temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Checks that the array is not null.
	 * @param array the array to check
	 * @throws IllegalArgumentException if the array is null
	 */
	private static void checkArray( Product[] array )
	{
		if( array == null )
		{
			throw new IllegalArgumentException( "Array to sort cannot be null." );
		}
	}

	/**
	 * Checks that the comparator is not null.
	 * @param comparator the comparator to check
	 * @throws IllegalArgumentException if the comparator is null
	 */
	private static void checkComparator( Comparator<Product> comparator )
	{
		if( comparator == null )
		{
			throw new IllegalArgumentException( "Comparator cannot be null." );
		}
	}
}