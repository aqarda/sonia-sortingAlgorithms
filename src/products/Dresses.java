package products;

/**
 * Represents a dress. Uses an adjustment of 0.6.
 */
public class Dresses extends Product
{
	/** Adjustment applied to the price difference for dresses. */
	public static final double ADJUSTMENT = 0.6;

	/**
	 * Creates a Dresses product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Dresses( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.6 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}