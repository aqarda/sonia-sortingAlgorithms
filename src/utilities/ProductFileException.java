package utilities;

/**
 * <p>
 * Signals that a product data file could not be read or that its contents
 * are unusable (for example, the file does not exist or the first line does
 * not contain a valid object count).
 * </p>
 * <p>
 * The message of this exception is written to be shown directly to the user.
 * </p>
 */
public class ProductFileException extends Exception
{
	/** Version number used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates an exception with a user-friendly message.
	 *
	 * @param message A description of what went wrong.
	 */
	public ProductFileException( String message )
	{
		super( message );
	}

	/**
	 * Creates an exception with a user-friendly message and the underlying cause.
	 *
	 * @param message A description of what went wrong.
	 * @param cause   The original exception that triggered this one.
	 */
	public ProductFileException( String message, Throwable cause )
	{
		super( message, cause );
	}
}
