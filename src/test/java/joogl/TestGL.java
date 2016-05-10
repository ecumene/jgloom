package joogl;

import org.junit.Test;

import joogl.utils.ContextManager;

public class TestGL {
    @Test
    public void testSharedLibraries(){
    	// Loads the native libraries
        SharedLibraryLoader.load();
    }

    @Test
    public void testGLFW(){
    	// Creates a default context manager
    	ContextManager contextManager = new ContextManager();
    	contextManager.createContext();
    	
    	// Demonstrates maintaining context and
    	// updating context window data
    	for (long l = 0; !contextManager.shouldWindowClose(); l ++)
    	{
    		contextManager.setTitle("" + l);
    		contextManager.update();
    	}
    	
    	// Destroys the context manager
    	contextManager.destroyContext();
    }
}
