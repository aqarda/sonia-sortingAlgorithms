
package products;

import java.util.Comparator;

/**
 * Compares two products by their original price. Follows the Comparator
 * contract: negative if the first is less, zero if equal, positive if greater.
 */
public class OriginalPriceComparator implements Comparator<Product>
{
	/**
	 * Compares two products by original price.
	 *
	 * @param p1 the first product
	 * @param p2 the second product
	 * @return a negative, zero, or a positive number as the first
	 *         products original price is less than, equal to, or greater
	 *         than the seconds
	 */
	@Override
	public int compare( Product p1, Product p2 )
	{
		return Double.compare( p1.getOriginalPrice(), p2.getOriginalPrice() );
	}
}