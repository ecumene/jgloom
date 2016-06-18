package jgloom.io.images;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import org.junit.Test;

import jgloom.io.images.decoding.ImageDecoder;

public class TestImageDecoder {
    private void testLoad(String path) throws IOException {
        System.out.println("Contents of " + path);
        if (path.endsWith(".jpg")) System.out.println("(Inaccurate due to compression)");
        path = "textures/test/tiny/" + path;
        InputStream in = TestImageDecoder.class.getClassLoader().getResourceAsStream(path);
        ImageDataCallback callback = new ImageDataCallback();
        FloatBuffer dat = ImageDecoder.decodeImage(in, callback);
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
