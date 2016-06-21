package jgloom.io.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Implementation of {@link Resource} for reading data from {@link File} objects; wraps a {@link FileInputStream}
 */
public class FileResource implements Resource {
    private FileInputStream input;
    
    /**
     * Creates a wrapper for a {@link FileInputStream} for reading from the given {@link File}
     * @param file File to create a resource wrapper for
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    public FileResource(File file) throws InvalidResourceException
    {
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            throw new InvalidResourceException("File resource not found: " + file.getPath());
        }
    }
    
    @Override
    public InputStream getInputStream() {
        return input;
    }
}
