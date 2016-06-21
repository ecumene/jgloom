package jgloom.io.resources;

import java.io.InputStream;

/**
 * Implementation of {@link Resource} for reading data from a class-path resource
 */
public class ClasspathResource implements Resource {
    private InputStream input;
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by the given class loader
     * @param loader {@link ClassLoader} to use to find the requested class-path resource
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public ClasspathResource(ClassLoader loader, String path) throws InvalidResourceException {
        input = getClass().getClassLoader().getResourceAsStream(path);
        if (input == null)
            throw new InvalidResourceException("Classpath resource not found: " + path);
    }
    
    /**
     * Creates a wrapper for the given resource as a stream; stream is created by this classes class loader
     * @param path Path to the resource, relative or absolute
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public ClasspathResource(String path) throws InvalidResourceException {
        this(ClasspathResource.class.getClassLoader(), path);
    }
    
    @Override
    public InputStream getInputStream() {
        return input;
    }
}
