package products;

/**
 * Represents a pair of shoes. Uses an adjustment of 0.4.
 */
public class Shoes extends Product
{
	/** Adjustment applied to the price difference for shoes. */
	public static final double ADJUSTMENT = 0.4;

	/**
	 * Creates a Shoes product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Shoes( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.4 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}