package products;

/**
 * Represents a skirt. Uses an adjustment of 0.1.
 */
public class Skirts extends Product
{
	/** Adjustment applied to the price difference for skirts. */
	public static final double ADJUSTMENT = 0.1;

	/**
	 * Creates a Skirts product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public Skirts( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.1 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}