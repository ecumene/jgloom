package jgloom.io.resources;

import java.io.IOException;

import org.junit.Test;

/**
 * Class for testing the {@link TestResourceReader} and its ability to load resources
 */
public class TestResourceReader {
    @Test
    public void testIndividualRead() throws IOException {
        ResourceLineHandler handler = (String line) -> {
            System.out.println("Read line: " + line);
        };

        Resource testText = Resource.createClasspathResource("text/test_text.txt");
        ResourceReader.readIndividualLines(testText, handler);
        System.out.println();
    }
    
    @Test
    public void testWholeRead() throws IOException {
        Resource testText = Resource.createClasspathResource("text/test_text.txt");
        System.out.println(ResourceReader.readWhole(testText));
        testText.getInputStream().close();
        System.out.println();
    }
}
