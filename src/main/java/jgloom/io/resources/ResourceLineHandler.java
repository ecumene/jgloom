package jgloom.io.resources;

/**
 * Used to handle each line read by the {@link ResourceReader} individually
 */
@FunctionalInterface
public interface ResourceLineHandler {
    /**
     * Called when the {@link ResourceLoader} reads a line
     * @param line Line read by the resource loader
     */
    public void handleLine(String line);
}
