package dad.javafx.weather;

/**
 * Compass point (for wind direction)
 * @author fvarrui
 */
public enum CompassPoint {
	N("North"),
	NNE("North North East"),
	NE("North East"),
	ENE("East North East"),
	E("East"),
	ESE("East South East"),
	SE("South East"),
	SSE("South South East"),
	S("South"),
	SSW("South South West"),
	SW("South West"),
	WSW("West South West"),
	W("West"),
	WNW("West North West"),
	NW("North West"),
	NNW("North North West");
	
    private final String compass;

    CompassPoint(final String compass) {
        this.compass = compass;
    }

    @Override
    public String toString() {
        return compass;
    }

}
