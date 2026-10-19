package products;

/**
 * Represents a pair of jeans. Uses an adjustment of 0.5.
 */
public class Jeans extends Product
{
	/** Adjustment applied to the price difference for jeans. */
	public static final double ADJUSTMENT = 0.5;

	/**
	 * Creates a Jeans product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Jeans( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.5 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}