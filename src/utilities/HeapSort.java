package utilities;

import java.util.Comparator;

/**
 * <p>
 * Provides heapsort, the custom sorting algorithm (option <b>z</b>) for the
 * Complexity and Sorting assignment. It arranges an array of objects from
 * largest to smallest (descending order).
 * </p>
 * <p>
 * Heapsort treats the array as a complete binary tree called a heap. For the
 * element at index <code>i</code>, its left child is at <code>2i + 1</code>
 * and its right child is at <code>2i + 2</code>. This class builds a
 * <b>min-heap</b>, where every parent is less than or equal to its children,
 * so the smallest element is always at index 0 (the root). The smallest
 * element is repeatedly swapped to the end of the unsorted part of the array,
 * so the array fills from the back with the smallest values and ends up in
 * descending order. The array is never sorted ascending and then reversed.
 * </p>
 * <p>
 * Time complexity: O(n log n) in the worst case.<br>
 * Space complexity: O(1), because the array is sorted in place and no
 * recursion is used.
 * </p>
 *
 */
public final class HeapSort
{
	/**
	 * Prevents objects of this class from being created, because it only
	 * provides static sorting methods.
	 */
	private HeapSort()
	{
	}

	/**
	 * Sorts the array from largest to smallest using the natural ordering of
	 * its elements, as defined by their <code>compareTo()</code> method
	 * (Comparable). In this application, this is the ordering used for the
	 * <b>d</b> (calculated difference) compare type.
	 *
	 * @param <T>   the type of the elements, which must implement Comparable
	 * @param array the array to sort; it is rearranged in place
	 * @throws IllegalArgumentException if the array is null
	 */
	public static <T extends Comparable<? super T>> void heapSort( T[] array )
	{
		heapSort( array, ( first, second ) -> first.compareTo( second ) );
	}

	/**
	 * Sorts the array from largest to smallest using the ordering defined by
	 * the given Comparator. In this application, this is used for the
	 * <b>o</b> (original price) and <b>u</b> (updated price) compare types.
	 * <p>
	 * The sort runs in two phases:
	 * </p>
	 * <ol>
	 * <li>Build a min-heap from the whole array.</li>
	 * <li>Repeatedly swap the root (the smallest remaining element) with the
	 * last element of the heap, shrink the heap by one, and sift the new root
	 * down to restore the min-heap.</li>
	 * </ol>
	 *
	 * @param <T>        the type of the elements
	 * @param array      the array to sort; it is rearranged in place
	 * @param comparator the Comparator that decides which of two elements is
	 *                   greater
	 * @throws IllegalArgumentException if the array or the comparator is null
	 */
	public static <T> void heapSort( T[] array, Comparator<? super T> comparator )
	{
		if( array == null )
		{
			throw new IllegalArgumentException( "Heap sort error: the array to sort cannot be null." );
		}
		if( comparator == null )
		{
			throw new IllegalArgumentException( "Heap sort error: the comparator cannot be null." );
		}

		int n = array.length;

		// Phase 1: build the min-heap. Leaves are already valid one-element
		// heaps, so start at the last parent (index n / 2 - 1) and work back
		// up to the root, sifting each parent down into place.
		for( int i = n / 2 - 1; i >= 0; i-- )
		{
			siftDown( array, i, n, comparator );
		}

		// Phase 2: the root holds the smallest value in the heap. Swap it to
		// the end of the heap, where it is now in its final sorted position,
		// then shrink the heap by one and repair it from the root.
		for( int end = n - 1; end > 0; end-- )
		{
			swap( array, 0, end );
			siftDown( array, 0, end, comparator );
		}
	}

	/**
	 * Moves the element at the given parent index down the heap until it is
	 * less than or equal to both of its children, which restores the
	 * min-heap rule for that part of the tree. Only the first
	 * <code>heapSize</code> elements of the array belong to the heap; the
	 * elements after them are already sorted and are not touched.
	 *
	 * @param <T>        the type of the elements
	 * @param array      the array that holds the heap
	 * @param parent     the index of the element to move down
	 * @param heapSize   the number of elements currently in the heap
	 * @param comparator the Comparator used to compare elements
	 */
	private static <T> void siftDown( T[] array, int parent, int heapSize, Comparator<? super T> comparator )
	{
		int child = 2 * parent + 1; // the left child

		while( child < heapSize )
		{
			// If there is a right child and it is smaller, use it instead,
			// so the parent is always compared with its smaller child.
			if( child + 1 < heapSize && comparator.compare( array[child + 1], array[child] ) < 0 )
			{
				child = child + 1;
			}

			// The min-heap rule already holds: the parent is not greater
			// than its smaller child, so nothing below it needs to move.
			if( comparator.compare( array[parent], array[child] ) <= 0 )
			{
				return;
			}

			// The parent is greater than its smaller child: swap them and
			// continue one level down the tree.
			swap( array, parent, child );
			parent = child;
			child = 2 * parent + 1;
		}
	}

	/**
	 * Swaps the elements at two indexes of the array.
	 *
	 * @param <T>   the type of the elements
	 * @param array the array that holds the elements
	 * @param i     the index of the first element
	 * @param j     the index of the second element
	 */
	private static <T> void swap( T[] array, int i, int j )
	{
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
