package joogl;

import org.junit.Test;

public class TestGL {
    @Test
    public void testSharedLibraries(){
        SharedLibraryLoader.load();
    }

    @Test
    public void testGLFW(){
        TestGLUtil.openContext();
        TestGLUtil.closeContext();
    }
}
