package joogl.gl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Handles a framebuffer for rendering to texture
 */
public class Framebuffer
{
	private int framebuffer;
	private int depthRenderbuffer;
	private Texture renderTexture;
	private int width, height;
	
	/**
	 * Creates a framebuffer and requisite objects
	 * @param width Width of the framebuffer / render texture
	 * @param height Height of the framebuffer / render texture
	 */
	public Framebuffer(int width, int height)
	{
		this.width = width;
		this.height = height;
		framebuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
		
		int texture = glGenTextures();
		renderTexture = new Texture(texture);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		depthRenderbuffer = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, depthRenderbuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depthRenderbuffer);
		
		glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, texture, 0);
		IntBuffer drawBuffers = ByteBuffer.allocateDirect(4).asIntBuffer();
		drawBuffers.put(0, GL_COLOR_ATTACHMENT0);
		glDrawBuffers(drawBuffers);
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Binds the framebuffer for rendering
	 */
	public void bind()
	{
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
	}
	
	/**
	 * Releases the framebuffer (binds 0).
	 */
	public void release()
	{
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
	
	/**
	 * Deletes the framebuffer and requisite objects
	 */
	public void destroy()
	{
		glDeleteFramebuffers(framebuffer);
		glDeleteRenderbuffers(depthRenderbuffer);
		renderTexture.destroy();
	}
	
	/**
	 * Sets the viewport size to the framebuffer's
	 */
	public void setViewport()
	{
		glViewport(0, 0, width, height);
	}
	
	/**
	 * Framebuffer being managed by the container
	 * @return Framebuffer pointer to framebuffer
	 */
	public int getFramebuffer()
	{
		return framebuffer;
	}
	
	/**
	 * Depth renderbuffer used for depth calculations
	 * @return Renderbuffer pointer to depth buffer
	 */
	public int getDepthRenderbuffer()
	{
		return depthRenderbuffer;
	}
	
	/**
	 * Width of the framebuffer
	 * @return Private width value
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Height of the framebuffer
	 * @return Private height value
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Texture containing the rendered image
	 * @return Texture being rendered into
	 */
	public Texture getRenderTexture()
	{
		return renderTexture;
	}
}
