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
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param texture Texture to bind and upload data to
     * @param input Input stream to read RGB(A) image data from
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, GLTexture texture, InputStream input, ImageDataCallback callback) throws IOException {
        GLTextureContainer container = new GLTextureContainer(texture);
        FloatBuffer data = ImageDecoder.decodeImage(input, callback);
        container.bind(target);
        container.image2D(target, 0, callback.hasAlpha ? GL11.GL_RGBA8 : GL11.GL_RGB8, callback.width, callback.height, 0, callback.hasAlpha ? GL11.GL_RGBA : GL11.GL_RGB, GL11.GL_FLOAT, data);
        return texture;
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param input Input stream to read RGB(A) image data from
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, InputStream input, ImageDataCallback callback) throws IOException {
        return loadTexture(target, GLTexture.createTexture(), input, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param texture Texture to bind and upload data to
     * @param input Input stream to read RGB(A) image data from
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, GLTexture texture, InputStream input) throws IOException {
        return loadTexture(target, texture, input, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param input Input stream to read RGB(A) image data from
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, InputStream input) throws IOException {
        return loadTexture(target, GLTexture.createTexture(), input, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param texture Texture to bind and upload data to
     * @param input Input stream to read RGB(A) image data from
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(GLTexture texture, InputStream input, ImageDataCallback callback) throws IOException {
        return loadTexture(GL11.GL_TEXTURE_2D, texture, input, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param input Input stream to read RGB(A) image data from
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(InputStream input, ImageDataCallback callback) throws IOException {
        return loadTexture2D(GLTexture.createTexture(), input, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param texture Texture to bind and upload data to
     * @param input Input stream to read RGB(A) image data from
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(GLTexture texture, InputStream input) throws IOException {
        return loadTexture2D(texture, input, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param input Input stream to read RGB(A) image data from
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(InputStream input) throws IOException {
        return loadTexture2D(GLTexture.createTexture(), input, new ImageDataCallback());
    }
}
