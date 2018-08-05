package api.geo;

import java.util.Objects;

/**
 * Geo coordinates of a location.
 */
public final class Location {

    /**
     * Latitude of location.
     */
    public final double lat;
    /**
     * Longitude of location.
     */
    public final double lng;

    /**
     * Constructor for location data.
     * @param lat Latitude of location.
     * @param lng Longitude of location.
     */
    public Location(final double lat, final double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
