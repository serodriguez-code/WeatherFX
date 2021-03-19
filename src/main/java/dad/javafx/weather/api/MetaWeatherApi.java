package dad.javafx.weather.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.weather.api.model.Location;
import dad.javafx.weather.api.model.Weather;

/**
 * Class used to retrieve data from MetaWeather REST API 
 * @author fvarrui
 */
public class MetaWeatherApi {
	
	public static final String ENDPOINT = "https://www.metaweather.com";
	
	public MetaWeatherApi() {
		Unirest.setObjectMapper(new JacksonObjectMapper());
	}
	
	/**
	 * Search locations by name in MetaWeather
	 * @param query Name or part of the name of the location
	 * @return A list with the locations found
	 * @throws UnirestException Exception thrown in case of not being able to access the MetaWeather REST API 
	 */
	public List<Location> searchLocation(String query) throws UnirestException {
		
		Location [] locations = Unirest
			.get(ENDPOINT + "/api/location/search/?query={query}")
			.routeParam("query", query)
			.asObject(Location[].class)
			.getBody();
		
		return Arrays.asList(locations);
		
	}
	
	/**
	 * Retrieve weather forecast data for an specific location from MetaWeather  
	 * @param woeid Where On Eaerth IDentifier
	 * @return Weather forecast for the location
	 * @throws UnirestException Exception thrown in case of not being able to access the MetaWeather REST API
	 */
	public Weather getWeather(Long woeid) throws UnirestException {

		return Unirest
				.get(ENDPOINT + "/api/location/{woeid}/")
				.routeParam("woeid", "" + woeid)
				.asObject(Weather.class)
				.getBody();
		
	}
	
	/**
	 * Generates the URL for the weather icon (64 pixels)
	 * @param stateAbbr Weather state 
	 * @return URL for the weather icon
	 */
	public URL getWeatherStateIcon(String stateAbbr) {
		try {
			return new URL(ENDPOINT + "/static/img/weather/png/64/" + stateAbbr + ".png");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}	

}
