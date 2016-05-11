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
	/**
	 * Pointer to the GL framebuffer object
	 */
	private int framebuffer;
	
	/**
	 * Pointer to the depth renderbuffer
	 */
	private int depthRenderbuffer;
	
	/**
	 * Texture containing framebuffer render data
	 */
	private Texture renderTexture;
	
	/**
	 * Width of the framebuffer/render texture
	 */
	private int width;
	
	/**
	 * Height of the framebuffer/render texture
	 */
	private int height;
	
	/**
	 * @return Pointer to the GL framebuffer object
	 */
	public int getFramebuffer()
	{
		return framebuffer;
	}
	
	/**
	 * @return Pointer to the depth renderbuffer
	 */
	public int getDepthRenderbuffer()
	{
		return depthRenderbuffer;
	}
	
	/**
	 * @return Width of the framebuffer/render texture
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return Height of the framebuffer/render texture
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * @return Texture containing framebuffer render data
	 */
	public Texture getRenderTexture()
	{
		return renderTexture;
	}
	
	/**
	 * Creates a framebuffer and requisite objects
	 * @param framebuffer Framebuffer wrapper to initialize
	 * @param width Width of the framebuffer/render texture
	 * @param height Height of the framebuffer/render texture
	 */
	public static void genFramebuffer(Framebuffer framebuffer, int width, int height)
	{
		framebuffer.width = width;
		framebuffer.height = height;
		framebuffer.framebuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer.framebuffer);
		
		int texture = glGenTextures();
		framebuffer.renderTexture = new Texture(texture);
		glBindTexture(GL_TEXTURE_2D, texture);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		framebuffer.depthRenderbuffer = glGenRenderbuffers();
		glBindRenderbuffer(GL_RENDERBUFFER, framebuffer.depthRenderbuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, width, height);
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, framebuffer.depthRenderbuffer);
		
		glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, texture, 0);
		IntBuffer drawBuffers = ByteBuffer.allocateDirect(4).asIntBuffer();
		drawBuffers.put(0, GL_COLOR_ATTACHMENT0);
		glDrawBuffers(drawBuffers);
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Binds the given framebuffer for rendering
	 * @param framebuffer Framebuffer to bind
	 */
	public static void bindFramebuffer(Framebuffer framebuffer)
	{
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer.framebuffer);
	}
	
	/**
	 * Releases all framebuffers (binds 0)
	 */
	public static void releaseFramebuffers()
	{
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}
	
	/**
	 * Deletes the framebuffer and requisite objects
	 * @param framebuffer Framebuffer to destroy
	 */
	public static void destroyFramebuffer(Framebuffer framebuffer)
	{
		glDeleteFramebuffers(framebuffer.framebuffer);
		glDeleteRenderbuffers(framebuffer.depthRenderbuffer);
		Texture.destroyTexture(framebuffer.renderTexture);
	}
	
	/**
	 * Sets the viewport size to the given framebuffer
	 * @param framebuffer Framebuffer to set viewport of
	 */
	public static void setViewport(Framebuffer framebuffer)
	{
		glViewport(0, 0, framebuffer.width, framebuffer.height);
	}
}
