package org.jgloom.io.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;

import org.jgloom.io.resources.Resource;
import org.jgloom.utils.BufferUtils;

/**
 * Decodes BMP, GIF, JPEG, PNG, and TIFF graphics into useful data
 */
public class ImageDecoder {
    /**
     * Decodes BMP, GIF, JPEG, PNG, and TIFF images from the given {@link InputStream} and loads the image data into a
     * {@link FloatBuffer}
     * @param resource {@link Resource} containing image data to decode
     * @param callback Object to pass image width, height, and hasAlpha through
     * @return {@link FloatBuffer} containing RGB or RGBA data (depending on alpha channel) of image
     * @throws IOException In case image loading fails
     */
    public static FloatBuffer decodeImage(Resource resource, ImageDataCallback callback) throws IOException {
        BufferedImage image = ImageIO.read(resource.getInputStream());
        callback.width = image.getWidth();
        callback.height = image.getHeight();
        int[] pixels = new int[callback.width * callback.height];
        image.getRGB(0, 0, callback.width, callback.height, pixels, 0, callback.width);
        callback.hasAlpha = image.getColorModel().hasAlpha();
        int bytesPerPixel = callback.hasAlpha ? 4 : 3;
        FloatBuffer buffer = BufferUtils.createFloatBuffer(pixels.length * bytesPerPixel);

        for (int y = 0; y < callback.height; y ++)
            for (int x = 0; x < callback.width; x ++) {
                int pixel = pixels[y * callback.width + x];
                buffer.put((float) ((pixel >> 16) & 0xFF) / 255);
                buffer.put((float) ((pixel >> 8) & 0xFF) / 255);
                buffer.put((float) (pixel & 0xFF) / 255);
                if (callback.hasAlpha)
                    buffer.put((float) ((pixel >> 24) & 0xFF) / 255);
            }

        buffer.flip();
        return buffer;
    }

    /**
     * Decodes BMP, GIF, JPEG, PNG, and TIFF images from the given {@link InputStream} and loads the image data into a
     * {@link FloatBuffer}
     * @param resource {@link Resource} containing image data to decode
     * @return {@link FloatBuffer} containing RGB or RGBA data (depending on alpha channel) of image
     * @throws IOException In case image loading fails
     */
    public static FloatBuffer decodeImage(Resource resource) throws IOException {
        return decodeImage(resource, new ImageDataCallback());
    }
}
