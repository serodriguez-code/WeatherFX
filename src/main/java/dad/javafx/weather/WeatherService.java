package dad.javafx.weather;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.weather.api.MetaWeatherApi;
import dad.javafx.weather.api.model.ConsolidatedWeather;
import dad.javafx.weather.api.model.Location;

/**
 * Weather forecast service, used to get forecast data 
 * @author fvarrui
 */
public class WeatherService {
	
	private static final Double MILE_TO_KILOMETER = 1.60934;
	
	private static final MetaWeatherApi api = new MetaWeatherApi(); 
	
	/**
	 * Get the weather for the location (city, province, state, ...) indicated
	 * @param location Location to get forecast
	 * @return Weather Current weather
	 * @throws WeatherServiceException Exception thrown on failure to retrieve forecast 
	 */
	public static Weather getWeather(String location) throws WeatherServiceException {
		
		try {
			
			List<Location> locations = api.searchLocation(location);
			
			if (locations.isEmpty()) {
				throw new WeatherServiceException("Location not found: " + location);
			}
			
			Long woeid = locations.get(0).getWoeid();
			
			dad.javafx.weather.api.model.Weather apiWeather = api.getWeather(woeid);

			ConsolidatedWeather consolidated = apiWeather.getConsolidatedWeather().get(0); // 0 = today, 1 = tomorrow

			Wind wind = new Wind();
			wind.setSpeed(consolidated.getWindSpeed() * MILE_TO_KILOMETER);
			wind.setDirectionCompass(asCompassPoint(consolidated.getWindDirectionCompass()));
			
			String [] geolocation = apiWeather.getLattLong().split(",");
			
			Weather weather = new Weather();
			weather.setLocation(apiWeather.getTitle());
			weather.setTime(ZonedDateTime.parse(apiWeather.getTime()));
			weather.setSunrise(ZonedDateTime.parse(apiWeather.getSunRise()));
			weather.setSunset(ZonedDateTime.parse(apiWeather.getSunSet()));
			weather.setTimeZone(TimeZone.getTimeZone(apiWeather.getTimezone()).getID());
			weather.setLatitude(Double.parseDouble(geolocation[0]));
			weather.setLongitude(Double.parseDouble(geolocation[1]));
			
			weather.setState(asWeatherState(consolidated.getWeatherStateAbbr()));
			weather.setStateIconUrl(api.getWeatherStateIcon(consolidated.getWeatherStateAbbr()));
			weather.setWind(wind);
			weather.setTemperature(consolidated.getTheTemp());
			weather.setMinTemperature(consolidated.getMinTemp());
			weather.setMaxTemperature(consolidated.getMaxTemp());
			weather.setVisibility(consolidated.getVisibility() * MILE_TO_KILOMETER);
			weather.setHumidity(consolidated.getHumidity());
			weather.setAirPressure(consolidated.getAirPressure());
			
			return weather;
			
		} catch (UnirestException e) {

			throw new WeatherServiceException("MetaWeather API call exception: " + e.getMessage(), e);
			
		}

	}
	
	private static WeatherState asWeatherState(String weatherStateAbbr) {
		switch (weatherStateAbbr) {
		case "sn":	return WeatherState.SNOW;
		case "sl":	return WeatherState.SLEET;
		case "h":	return WeatherState.HAIL;
		case "t":	return WeatherState.THUNDERSTORM;
		case "hr":	return WeatherState.HEAVY_RAIN;
		case "lr":	return WeatherState.LIGHT_RAIN;
		case "s":	return WeatherState.SHOWERS;
		case "hc":	return WeatherState.HEAVY_CLOUD;
		case "lc":	return WeatherState.LIGHT_CLOUD;
		case "c":	return WeatherState.CLEAR;
		}
		return null;
	}
	
	private static CompassPoint asCompassPoint(String windDirectionCompass) {
		switch (windDirectionCompass) {
		case "N":	return CompassPoint.N;
		case "NNE":	return CompassPoint.NNE;
		case "NE":	return CompassPoint.NE;
		case "ENE":	return CompassPoint.ENE;
		case "E":	return CompassPoint.E;
		case "ESE":	return CompassPoint.ESE;
		case "SE":	return CompassPoint.SE;
		case "SSE":	return CompassPoint.SSE;
		case "S":	return CompassPoint.S;
		case "SSW":	return CompassPoint.SSW;
		case "SW":	return CompassPoint.SW;
		case "WSW":	return CompassPoint.WSW;
		case "W":	return CompassPoint.W;
		case "WNW":	return CompassPoint.WNW;
		case "NW":	return CompassPoint.NW;
		case "NNW":	return CompassPoint.NNW;		
		}
		return null;
	}
	
	public static void main(String[] args) throws WeatherServiceException {
		Weather weather = WeatherService.getWeather("Santa Cruz de Tenerife");
		System.out.println(weather);
	}

}
