package jgloom.io.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Interface for wrapping an {@link InputStream} for allowing loading from files, class-path resources, or from any
 * {@link InputStream} in one method
 */
@FunctionalInterface
public interface Resource {
    /**
     * @return {@link InputStream} wrapped by the object from which a resource can be read
     */
    InputStream getInputStream();
    
    /**
     * Shorthand for closing the wrapped {@link InputStream}
     * @throws IOException In case closing fails (amazing, right?)
     */
    default void close() throws IOException {
        getInputStream().close();
    }
    
    /**
     * Creates a simple resource object from the given {@link InputStream}
     * @param input InputStream to create a simple wrapper for
     * @return Simple wrapper pointing to the given InputStream
     */
    public static Resource createResource(InputStream input) {
        return () -> input;
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by the given class loader
     * @param loader {@link ClassLoader} to use to find the requested class-path resource
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    @SuppressWarnings("unused")
    public static Resource createClasspathResource(ClassLoader loader, String path) throws InvalidResourceException {
        InputStream stream = loader.getResourceAsStream(path);
        if (path == null)
            throw new InvalidResourceException("Could not find classpath resource: " + path);
        return () -> loader.getResourceAsStream(path);
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by this classes class loader
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public static Resource createClasspathResource(String path) throws InvalidResourceException {
        return createClasspathResource(Resource.class.getClassLoader(), path);
    }
    
    /**
     * Creates a wrapper for a {@link FileInputStream} for reading from the given {@link File}
     * @param file File to create a resource wrapper for
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    @SuppressWarnings("resource")
    public static Resource createFileResource(File file) throws InvalidResourceException {
        try {
            InputStream stream = new FileInputStream(file);
            return () -> stream;
        } catch (FileNotFoundException ex) {
            throw new InvalidResourceException("Could not find file resource: " + file.getPath());
        }
    }
    
    /**
     * Creates a wrapper for an {@link InputStream} created from the given connection
     * @param connection Connection to the resource already created
     * @return Wrapper for the given connection's stream
     * @throws InvalidResourceException In case the resource's stream could not be retrieved
     */
    public static Resource createWebResource(URLConnection connection) throws InvalidResourceException {
        try {
            InputStream stream = connection.getInputStream();
            return () -> stream;
        } catch (IOException ex) {
            throw new InvalidResourceException("Failed to get connection's stream: " + connection.getURL().getPath());
        }
    }
    
    /**
     * Creates a wrapper for an {@link InputStream} created from a created URL connection
     * @param url Full URL (including http:// or https://) of the resource
     * @return Wrapper for a created connection to the given URL
     * @throws InvalidResourceException In case connecting to the web resource fails
     */
    public static Resource createWebResource(URL url) throws InvalidResourceException {
        try {
            return createWebResource(url.openConnection());
        } catch (IOException ex) {
            throw new InvalidResourceException("Failed to connect to web resource: " + url.getPath());
        }
    }
}
