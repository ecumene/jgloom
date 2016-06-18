package jgloom.io.images;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

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
        container.bind(GL11.GL_TEXTURE_2D);
        container.image2D(GL11.GL_TEXTURE_2D, 0, callback.hasAlpha ? GL11.GL_RGBA8 : GL11.GL_RGB8, callback.width, callback.height, 0, callback.hasAlpha ? GL11.GL_RGBA : GL11.GL_RGB, GL11.GL_FLOAT, data);
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
