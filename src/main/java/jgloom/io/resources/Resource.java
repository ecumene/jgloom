package jgloom.io.resources;

import java.io.InputStream;

/**
 * Interface for wrapping an {@link InputStream} for allowing loading from files, class-path resources, or from any
 * {@link InputStream} in one method
 */
@FunctionalInterface
public interface Resource {
    /**
     * @return {@link InputStream} wrapped by the object from which a resource can be read
     */
    public InputStream getInputStream();
    
    /**
     * Creates a simple resource object from the given {@link InputStream}
     * @param input InputStream to create a simple wrapper for
     * @return Simple wrapper pointing to the given InputStream
     */
    public static Resource createResource(InputStream input) {
        return () -> input;
    }
}
