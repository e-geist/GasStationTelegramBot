package api.geo;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

/**
 * Class for fetching geo coordinates for a location.
 */
public final class GeocodingData {

    /**
     * Instance of class as it provides the functionality through a static method -> singleton.
     */
    private static GeocodingData instance = null;

    /**
     * Key for API that is used to get the coordinates for a location.
     */
    private static String apiKey = null;

    /**
     * Context that is needed to use the geo api.
     */
    private final GeoApiContext context;

    /**
     * Sets Key for the used geo api.
     * @param apiKey API-Key that shall be used for the geo api.
     */
    public static final void setApiKey(final String apiKey) {
        GeocodingData.apiKey = apiKey;
    }

    /**
     * Fetches the Location for a given location.
     * @param location Location whose coordinates shall be fetched.
     * @return Instance of Location with longitude and latitude of location
     *         if retrieving them was successful, otherwise null.
     */
    public static Location getGeoCoordsForLocation(final String location) {

        // Create instance of GeocodingData if there is none yet.
        if (instance == null) {
            instance = new GeocodingData();
        }

        // Fetch coordinates.
        GeocodingResult[] results = getGeoCoordinatesFromApi(location);

        // Wrap geo coordinates in Location class.
        Location result = null;
        if (results != null) {
            result = new Location(results[0].geometry.location.lat, results[0].geometry.location.lng);
        }

        return result;
    }

    /**
     * Fetches the geo coordinates for a location through an API.
     * @param location Location whose coordinates shall be fetched.
     * @return Array of geo coordinates from API for given location, if retrieving was successful,
     *         otherwise null.
     */
    private static GeocodingResult[] getGeoCoordinatesFromApi(final String location) {
        GeocodingResult[] results = null;
        try {
            results = GeocodingApi.geocode(instance.context, location).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * GeocodingData constructor that creates an instance of context with current apiKey.
     */
    private GeocodingData() {
        context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

}
