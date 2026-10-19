package products;

/**
 * <p>
 * Abstract base class a clothing product with an original price
 * and an updated price. Subclasses have their own price adjustment
 * {@link #calcRealDiff()}.
 * </p>
 * <p>
 * Products are ordered by their calculated real difference
 * ({@link #compareTo(Product)}).
 * </p>
 */
public abstract class Product implements Comparable<Product>
{
	private double originalPrice;
	private double updatedPrice;

	/**
	 * Creates a product with the original and updated prices.
	 * @param originalPrice the original price of the product
	 * @param updatedPrice  the updated price of the product
	 * @throws IllegalArgumentException if either price is negative or not a number
	 */
	public Product( double originalPrice, double updatedPrice )
	{
		if( Double.isNaN( originalPrice ) || Double.isNaN( updatedPrice ) )
		{
			throw new IllegalArgumentException( "Prices must be valid numbers." );
		}
		if( originalPrice < 0 || updatedPrice < 0 )
		{
			throw new IllegalArgumentException( "Prices cannot be negative." );
		}
		this.originalPrice = originalPrice;
		this.updatedPrice = updatedPrice;
	}

	/**
	 * Returns the original price of the product.
	 * @return the original price
	 */
	public double getOriginalPrice()
	{
		return originalPrice;
	}

	/**
	 * Returns the updated price of the product.
	 * @return the updated price
	 */
	public double getUpdatedPrice()
	{
		return updatedPrice;
	}

	/**
	 * Calculates the difference between the original and updated
	 * prices = adjustment * |originalPrice - updatedPrice|, where the
	 * adjustment is based on the product type.
	 * @return the adjusted real difference
	 */
	public abstract double calcRealDiff();

	/**
	 * Compares this product to another product by their real difference.
	 * Follows the Comparable: returns a negative value if the products
	 * difference is less, zero if equal, and a positive value when greater
	 * 
	 *
	 * @param other the product to compare against
	 * @return a negative integer, zero, or a positive integer as this product's
	 *         real difference is less than, equal to, or greater than the other's
	 */
	@Override
	public int compareTo( Product other )
	{
		return Double.compare( this.calcRealDiff(), other.calcRealDiff() );
	}

	/**
	 * Returns string with the product type and its prices.
	 *
	 * @return description of the product
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + " Original: " + originalPrice
				+ " Updated: " + updatedPrice + " RealDiff: " + calcRealDiff();
	}
}