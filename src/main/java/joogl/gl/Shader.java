package joogl.gl;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import joogl.errors.GLError;

/**
 * Handles shader programs and variables
 */
public class Shader
{
	/**
	 * Pointer to GL shader program
	 */
	private int program;
	
	/**
	 * @return Pointer to GL shader program
	 */
	public int getProgram()
	{
		return program;
	}
	
	/**
	 * Creates a shader wrapper from GLSL source
	 * @param shader Shader wrapper to initialize
	 * @param vertexSrc Vertex shader GLSL source
	 * @param fragmentSrc Fragment shader GLSL source
	 * @throws GLError If shader compilation fails
	 */
	public static void genShader(Shader shader, String vertexSrc, String fragmentSrc)
	{
		int vertShader = createGLShader(GL_VERTEX_SHADER, vertexSrc);
		int fragShader = createGLShader(GL_FRAGMENT_SHADER, fragmentSrc);
		
		shader.program = glCreateProgram();
		glAttachShader(shader.program, vertShader);
		glAttachShader(shader.program, fragShader);
		glLinkProgram(shader.program);
		
		glDeleteShader(vertShader);
		glDeleteShader(fragShader);
	}
	
	/**
	 * Creates a GL shader from given type and GLSL source
	 * @param type Type of GL shader to create
	 * @param src GLSL source to compile
	 * @return Pointer to created shader of given type
	 */
	public static int createGLShader(int type, String src) 
	{
		int shader = glCreateShader(type);
		glShaderSource(shader, src);
		glCompileShader(shader);
		checkShaderCompileStatus(shader);
		return shader;
	}
	
	/**
	 * Checks for compile errors and throws a {@link GLError} if
	 * there are any
	 * @param shader Shader to check for errors
	 */
	public static void checkShaderCompileStatus(int shader)
	{
		int status = glGetShaderi(shader, GL_COMPILE_STATUS);
		
		if (status == GL_FALSE)
		{
			String log = glGetShaderInfoLog(shader);
			throw new GLError("Could not compile shader:\n" + log);
		}
	}
	
	/**
	 * Binds the given shader object
	 * @param shader Shader wrapper to bind
	 */
	public static void bindShader(Shader shader)
	{
		glUseProgram(shader.program);
	}
	
	/**
	 * Deletes the given shader object
	 * @param shader Shader wrapper to delete
	 */
	public static void destroyShader(Shader shader)
	{
		glDeleteProgram(shader.program);
	}
	
	/**
	 * Finds the requested uniform location
	 * @param shader Shader with the uniform variable
	 * @param name Name of the uniform variable
	 * @return Location of the uniform variable
	 */
	public static int getShaderUniform(Shader shader, String name)
	{
		return glGetUniformLocation(shader.program, name);
	}
	
	/**
	 * Finds the requested attribute location
	 * @param shader Shader with the attribute variable
	 * @param name Name of the attribute variable
	 * @return Location of the attribute variable
	 */
	public static int getShaderAttrib(Shader shader, String name)
	{
		return glGetAttribLocation(shader.program, name);
	}
}
