package jgloom.io.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * An interface representing a resource stored in a file on the file-system
 */
public interface FileResource extends Resource {
    /**
     * Creates a wrapper for a {@link FileInputStream} for reading from the given {@link File}
     * @param file File to create a resource wrapper for
     * @throws InvalidResourceException In case the resource is invalid or does not exist
     */
    static FileResource createFileResource(File file) throws InvalidResourceException {
        FileInputStreamContainer stream = new FileInputStreamContainer();
        
        try {
            stream.s = new FileInputStream(file);
            FileResource resource = () -> stream.s;
            return resource;
        } catch (FileNotFoundException ex) {
            try {
                stream.s.close();
            } catch (Throwable t) {}
            throw new InvalidResourceException("Could not find file resource: " + file.getPath());
        }
    }
    
    /**
     * Only exists to fix possible resource leaks, ignore it unless you want a nice container
     */
    class FileInputStreamContainer {
        public FileInputStream s;
    }
}
