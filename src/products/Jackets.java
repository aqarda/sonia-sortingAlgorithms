package products;

/**
 * Represents a jacket. Uses an adjustment of 0.7.
 */
public class Jackets extends Product
{
	/** Adjustment applied to the price difference for jackets. */
	public static final double ADJUSTMENT = 0.7;

	/**
	 * Creates a Jackets product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Jackets( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.7 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}