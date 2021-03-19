package dad.javafx.weather;

/**
 * Weather states enum
 * @author fvarrui
 */
public enum WeatherState {
	SNOW("Snow"),
	SLEET("Sleet"),
	HAIL("Hail"),
	THUNDERSTORM("Thunderstorm"),
	HEAVY_RAIN("Heavy rain"),
	LIGHT_RAIN("Light rain"),
	SHOWERS("Showers"),
	HEAVY_CLOUD("Heavy cloud"),
	LIGHT_CLOUD("Light cloud"),
	CLEAR("Clear");
	
    private final String state;

    WeatherState(final String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state;
    }
    
}
