package appDomain;

import java.util.Comparator;
import java.util.Locale;

import products.Product;
import products.OriginalPriceComparator;
import products.UpdatedPriceComparator;

import utilities.HeapSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.ProductFileException;
import utilities.ProductFileReader;
import utilities.QuickSort;
import utilities.Sorter;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
    /**
     *  The main method is the entry point of the application.
     *
     *  @param args The input to control the execution of the application.
     */
    public static void main( String[] args )
    {
        String fileName = null;
        String compareType = null;
        String sortType = null;

        try
        {
            for (String argument : args)
            {
                if (argument.length() < 3 || argument.charAt(0) != '-')
                {
                    throw new IllegalArgumentException(
                            "Invalid argument: " + argument);
                }

                char option =
                        Character.toLowerCase(argument.charAt(1));
                String value = argument.substring(2);

                switch (option)
                {
                    case 'f':
                        if (fileName != null)
                        {
                            throw new IllegalArgumentException(
                                    "Provide -f only once.");
                        }

                        if (value.isBlank())
                        {
                            throw new IllegalArgumentException(
                                    "Provide a file name after -f.");
                        }

                        fileName = value;
                        break;

                    case 't':
                        if (compareType != null)
                        {
                            throw new IllegalArgumentException(
                                    "Provide -t only once.");
                        }

                        if (!value.equalsIgnoreCase("o")
                                && !value.equalsIgnoreCase("u")
                                && !value.equalsIgnoreCase("d"))
                        {
                            throw new IllegalArgumentException(
                                    "Comparison must be o, u, or d.");
                        }

                        compareType = value.toLowerCase(Locale.ROOT);
                        break;

                    case 's':
                        if (sortType != null)
                        {
                            throw new IllegalArgumentException(
                                    "Provide -s only once.");
                        }

                        if (value.length() != 1
                                || "bsimqz".indexOf(
                                    Character.toLowerCase(
                                        value.charAt(0))) < 0)
                        {
                            throw new IllegalArgumentException(
                                    "Sort must be b, s, i, m, q, or z.");
                        }

                        sortType = value.toLowerCase(Locale.ROOT);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Unknown option: -" + option);
                }
            }

            if (fileName == null || compareType == null
                    || sortType == null)
            {
                throw new IllegalArgumentException(
                        "Provide all three options: -f, -t, and -s.");
            }

            // Read the file before starting the sorting timer.
            Product[] products =
                    ProductFileReader.readProducts(fileName);

            Comparator<Product> comparator;

            switch (compareType)
            {
                case "o":
                    comparator = new OriginalPriceComparator();
                    break;

                case "u":
                    comparator = new UpdatedPriceComparator();
                    break;

                default:
                    comparator = Comparator.naturalOrder();
                    break;
            }

            // Choose the sorting operation before starting the timer.
            Runnable sortingTask;
            String algorithmName;

            switch (sortType)
            {
                case "b":
                    sortingTask =
                            () -> Sorter.bubbleSort(products, comparator);
                    algorithmName = "Bubble Sort";
                    break;

                case "s":
                    sortingTask =
                            () -> Sorter.selectionSort(products, comparator);
                    algorithmName = "Selection Sort";
                    break;

                case "i":
                    sortingTask =
                            () -> InsertionSort.sort(products, comparator);
                    algorithmName = "Insertion Sort";
                    break;

                case "m":
                    sortingTask =
                            () -> MergeSort.sort(products, comparator);
                    algorithmName = "Merge Sort";
                    break;

                case "q":
                    sortingTask =
                            () -> QuickSort.sort(products, comparator);
                    algorithmName = "Quick Sort";
                    break;

                case "z":
                    sortingTask =
                            () -> HeapSort.heapSort(products, comparator);
                    algorithmName = "Heap Sort";
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid sorting algorithm.");
            }

            // Measure only the sorting operation.
            long start = System.nanoTime();
            sortingTask.run();
            long end = System.nanoTime();

            long milliseconds = (end - start) / 1_000_000L;

            displayProducts(products, compareType);

            System.out.println(algorithmName + " run time was: "
                    + milliseconds + " milliseconds");
        }
        catch (IllegalArgumentException e)
        {
            System.err.println("Error: " + e.getMessage());
            System.err.println(
                    "Example: -fres/products1.txt -td -sq");
        }
        catch (ProductFileException e)
        {
            System.err.println("File error: " + e.getMessage());
        }
    }

    /**
     * Displays all products for small arrays, or the required positions
     * for larger arrays.
     *
     * @param products the sorted product array
     * @param compareType the selected comparison type: o, u, or d
     */
    private static void displayProducts(
            Product[] products, String compareType)
    {
        int count = products.length;

        if (count == 0)
        {
            System.out.println("No products to display.");
            return;
        }

        if (count <= 10)
        {
            for (int i = 0; i < count; i++)
            {
                int position = i + 1;
                String suffix;

                switch (position)
                {
                    case 1:
                        suffix = "st";
                        break;

                    case 2:
                        suffix = "nd";
                        break;

                    case 3:
                        suffix = "rd";
                        break;

                    default:
                        suffix = "th";
                        break;
                }

                printProduct(
                        position + "-" + suffix + " element:",
                        products[i],
                        compareType);
            }

            return;
        }

        printProduct(
                "First element is:",
                products[0],
                compareType);

        int interval = count <= 1000 ? 100 : 1000;

        for (int position = interval; position < count;
                position += interval)
        {
            printProduct(
                    position + "-th element:",
                    products[position - 1],
                    compareType);
        }

        printProduct(
                "Last element is:",
                products[count - 1],
                compareType);
    }

    /**
     * Prints a product's type and the selected comparison value.
     *
     * @param label the position label
     * @param product the product to display
     * @param compareType the selected comparison type: o, u, or d
     */
    private static void printProduct(
            String label, Product product, String compareType)
    {
        String valueName;
        double value;

        switch (compareType)
        {
            case "o":
                valueName = "Original Price:";
                value = product.getOriginalPrice();
                break;

            case "u":
                valueName = "Updated Price:";
                value = product.getUpdatedPrice();
                break;

            default:
                valueName = "Calculated Difference:";
                value = product.calcRealDiff();
                break;
        }

        System.out.printf(
                Locale.ROOT,
                "%-22s %-22s %s %s%n",
                label,
                product.getClass().getName(),
                valueName,
                value);
    }
}
