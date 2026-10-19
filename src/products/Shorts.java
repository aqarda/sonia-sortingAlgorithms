package products;

/**
 * Represents a pair of shorts. Uses an adjustment of 0.3.
 */
public class Shorts extends Product
{
	/** Adjustment applied to the price difference for shorts. */
	public static final double ADJUSTMENT = 0.3;

	/**
	 * Creates a Shorts product.

	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Shorts( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.3 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}