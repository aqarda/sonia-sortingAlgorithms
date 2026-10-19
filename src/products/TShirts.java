package products;

/**
 * Represents a t-shirt. Uses an adjustment of 0.2.
 */
public class TShirts extends Product
{
	/** Adjustment applied to the price difference for t-shirts. */
	public static final double ADJUSTMENT = 0.2;

	/**
	 * Creates a TShirts product.
	 * @param originalPrice the original price
	 * @param updatedPrice  the updated price
	 */
	public TShirts( double originalPrice, double updatedPrice )
	{
		super( originalPrice, updatedPrice );
	}

	/**
	 * Calculates 0.2 * |originalPrice - updatedPrice|.
	 * @return the adjusted real difference
	 */
	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}