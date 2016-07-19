package org.jgloom.io.images;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.jgloom.gl.GLTexture;
import org.jgloom.gl.functions.texture.GLFTextureImage2D;
import org.jgloom.io.resources.Resource;
import org.jgloom.utils.enums.GLEnumLookup;

/**
 * Creates a {@link GLTexture} from loaded image data
 */
public class TextureLoader {

    private static TextureLoader INSTANCE = new TextureLoader();

    @GLEnumLookup(glVersion = "11", glEnum = "RGBA8")
    public int RGBA8 = -1;
    @GLEnumLookup(glVersion = "11", glEnum = "RGB8")
    public int RGB8 = -1;
    @GLEnumLookup(glVersion = "11", glEnum = "RGBA")
    public int RGBA = -1;
    @GLEnumLookup(glVersion = "11", glEnum = "RGB")
    public int RGB = -1;
    @GLEnumLookup(glVersion = "11", glEnum = "FLOAT")
    public int FLOAT = -1;
    @GLEnumLookup(glVersion = "11", glEnum = "TEXTURE_2D")
    public int TEXTURE_2D = -1;

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
    public synchronized GLTexture loadTexture(int target, GLFTextureImage2D texture, Resource resource, ImageDataCallback callback) throws IOException {
        FloatBuffer data = ImageDecoder.decodeImage(resource, callback);
        texture.bind(target);
        texture.image2D(target, 0, callback.hasAlpha ? RGBA8 : RGB8, callback.width, callback.height, 0, callback.hasAlpha ? RGBA : RGB, FLOAT, data);
        return texture;
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
    public synchronized GLTexture loadTexture(int target, GLFTextureImage2D texture, Resource resource) throws IOException {
        return loadTexture(target, texture, resource, new ImageDataCallback());
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
    public synchronized GLTexture loadTexture2D(GLFTextureImage2D texture, Resource resource, ImageDataCallback callback) throws IOException {
        return loadTexture(TEXTURE_2D, texture, resource, callback);
    }

    /**
     * Creates a texture image from data read from the given input stream and sends information about the image through
     * the {@link ImageDataCallback} if any is provided; defaults bind target to GL_TEXTURE_2D
     * @param texture Texture to bind and upload data to
     * @param resource {@link Resource} containing RGB(A) image data
     * @return Created texture with uploaded image data
     * @throws IOException In case of an image loading error
     */
    public synchronized GLTexture loadTexture2D(GLFTextureImage2D texture, Resource resource) throws IOException {
        return loadTexture2D(texture, resource, new ImageDataCallback());
    }

    public static TextureLoader getInstance() {
        return INSTANCE;
    }
}
