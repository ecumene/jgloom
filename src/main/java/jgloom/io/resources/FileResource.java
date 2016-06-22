package jgloom.io.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * An interface representing a resource stored in a file on the file-system
 */
public interface FileResource extends Resource {
    /**
     * Creates a wrapper for a {@link FileInputStream} for reading from the given {@link File}
     * @param file File to create a resource wrapper for
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    @SuppressWarnings("resource")
    public static FileResource createFileResource(File file) throws InvalidResourceException {
        try {
            InputStream stream = new FileInputStream(file);
            return () -> stream;
        } catch (FileNotFoundException ex) {
            throw new InvalidResourceException("Could not find file resource: " + file.getPath());
        }
    }
}
