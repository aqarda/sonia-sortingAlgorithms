package utilities;

import java.util.Comparator;

/**
 * Sorts arrays in descending order using Quicksort.
 * Uses Median-of-3 to choose the pivot.
 */
public class QuickSort
{
    /** Prevents creating objects of this utility class. */
    private QuickSort()
    {
    }

    /**
     * Sorts using the elements' natural ordering.
     *
     * @param <T> the comparable element type
     * @param array the array to sort
     */
    public static <T extends Comparable<? super T>> void sort(T[] array)
    {
        sort(array, Comparator.<T>naturalOrder());
    }

    /**
     * Sorts from largest to smallest using a comparator.
     *
     * @param <T> the element type
     * @param array the array to sort
     * @param comparator the comparator used to compare elements
     */
    public static <T> void sort(
            T[] array, Comparator<? super T> comparator)
    {
        quickSort(array, 0, array.length - 1, comparator);
    }

    /**
     * Sorts a section using a Median-of-3 pivot.
     * Recurses on the smaller section to limit stack usage.
     *
     * @param <T> the element type
     * @param array the array to sort
     * @param low the first index
     * @param high the last index
     * @param comparator the comparator used to compare elements
     */
    private static <T> void quickSort(
            T[] array, int low, int high,
            Comparator<? super T> comparator)
    {
        while (low < high)
        {
            int middle = low + (high - low) / 2;

            // Arrange the first, middle and last values descending.
            if (comparator.compare(array[low], array[middle]) < 0)
            {
                swap(array, low, middle);
            }

            if (comparator.compare(array[low], array[high]) < 0)
            {
                swap(array, low, high);
            }

            if (comparator.compare(array[middle], array[high]) < 0)
            {
                swap(array, middle, high);
            }

            // The middle value of the three becomes the pivot.
            T pivot = array[middle];

            int left = low;
            int right = high;

            while (left <= right)
            {
                // Skip values larger than the pivot.
                while (left <= right
                        && comparator.compare(array[left], pivot) > 0)
                {
                    left++;
                }

                // Skip values smaller than the pivot.
                while (left <= right
                        && comparator.compare(array[right], pivot) < 0)
                {
                    right--;
                }

                if (left <= right)
                {
                    swap(array, left, right);
                    left++;
                    right--;
                }
            }

            // Recurse on the smaller section.
            // Continue the larger section using the outer loop.
            if (right - low < high - left)
            {
                if (low < right)
                {
                    quickSort(array, low, right, comparator);
                }

                low = left;
            }
            else
            {
                if (left < high)
                {
                    quickSort(array, left, high, comparator);
                }

                high = right;
            }
        }
    }

    /**
     * Exchanges two elements in an array.
     *
     * @param <T> the element type
     * @param array the array containing the elements
     * @param first the first index
     * @param second the second index
     */
    private static <T> void swap(T[] array, int first, int second)
    {
        T temporary = array[first];
        array[first] = array[second];
        array[second] = temporary;
    }
}