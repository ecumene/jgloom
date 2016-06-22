package jgloom.io.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.FloatBuffer;

import org.junit.Test;

import jgloom.io.images.decoding.ImageDecoder;

/**
 * Class for testing implementations of {@link Resource}
 */
public class TestResources {
    private void readContents(Resource res) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null)
            System.out.println(line);
        reader.close();
        System.out.println();
    }
    
    @Test
    public void testFileResource() throws IOException {
        File textFile = new File("src/test/resources/text/test_text.txt");
        Resource testText = Resource.createFileResource(textFile);
        readContents(testText);
    }
    
    @Test
    public void testClasspathResource() throws IOException {
        Resource testText = Resource.createClasspathResource("text/test_text.txt");
        readContents(testText);
    }
    
    /**
     * Test image stored online using Imgur at http://i.imgur.com/ruLnHms.png
     * @throws IOException
     */
    @Test
    public void testWebResource() throws IOException {
        URL testImage = new URL("http://i.imgur.com/ruLnHms.png");
        Resource testResource = Resource.createWebResource(testImage);
        FloatBuffer loaded = ImageDecoder.decodeImage(testResource);
        System.out.println("Loaded from web resource: ");
        while (loaded.hasRemaining())
            System.out.println(loaded.get() + " " + loaded.get() + " " + loaded.get() + " " + loaded.get());
        testResource.close();
        System.out.println();
    }
}
