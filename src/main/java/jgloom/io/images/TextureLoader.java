package jgloom.io.images;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

import jgloom.gl.GLTexture;
import jgloom.io.images.decoding.ImageDecoder;
import jgloom.io.resources.Resource;
import jgloom.lwjgl.gl.GLTextureContainer;

/**
 * Creates a {@link GLTexture} from loaded image data
 */
public class TextureLoader {
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param texture Texture to bind and upload data to
     * @param resource {@link Resource} containing RGB(A) image data
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, GLTexture texture, Resource resource, ImageDataCallback callback) throws IOException {
        GLTextureContainer container = new GLTextureContainer(texture);
        FloatBuffer data = ImageDecoder.decodeImage(resource, callback);
        container.bind(target);
        container.image2D(target, 0, callback.hasAlpha ? GL11.GL_RGBA8 : GL11.GL_RGB8, callback.width, callback.height, 0, callback.hasAlpha ? GL11.GL_RGBA : GL11.GL_RGB, GL11.GL_FLOAT, data);
        return texture;
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param resource {@link Resource} containing RGB(A) image data
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, Resource resource, ImageDataCallback callback) throws IOException {
        return loadTexture(target, GLTexture.createTexture(), resource, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param texture Texture to bind and upload data to
     * @param resource {@link Resource} containing RGB(A) image data
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, GLTexture texture, Resource resource) throws IOException {
        return loadTexture(target, texture, resource, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided
     * @param target Texture bind target for operations
     * @param resource {@link Resource} containing RGB(A) image data
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture(int target, Resource resource) throws IOException {
        return loadTexture(target, GLTexture.createTexture(), resource, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param texture Texture to bind and upload data to
     * @param resource {@link Resource} containing RGB(A) image data
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(GLTexture texture, Resource resource, ImageDataCallback callback) throws IOException {
        return loadTexture(GL11.GL_TEXTURE_2D, texture, resource, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param resource {@link Resource} containing RGB(A) image data
     * @param callback Callback to send image data back
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(Resource resource, ImageDataCallback callback) throws IOException {
        return loadTexture2D(GLTexture.createTexture(), resource, callback);
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param texture Texture to bind and upload data to
     * @param resource {@link Resource} containing RGB(A) image data
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(GLTexture texture, Resource resource) throws IOException {
        return loadTexture2D(texture, resource, new ImageDataCallback());
    }
    
    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param resource {@link Resource} containing RGB(A) image data
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public static synchronized GLTexture loadTexture2D(Resource resource) throws IOException {
        return loadTexture2D(GLTexture.createTexture(), resource, new ImageDataCallback());
    }
}
