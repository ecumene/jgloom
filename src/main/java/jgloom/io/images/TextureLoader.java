package jgloom.io.images;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import jgloom.common.gl.GLTextureContainer;
import jgloom.gl.GLTexture;
import jgloom.io.images.decoding.ImageDecoder;

/**
 * Creates a {@link GLTexture} from loaded image data
 */
public class TextureLoader {
    /**
     * Generates a {@link GLTexture}, loads data from the given {@link InputStream}, and uploads the data to the
     * texture; calls back image information through the given {@link ImageDataCallback}
     * @param input {@link InputStream} to read image data from
     * @param callback Object to pass image width, height, and hasAlpha through
     * @return Generated {@link GLTexture} with uploaded image data
     * @throws IOException In case image loading fails
     */
    public static synchronized GLTexture loadTexture(InputStream input, ImageDataCallback callback) throws IOException {
        GLTexture texture = GLTexture.createTexture();
        GLTextureContainer container = new GLTextureContainer(texture);
        FloatBuffer data = ImageDecoder.decodeImage(input, callback);
        // TODO: Upload data to texture
        return texture;
    }
    
    /**
     * Generates a {@link GLTexture}, loads data from the given {@link InputStream}, and uploads the data to the
     * texture
     * @param input {@link InputStream} to read image data from
     * @return Generated {@link GLTexture} with uploaded image data
     * @throws IOException In case image loading fails
     */
    public static synchronized GLTexture loadTexture(InputStream input) throws IOException {
        return loadTexture(input, new ImageDataCallback());
    }
}
