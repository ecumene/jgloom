package jgloom.io.resources;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * An interface representing a resource stored either on the current class-path or the classppath of the given loader
 */
public interface ClasspathResource extends Resource {
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by the given class loader
     * @param loader {@link ClassLoader} to use to find the requested class-path resource
     * @param path Path to the resource, relative or absolute
     * @throws IllegalResourceException In case the resource is invalid or does not exist
     */
    static ClasspathResource createClasspathResource(ClassLoader loader, String path) throws IllegalResourceException {
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null)
            throw new IllegalResourceException("Could not find classpath resource: " + path);
        return () -> stream;
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by this classes class loader
     * @param path Path to the resource, relative or absolute
     * @throws IllegalResourceException In case the resource is invalid or does not exist
     */
    static ClasspathResource createClasspathResource(String path) throws IllegalResourceException {
        return createClasspathResource(Resource.class.getClassLoader(), path);
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; creates a class loader for the given JAR file
     * @param jarFile URL for the JAR file containing the resource
     * @param path Path to the resource, relative or absolute
     * @throws IllegalResourceException In case the resource is invalid or does not exist
     */
    static ClasspathResource createClasspathResource(URL jarFile, String path) throws IllegalResourceException {
        return createClasspathResource(new URLClassLoader(new URL[] { jarFile }), path);
    }
}
