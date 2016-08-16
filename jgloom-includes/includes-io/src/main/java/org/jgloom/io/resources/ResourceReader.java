package org.jgloom.io.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class of static utilities for reading the contents of a given {@link Resource}
 */
public class ResourceReader {
    /**
     * Reads the given {@link Resource} line by line and calls the given handler for every line; DOES NOT CLOSE THE
     * RESOURCE'S INPUTSTREAM
     * @param resource Resource to read line by line
     * @param handler Handler to call for every line read
     * @throws IOException In case reading of the resource fails
     */
    public static void readIndividualLines(Resource resource, ResourceLineHandler handler) throws IOException {
        InputStream stream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null)
            handler.handleLine(line);
    }
    
    /**
     * Reads and assembles the entire {@link Resource} into a string; DOES NOT CLOSE THE RESOURCE'S INPUTSTREAM
     * @param resource Resource to read entirely
     * @return Contents of the given resource as a string
     * @throws IOException In case reading of the resource fails
     */
    public static String readWhole(Resource resource) throws IOException {
        StringBuilder builder = new StringBuilder();
        
        ResourceLineHandler handler = (String line) -> {
            builder.append("\n").append(line);
        };
        
        readIndividualLines(resource, handler);
        return builder.toString().substring(1);
    }
}
