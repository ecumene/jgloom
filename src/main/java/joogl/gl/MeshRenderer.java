package joogl.gl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Handles interleaved and eventually regular rendering
 */
public class MeshRenderer
{
	/**
	 * How many floats are in each interleaved vertex
	 */
	public static final int INTERLEAVED_ELEMENTS = 9;
	
	/**
	 * Shader attrib location of 'vec3 vertex_in'
	 */
	public static final int VERTEX_ATTRIB = 10;
	
	/**
	 * Shader attrib location of 'vec4 color_in'
	 */
	public static final int COLOR_ATTRIB = 11;
	
	/**
	 * Shader attrib location of 'vec2 tex_coord_in'
	 */
	public static final int TEX_COORD_ATTRIB = 12;
	
	/**
	 * Vertices are three numbers each
	 */
	public static final int VERTEX_DIMENSIONS = 3;
	
	/**
	 * Colors are four numbers each
	 */
	public static final int COLOR_DIMENSIONS = 4;
	
	/**
	 * Texture coordinates are two numbers each
	 */
	public static final int TEX_COORD_DIMENSIONS = 2;
	
	/**
	 * GL buffer holding interleaved vertex data
	 */
	private int buffer;
	
	/**
	 * GL vertex array for the rendering data
	 */
	private int vertexArray;
	
	/**
	 * Number of vertices stored in the buffer
	 */
	private int vertexCount;
	
	/**
	 * @return GL buffer holding interleaved vertex data
	 */
	public int getBuffer()
	{
		return buffer;
	}
	
	/**
	 * @return GL vertex array for the rendering data
	 */
	public int getVertexArray()
	{
		return vertexArray;
	}
	
	/**
	 * @return Number of vertices stored in the buffer
	 */
	public int getVertexCount()
	{
		return vertexCount;
	}
	
	/**
	 * Creates an empty buffer and vertex array
	 * @param renderer MeshRenderer to initialize
	 */
	public static void genMeshRenderer(MeshRenderer renderer)
	{
		renderer.vertexArray = glGenVertexArrays();
		renderer.buffer = glGenBuffers();
	}
	
	/**
	 * Renders the stored interleaved data
	 * @param renderer Renderer to render data from
	 */
	public static void renderInterleavedMesh(MeshRenderer renderer)
	{
		glBindVertexArray(renderer.vertexArray);
		glBindBuffer(GL_ARRAY_BUFFER, renderer.buffer);
		glEnableVertexAttribArray(VERTEX_ATTRIB);
		glEnableVertexAttribArray(COLOR_ATTRIB);
		glEnableVertexAttribArray(TEX_COORD_ATTRIB);
		
		int stride = INTERLEAVED_ELEMENTS * 4;
		int colorOffset = VERTEX_DIMENSIONS * 4;
		int texCoordOffset = colorOffset + COLOR_DIMENSIONS * 4;
		glVertexAttribPointer(VERTEX_ATTRIB, VERTEX_DIMENSIONS, GL_FLOAT, false, stride, 0);
		glVertexAttribPointer(COLOR_ATTRIB, COLOR_DIMENSIONS, GL_FLOAT, false, stride, colorOffset);
		glVertexAttribPointer(TEX_COORD_ATTRIB, TEX_COORD_DIMENSIONS, GL_FLOAT, false, stride, texCoordOffset);
		glDrawArrays(GL_TRIANGLES, 0, renderer.vertexCount);

		glDisableVertexAttribArray(VERTEX_ATTRIB);
		glDisableVertexAttribArray(COLOR_ATTRIB);
		glDisableVertexAttribArray(TEX_COORD_ATTRIB);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	/**
	 * Uploads interleaved vertex data from a buffer
	 * @param renderer Renderer to upload to
	 * @param data FloatBuffer to upload from
	 */
	public static void createInterleavedRenderer(MeshRenderer renderer, FloatBuffer data)
	{
		renderer.vertexCount = data.remaining() / INTERLEAVED_ELEMENTS;
		glBindBuffer(GL_ARRAY_BUFFER, renderer.buffer);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Uploads interleaved vertex data from an array
	 * @param renderer Renderer to upload to
	 * @param data Float array to upload from
	 */
	public static void createInterleavedRenderer(MeshRenderer renderer, float[] data)
	{
		FloatBuffer buffer = ByteBuffer.allocateDirect(data.length * 4).asFloatBuffer();
		buffer.put(data);
		buffer.flip();
		createInterleavedRenderer(renderer, buffer);
	}
	
	/**
	 * Deletes the buffer and vertex array
	 * @param renderer Renderer to destroy
	 */
	public static void destroyMeshRenderer(MeshRenderer renderer)
	{
		glDeleteBuffers(renderer.buffer);
		glDeleteVertexArrays(renderer.vertexArray);
	}
}
