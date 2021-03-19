package dad.javafx.weather;

/**
 * Wind forecast
 * @author fvarrui
 */
public class Wind {

	/**
	 * Wind speed (km/h)
	 */
	private Double speed;

	/**
	 * Compass point of the wind direction
	 */
	private CompassPoint directionCompass;

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public CompassPoint getDirectionCompass() {
		return directionCompass;
	}

	public void setDirectionCompass(CompassPoint directionCompass) {
		this.directionCompass = directionCompass;
	}

	@Override
	public String toString() {
		return "Wind [speed=" + speed + ",directionCompass=" + directionCompass + "]";
	}

}
