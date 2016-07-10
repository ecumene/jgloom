package jgloom.io.resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for wrapping an {@link InputStream} for allowing loading from files, class-path resources, or from any
 * {@link InputStream} in one method
 */
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
    static Resource createResource(InputStream input) {
        return () -> input;
    }
}
