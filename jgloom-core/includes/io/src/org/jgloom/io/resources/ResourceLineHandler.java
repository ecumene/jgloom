package org.jgloom.io.resources;

/**
 * Used to handle each line read by the {@link ResourceReader} individually
 */
public interface ResourceLineHandler {
    /**
     * Called when the {@link ResourceReader} reads a line
     * @param line Line read by the resource loader
     */
    void handleLine(String line);
}
