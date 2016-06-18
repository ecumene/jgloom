package jgloom.io.images;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import jgloom.common.gl.GLTextureContainer;
import jgloom.gl.GLTexture;
import jgloom.io.images.decoding.ImageDecoder;

public class TextureLoader {
    public static synchronized GLTexture loadTexture(InputStream input, ImageDataCallback callback) throws IOException {
        GLTexture texture = GLTexture.createTexture();
        GLTextureContainer container = new GLTextureContainer(texture);
        Buffer data = ImageDecoder.decodeImage(input, callback);
        // TODO: Upload data to texture
        return texture;
    }
    
    public static synchronized GLTexture loadTexture(InputStream input) throws IOException {
        return loadTexture(input, new ImageDataCallback());
    }
}
