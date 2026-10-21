package appDomain;

import java.util.Comparator;

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

                char option = Character.toLowerCase(argument.charAt(1));
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

                        compareType =
                                value.toLowerCase(java.util.Locale.ROOT);
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

                        sortType =
                                value.toLowerCase(java.util.Locale.ROOT);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Unknown option: -" + option);
                }
            }

            if (fileName == null || compareType == null || sortType == null)
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

            switch (sortType)
            {
                case "b":
                    sortingTask =
                            () -> Sorter.bubbleSort(products, comparator);
                    break;

                case "s":
                    sortingTask =
                            () -> Sorter.selectionSort(products, comparator);
                    break;

                case "i":
                    sortingTask =
                            () -> InsertionSort.sort(products, comparator);
                    break;

                case "m":
                    sortingTask =
                            () -> MergeSort.sort(products, comparator);
                    break;

                case "q":
                    sortingTask =
                            () -> QuickSort.sort(products, comparator);
                    break;

                case "z":
                    sortingTask =
                            () -> HeapSort.heapSort(products, comparator);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid sorting algorithm.");
            }

            long start = System.nanoTime();
            sortingTask.run();
            long end = System.nanoTime();

            double milliseconds = (end - start) / 1_000_000.0;

            System.out.println("File: " + fileName);
            System.out.println("Comparison: " + compareType);
            System.out.println("Sorting algorithm: " + sortType);
            System.out.println("Products: " + products.length);
            System.out.printf("Sorting time: %.3f ms%n", milliseconds);

            displayProducts(products);
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
     * Displays all small collections, or selected positions in larger ones.
     *
     * @param products the sorted product array
     */
    private static void displayProducts(Product[] products)
    {
        int count = products.length;

        if (count == 0)
        {
            System.out.println("No products to display.");
            return;
        }

        System.out.println("\nSorted products:");

        if (count <= 10)
        {
            for (int i = 0; i < count; i++)
            {
                System.out.println((i + 1) + ": " + products[i]);
            }
            return;
        }

        int interval = count <= 1000 ? 100 : 1000;

        System.out.println("1: " + products[0]);

        // Positions 100, 200, ... or 1000, 2000, ...
        for (int position = interval; position < count;
                position += interval)
        {
            System.out.println(
                    position + ": " + products[position - 1]);
        }

        System.out.println(count + ": " + products[count - 1]);
    }
}