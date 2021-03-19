package dad.javafx.weather;

/**
 * Exception throewd by weather forecast service
 * @author fvarrui
 */
public class WeatherServiceException extends Exception {
	private static final long serialVersionUID = 1787422247020350889L;

	public WeatherServiceException() {
	}

	public WeatherServiceException(String message) {
		super(message);
	}

	public WeatherServiceException(Throwable cause) {
		super(cause);
	}

	public WeatherServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeatherServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
