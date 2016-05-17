package jgloom.common.gl.glsl;

import jgloom.GLNativeException;
import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLProgram;
import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLProgram}
 */
public class GLSLProgramContainer implements GLSLProgram {
	private GLSLProgram programInstance;

	/**
	 * Initializes the GLSL program container
	 * @param programInstance The GLSL program to track and contain
	 */
	public GLSLProgramContainer(GLSLProgram programInstance) {
		this.programInstance = programInstance;
	}

	/**
	 * In order to create a complete shader program, there must be a way to specify the list of things that will be
	 * linked together. Program objects provide this mechanism. Shaders that are to be linked together in a program
	 * object must first be attached to that program object. This attaches the shader object specified by shader to
	 * the program object specified by program. This indicates that shader will be included in link operations that
	 * will be performed on program.
	 * @param shader The shader to attach
	 */
	public void attachGLSLShader(GLSLShader shader) {
		GL20.glAttachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
	}

	/**
	 * Detaches the shader object specified by shader from the program object specified by program. This command can be
	 * used to undo the effect of the command {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}.
	 * @param shader The shader to detach
	 */
	public void detachGLSLShader(GLSLShader shader) {
		GL20.glDetachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
	}

	/**
	 * Links the program object specified by program. A shader object of type GL_VERTEX_SHADER attached to program is
	 * used to create an executable that will run on the programmable vertex processor. A shader object of type
	 * GL_FRAGMENT_SHADER attached to program is used to create an executable that will run on the programmable fragment
	 * processor.
	 */
	public void link(){
		GL20.glLinkProgram(getGLSLProgram());
		int linkStatus = GL20.glGetProgrami(getGLSLProgram(), GL20.GL_LINK_STATUS);
		if (linkStatus == GL11.GL_FALSE)
			// I've uploaded a vertex shader as a fragment shader type too many times...
			throw new GLNativeException("Could not link program, invalid shader types?");
	}

	/**
	 * Frees the memory and invalidates the name associated with the program object specified by program. This command
	 * effectively undoes the effects of a call to {@link GLSLPrograms#createProgram()}.
	 */
	public void destroy() {
		GL20.glDeleteProgram(programInstance.getGLSLProgram());
	}

	@Override
	public int getGLSLProgram() {
		return programInstance.getGLSLProgram();
	}

	public GLSLProgram getProgramInstance() {
		return programInstance;
	}

    // I'm putting these at the bottom for convenience

    // My hands hurt just reading this ...
    //TODO: document this... whenever
    //TODO: Add matrixi and matrixd methods and integer uploads + double uploads
    //TODO: Add string versions of uniforms for them

    public int getUniformLocation(String name){
        return GL20.glGetUniformLocation(getGLSLProgram(), name);
    }

    public void setUniform(int location, int ... values){
        if (values.length > 4)
            throw new GLNativeException("Uniform component cannot have more than 4 components");

        switch(values.length)
        {
            case 1:
                GL20.glUniform1i(location, values[0]);
                break;

            case 2:
                GL20.glUniform2i(location, values[0], values[1]);
                break;

            case 3:
                GL20.glUniform3i(location, values[0], values[1], values[2]);
                break;

            case 4:
                GL20.glUniform4i(location, values[0], values[1], values[2], values[3]);
                break;
        }
    }

    public void setUniform(int location, float... values) {
        if (values.length > 4)
            throw new GLNativeException("Uniform component cannot have more than 4 components");

        switch (values.length)
        {
            case 1:
                GL20.glUniform1f(location, values[0]);
                break;

            case 2:
                GL20.glUniform2f(location, values[0], values[1]);
                break;

            case 3:
                GL20.glUniform3f(location, values[0], values[1], values[2]);
                break;

            case 4:
                GL20.glUniform4f(location, values[0], values[1], values[2], values[3]);
                break;
        }
    }

    public void setUniform(int location, boolean transpose, Matrix4f value) {
        GL20.glUniformMatrix4fv(location, transpose, value.get(BufferUtils.createFloatBuffer(3*3)));
    }

    // Convenience methods

    public void setUniform(int location, boolean transpose, Matrix3f value) {
        GL20.glUniformMatrix3fv(location, transpose, value.get(BufferUtils.createFloatBuffer(3*3)));
    }

    public void setUniform(String name, int... values) {
        setUniform(getUniformLocation(name), values);
    }

    public void setUniform(String name, float... values) {
        setUniform(getUniformLocation(name), values);
    }

    public void setUniform(int location, Vector2f value) {
        setUniform(location, value.x, value.y);
    }

    public void setUniform(int location, Vector3f value) {
        setUniform(location, value.x, value.y, value.z);
    }

    public void setUniform(int location, Vector4f value) {
        setUniform(location, value.x, value.y, value.z, value.w);
    }

    public void setUniform(String name, Vector2f value) {
        setUniform(name, value.x, value.y);
    }

    public void setUniform(String name, Vector3f value) {
        setUniform(name, value.x, value.y, value.z);
    }

    public void setUniform(String name, Vector4f value) {
        setUniform(name, value.x, value.y, value.z, value.w);
    }

    public void setUniform(int location, Matrix3f value) {
        setUniform(location, false, value);
    }

    public void setUniform(int location, Matrix4f value) {
        setUniform(location, false, value);
    }

    public void setUniform(String name, boolean transpose, Matrix3f value) {
        setUniform(getUniformLocation(name), transpose, value);
    }

    public void setUniform(String name, Matrix3f value) {
        setUniform(name, false, value);
    }

    public void setUniform(String name, boolean transpose, Matrix4f value) {
        setUniform(getUniformLocation(name), transpose, value);
    }

    public void setUniform(String name, Matrix4f value) {
        setUniform(name, false, value);
    }

}
