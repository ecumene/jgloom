package jgloom.io.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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
        File textFile = new File("src/test/resources/text/test_text.txt");
        Resource testText = new FileResource(textFile);
        readContents(testText);
    }
    
    @Test
    public void testClasspathResource() throws IOException {
        Resource testText = new ClasspathResource("text/test_text.txt");
        readContents(testText);
    }
}
