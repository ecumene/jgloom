package jgloom.io.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * An interface representing a resource stored online at an internet address
 */
public interface WebResource extends Resource {
    /**
     * Creates a wrapper for an {@link InputStream} created from the given connection
     * @param connection Connection to the resource already created
     * @return Wrapper for the given connection's stream
     * @throws IllegalResourceException In case the resource's stream could not be retrieved
     */
    static WebResource createWebResource(URLConnection connection) throws IllegalResourceException {
        try {
            InputStream stream = connection.getInputStream();
            return () -> stream;
        } catch (IOException ex) {
            throw new IllegalResourceException("Failed to get connection's stream: " + connection.getURL().getPath());
        }
    }
    
    /**
     * Creates a wrapper for an {@link InputStream} created from a created URL connection
     * @param url Full URL (including http:// or https://) of the resource
     * @return Wrapper for a created connection to the given URL
     * @throws IllegalResourceException In case connecting to the web resource fails
     */
    static WebResource createWebResource(URL url) throws IllegalResourceException {
        try {
            return createWebResource(url.openConnection());
        } catch (IOException ex) {
            throw new IllegalResourceException("Failed to connect to web resource: " + url.getPath());
        }
    }
}
