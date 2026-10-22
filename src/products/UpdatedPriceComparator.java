package products;

import java.util.Comparator;

/**
 * Compares two products by their updated price. Follows the Comparator
 * contract: negative if the first is less, zero if equal, positive if greater.
 */
public class UpdatedPriceComparator implements Comparator<Product>
{
	/**
	 * Compares two products by updated price.
	 * @param p1 the first product
	 * @param p2 the second product
	 * @return a negative, zero, or a positive as the first
	 *         product's updated price is less than, equal to, or greater
	 *         than the second's
	 */
	@Override
	public int compare( Product p1, Product p2 )
	{
		return Double.compare( p1.getUpdatedPrice(), p2.getUpdatedPrice() );
	}
}