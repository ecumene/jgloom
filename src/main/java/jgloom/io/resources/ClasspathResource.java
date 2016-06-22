package jgloom.io.resources;

import java.io.InputStream;

/**
 * An interface representing a resource stored either on the current class-path or the classppath of the given loader
 */
public interface ClasspathResource extends Resource {
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by the given class loader
     * @param loader {@link ClassLoader} to use to find the requested class-path resource
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public static ClasspathResource createClasspathResource(ClassLoader loader, String path) throws InvalidResourceException {
        InputStream stream = loader.getResourceAsStream(path);
        if (stream == null)
            throw new InvalidResourceException("Could not find classpath resource: " + path);
        return () -> stream;
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by this classes class loader
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public static ClasspathResource createClasspathResource(String path) throws InvalidResourceException {
        return createClasspathResource(Resource.class.getClassLoader(), path);
    }
}
