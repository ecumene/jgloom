package joogl;

import org.junit.Test;

import joogl.utils.ContextManager;
import joogl.utils.Framebuffer;
import joogl.utils.Texture;

public class TestGL
{
	// Context manager for window
	public ContextManager contextManager;
	// Framebuffer for rendering
	public Framebuffer framebuffer;
	// Framebuffer's render texture
	public Texture renderTexture;

    @Test
    public void testGLUtils()
    {
    	// Creates a default context manager
    	contextManager = new ContextManager();
    	contextManager.createContext();
    	
    	// Creates a new framebuffer and gets texture
    	framebuffer = new Framebuffer(800, 450);
    	renderTexture = framebuffer.getRenderTexture();
    	
    	// Demonstrates maintaining context and
    	// updating context window data
    	for (long l = 0; !contextManager.shouldWindowClose(); l ++)
    	{
    		contextManager.setTitle("" + l);
    		contextManager.update();
    	}

    	// Destroys the framebuffer (and texture)
    	framebuffer.destroy();
    	// Destroys the context manager
    	contextManager.destroyContext();
    }
}
