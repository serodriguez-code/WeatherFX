package dad.javafx.weather;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * Weather information for an specific location
 * 
 * @author fvarrui
 *
 */
public class Weather {

	/**
	 * Name of the location
	 */
	private String location;

	/**
	 * Latitude of the lcation
	 */
	private double latitude;

	/**
	 * Logitude of the location
	 */
	private double longitude;

	/**
	 * Date/time in location (zulu)
	 */
	private ZonedDateTime time;

	/**
	 * Sunrise time (zulu)
	 */
	private ZonedDateTime sunrise;

	/**
	 * Sunset time (zulu)
	 */
	private ZonedDateTime sunset;

	/**
	 * Time zone
	 */
	private String timeZone;

	/**
	 * Weather state
	 */
	private WeatherState state;

	/**
	 * State icon image url
	 */ 
	private URL stateIconUrl;

	/**
	 * Wind info
	 */
	private Wind wind;

	/**
	 * Temperature (centigrade)
	 */
	private Double temperature;

	/**
	 * Minimum temperature (centigrade)
	 */
	private Double minTemperature;

	/**
	 * Maximum temperature (centigrade)
	 */
	private Double maxTemperature;

	/**
	 * Air pressure (mbar)
	 */
	private Double airPressure;

	/**
	 * Humidity (percentage)
	 */
	private Long humidity;

	/**
	 * Visibility (kms)
	 */
	private Double visibility;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public ZonedDateTime getTime() {
		return time;
	}

	public void setTime(ZonedDateTime time) {
		this.time = time;
	}

	public ZonedDateTime getSunrise() {
		return sunrise;
	}

	public void setSunrise(ZonedDateTime sunrise) {
		this.sunrise = sunrise;
	}

	public ZonedDateTime getSunset() {
		return sunset;
	}

	public void setSunset(ZonedDateTime sunset) {
		this.sunset = sunset;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public WeatherState getState() {
		return state;
	}

	public void setState(WeatherState state) {
		this.state = state;
	}

	public URL getStateIconUrl() {
		return stateIconUrl;
	}

	public void setStateIconUrl(URL stateIconUrl) {
		this.stateIconUrl = stateIconUrl;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public Double getAirPressure() {
		return airPressure;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	public Long getHumidity() {
		return humidity;
	}

	public void setHumidity(Long humidity) {
		this.humidity = humidity;
	}

	public Double getVisibility() {
		return visibility;
	}

	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}

	@Override
	public String toString() {
		return "Weather [" + "\n"
					+ "\t" + "location=" + location + ", " + "\n"
					+ "\t" + "latitude=" + latitude + ", " + "\n"
					+ "\t" + "longitude=" + longitude + ", " + "\n"
					+ "\t" + "time=" + time + ", " + "\n"
					+ "\t" + "sunrise=" + sunrise + ", " + "\n"
					+ "\t" + "sunset=" + sunset + ", " + "\n"
					+ "\t" + "timeZone=" + timeZone + ", " + "\n"
					+ "\t" + "state=" + state + ", " + "\n"
					+ "\t" + "stateIconUrl=" + stateIconUrl + ", " + "\n"
					+ "\t" + "wind=" + wind + ", " + "\n"
					+ "\t" + "temperature=" + temperature + ", " + "\n"
					+ "\t" + "minTemperature=" + minTemperature + ", " + "\n"
					+ "\t" + "maxTemperature=" + maxTemperature + ", " + "\n"
					+ "\t" + "airPressure=" + airPressure + ", " + "\n"
					+ "\t" + "humidity=" + humidity + ", " + "\n"
					+ "\t" + "visibility=" + visibility + "\n"
				+ "]";
	}

}
