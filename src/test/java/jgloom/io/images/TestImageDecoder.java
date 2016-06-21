package jgloom.io.images;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.junit.Test;

import jgloom.io.images.decoding.ImageDecoder;
import jgloom.io.resources.ClasspathResource;
import jgloom.io.resources.Resource;

/**
 * Tests loading RGB or RGBA data from BMP, GIF, JPEG, PNG, and TIFF images
 */
public class TestImageDecoder {
    private void testLoad(String path) throws IOException {
        System.out.println("Contents of " + path);
        if (path.endsWith(".jpg")) System.out.println("(Inaccurate due to compression)");
        path = "textures/tiny/" + path;
        Resource testImage = new ClasspathResource(path);
        ImageDataCallback callback = new ImageDataCallback();
        FloatBuffer dat = ImageDecoder.decodeImage(testImage, callback);
        System.out.println("Image size: " + callback.width + " by " + callback.height);
        System.out.println("Has alpha channel: " + callback.hasAlpha);
        while (dat.hasRemaining())
            if (callback.hasAlpha)
                System.out.println(dat.get() + " " + dat.get() + " " + dat.get() + " " + dat.get());
            else
                System.out.println(dat.get() + " " + dat.get() + " " + dat.get());
        System.out.println();
    }

    @Test
    public void testLoadBMP() throws IOException {
        testLoad("2x2.bmp");
    }

    @Test
    public void testLoadGIF() throws IOException {
        testLoad("2x2.gif");
    }

    @Test
    public void testLoadJPG() throws IOException {
        testLoad("2x2.jpg");
    }

    @Test
    public void testLoadPNG() throws IOException {
        testLoad("2x2.png");
    }

    @Test
    public void testLoadTIF() throws IOException {
        testLoad("2x2.tif");
    }
}
