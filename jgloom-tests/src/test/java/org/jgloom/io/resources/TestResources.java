package org.jgloom.io.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.FloatBuffer;

import org.jgloom.io.images.ImageDecoder;
import org.jgloom.io.resources.ClasspathResource;
import org.jgloom.io.resources.FileResource;
import org.jgloom.io.resources.Resource;
import org.jgloom.io.resources.WebResource;
import org.junit.Test;

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
        File testFile = File.createTempFile("test", "txt");
        testFile.deleteOnExit();
        OutputStream out = new FileOutputStream(testFile);
        out.write("Hello, world!".getBytes());
        out.close();
        Resource testText = FileResource.createFileResource(testFile);
        readContents(testText);
    }
    
    @Test
    public void testClasspathResource() throws IOException {
        Resource testText = ClasspathResource.createClasspathResource("text/test_text.txt");
        readContents(testText);
    }
    
    /**
     * Test image stored online using Imgur at http://i.imgur.com/ruLnHms.png
     * @throws IOException
     */
    @Test
    public void testWebResource() throws IOException {
        URL testImage = new URL("http://i.imgur.com/ruLnHms.png");
        Resource testResource = WebResource.createWebResource(testImage);
        FloatBuffer loaded = ImageDecoder.decodeImage(testResource);
        System.out.println("Loaded from web resource: ");
        while (loaded.hasRemaining())
            System.out.println(loaded.get() + " " + loaded.get() + " " + loaded.get() + " " + loaded.get());
        testResource.close();
        System.out.println();
    }
}
