package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import products.Dresses;
import products.Jackets;
import products.Jeans;
import products.Product;
import products.Shoes;
import products.Shorts;
import products.Skirts;
import products.TShirts;

/**
 * <p>
 * Reads a product data file and converts it into an array of {@link Product}
 * objects.
 * </p>
 * <p>
 * The expected file format is:
 * </p>
 * <ul>
 * <li>The first line holds the number of objects in the file.</li>
 * <li>Every following line holds a product type (Jeans, TShirts, Skirts,
 * Dresses, Shorts, Jackets or Shoes), the original price and the updated
 * price, separated by spaces. Example: <code>Shorts 5.25 23.36</code></li>
 * </ul>
 * <p>
 * The products are stored in a plain array (not an ArrayList).
 * </p>
 */
public class ProductFileReader
{
	/** Not meant to be instantiated; all methods are static. */
	private ProductFileReader()
	{
	}

	/**
	 * Reads the given file and returns its products in an array, in the same
	 * order as they appear in the file.
	 * <ul>
	 * <li>A missing/unreadable file or an invalid first line throws a
	 * {@link ProductFileException}.</li>
	 * <li>A malformed product line (wrong number of values, unknown product
	 * type, non-numeric or negative price) is skipped and a warning is printed
	 * to the error stream.</li>
	 * <li>Lines beyond the count given in the first line are ignored.</li>
	 * <li>If fewer valid products are found than the first line promised, the
	 * returned array is shortened to the number actually read.</li>
	 * </ul>
	 *
	 * @param fileName The file name and path of the data file.
	 * @return An array holding the products that were read; never null.
	 * @throws ProductFileException If the file cannot be read or its first line
	 *                              is not a valid non-negative whole number.
	 */
	public static Product[] readProducts( String fileName ) throws ProductFileException
	{
		if ( fileName == null || fileName.trim().isEmpty() )
		{
			throw new ProductFileException( "No file name was provided." );
		}

		Path path;
		try
		{
			path = Paths.get( fileName );
		}
		catch ( RuntimeException e )
		{
			throw new ProductFileException( "The file path \"" + fileName + "\" is not valid.", e );
		}

		if ( Files.isDirectory( path ) )
		{
			throw new ProductFileException( "\"" + fileName + "\" is a folder, not a data file." );
		}

		try ( BufferedReader reader = Files.newBufferedReader( path, StandardCharsets.UTF_8 ) )
		{
			int expected = readCount( reader, fileName );
			Product[] products = new Product[expected];
			int stored = 0;
			int lineNumber = 1;
			String line;

			while ( ( line = reader.readLine() ) != null )
			{
				lineNumber++;
				if ( line.trim().isEmpty() )
				{
					continue; // ignore blank lines
				}

				if ( stored == expected )
				{
					System.err.println( "Warning: file has more products than the " + expected
							+ " stated on line 1; ignoring line " + lineNumber + " onward." );
					break;
				}

				try
				{
					products[stored] = parseProduct( line );
					stored++;
				}
				catch ( IllegalArgumentException e )
				{
					System.err.println( "Warning: skipping line " + lineNumber + " (" + e.getMessage() + "): " + line.trim() );
				}
			}

			if ( stored < expected )
			{
				System.err.println( "Warning: line 1 says " + expected + " products but only " + stored
						+ " valid products were found." );
				products = Arrays.copyOf( products, stored );
			}
			return products;
		}
		catch ( NoSuchFileException e )
		{
			throw new ProductFileException( "File not found: \"" + fileName + "\". Check the name and path.", e );
		}
		catch ( IOException e )
		{
			throw new ProductFileException( "Could not read \"" + fileName + "\": " + e.getMessage(), e );
		}
	}

	/**
	 * Reads and validates the first line of the file, which holds the number of
	 * objects.
	 *
	 * @param reader   The reader positioned at the start of the file.
	 * @param fileName The file name, used for error messages.
	 * @return The number of objects the file claims to hold.
	 * @throws IOException          If reading fails.
	 * @throws ProductFileException If the file is empty or the first line is not
	 *                              a valid non-negative whole number.
	 */
	private static int readCount( BufferedReader reader, String fileName ) throws IOException, ProductFileException
	{
		String first = reader.readLine();
		if ( first == null )
		{
			throw new ProductFileException( "The file \"" + fileName + "\" is empty." );
		}

		// remove a byte-order mark if the file was saved with one
		first = first.replace( "\uFEFF", "" ).trim();

		try
		{
			int count = Integer.parseInt( first );
			if ( count < 0 )
			{
				throw new ProductFileException( "The object count on line 1 of \"" + fileName + "\" cannot be negative." );
			}
			return count;
		}
		catch ( NumberFormatException e )
		{
			throw new ProductFileException(
					"Line 1 of \"" + fileName + "\" must be the number of objects, but it was \"" + first + "\".", e );
		}
	}

	/**
	 * Converts one data line into the matching {@link Product} subclass.
	 *
	 * @param line A line such as <code>Jeans 7.83 71.4</code>.
	 * @return The new product object.
	 * @throws IllegalArgumentException If the line does not have exactly three
	 *                                  values, the type is unknown, or a price is
	 *                                  not a valid non-negative number.
	 */
	private static Product parseProduct( String line )
	{
		String[] parts = line.trim().split( "\\s+" );
		if ( parts.length != 3 )
		{
			throw new IllegalArgumentException( "expected: <type> <original price> <updated price>" );
		}

		double original = parsePrice( parts[1] );
		double updated = parsePrice( parts[2] );

		switch ( parts[0].toLowerCase() )
		{
			case "jeans":
				return new Jeans( original, updated );
			case "tshirts":
				return new TShirts( original, updated );
			case "skirts":
				return new Skirts( original, updated );
			case "dresses":
				return new Dresses( original, updated );
			case "shorts":
				return new Shorts( original, updated );
			case "jackets":
				return new Jackets( original, updated );
			case "shoes":
				return new Shoes( original, updated );
			default:
				throw new IllegalArgumentException( "unknown product type \"" + parts[0] + "\"" );
		}
	}

	/**
	 * Converts text into a valid price.
	 *
	 * @param text The text to convert.
	 * @return The price as a double.
	 * @throws IllegalArgumentException If the text is not a finite, non-negative
	 *                                  number.
	 */
	private static double parsePrice( String text )
	{
		double value;
		try
		{
			value = Double.parseDouble( text );
		}
		catch ( NumberFormatException e )
		{
			throw new IllegalArgumentException( "\"" + text + "\" is not a number" );
		}
		if ( Double.isNaN( value ) || Double.isInfinite( value ) || value < 0 )
		{
			throw new IllegalArgumentException( "invalid price \"" + text + "\"" );
		}
		return value;
	}
}
