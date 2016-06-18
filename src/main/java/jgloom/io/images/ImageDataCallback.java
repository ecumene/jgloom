package jgloom.io.images;

/**
 * Manifold of data for receiving information about loaded images, such as the image's size and whether an alpha channel
 * exists
 */
public class ImageDataCallback {
    /**
     * Width of the loaded image in pixels
     */
    public int width;
    
    /**
     * Height of the loaded image in pixels
     */
    public int height;
    
    /**
     * Whether an alpha channel exists in the loaded image
     */
    public boolean hasAlpha;
}
