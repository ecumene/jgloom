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
     * Installs the program object specified by program​ as part of current rendering state. One or more executables are
     * created in a program object by successfully attaching shader objects to it with
     * {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}, successfully compiling the shader objects with
     * {@link GLSLShaderContainer#compileShader()}, and successfully linking the program object with
     * {@link GLSLProgramContainer#link()}
     */
    public void use(){
        GL20.glUseProgram(getGLSLProgram());
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
        GL20.glAttachShader(getGLSLProgram(), shader.getGLSLShader());
    }

    /**
     * Detaches the shader object specified by shader from the program object specified by program. This command can be
     * used to undo the effect of the command {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}.
     * @param shader The shader to detach
     */
    public void detachGLSLShader(GLSLShader shader) {
        GL20.glDetachShader(getGLSLProgram(), shader.getGLSLShader());
    }

    /**
     * Validates the program
     */
    public void validate(){
        GL20.glValidateProgram(getGLSLProgram());
    }

    /**
     * Links the program object specified by program. A shader object of type GL_VERTEX_SHADER attached to program is
     * used to create an executable that will run on the programmable vertex processor. A shader object of type
     * GL_FRAGMENT_SHADER attached to program is used to create an executable that will run on the programmable fragment
     * processor.
     */
    public void link(){
        GL20.glLinkProgram(getGLSLProgram());
        String log = "";
        int comp = GL20.glGetProgrami(getGLSLProgram(), GL20.GL_LINK_STATUS);
        int len = GL20.glGetProgrami(getGLSLProgram(), GL20.GL_INFO_LOG_LENGTH);
        String err = GL20.glGetProgramInfoLog(getGLSLProgram(), len);
        if (err.length() != 0)
            log = err + "\n" + log;
        if (!log.equals(""))
            log = log.trim();
        if (comp == GL11.GL_FALSE)
            throw new GLNativeException(log.length()!=0 ? log : "Could not link program (Log is empty)");
    }

    /**
     * Frees the memory and invalidates the name associated with the program object specified by program. This command
     * effectively undoes the effects of a call to {@link GLSLProgram#createProgram()}.
     */
    public void destroy() {
        GL20.glDeleteProgram(getGLSLProgram());
    }

    @Override
    public int getGLSLProgram() {
        return programInstance.getGLSLProgram();
    }

    public GLSLProgram getProgramInstance() {
        return programInstance;
    }

    /**
     * Find the given uniform location
     * @param name The name of the uniform
     * @return The uniform's location 
     */
    public int getUniformLocation(String name){
        return GL20.glGetUniformLocation(getGLSLProgram(), name);
    }

    /**
     * Uploads the array of integers to the given uniform location (4 values maximum, 1 minimum)
     * @param location  The uniform location of the matrix
     * @param values    The array value to set
     */
    public void setUniformf(int location, float... values) {
        if (values.length > 4)
            throw new GLNativeException("Uniform component cannot have more than 4 components");
        if (values.length == 0 )
            throw new GLNativeException("Uniform component must have at least 1 component");

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

    /**
     * Uploads the array of integers to the given uniform location (4 values maximum, 1 minimum)
     * @param location  The uniform location of the matrix
     * @param values    The array value to set
     */
    public void setUniformi(int location, int ... values) {
        if (values.length > 4 )
            throw new GLNativeException("Uniform component cannot have more than 4 components");
        if (values.length == 0 )
            throw new GLNativeException("Uniform component must have at least 1 component");

        switch (values.length)
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

    /**
     * Converts the given matrix to a float buffer and uploads it to GL20
     * @param location  The uniform location of the matrix
     * @param transpose Transpose before uploading (does nothing to original matrix)
     * @param value     The value to upload
     */
    public void setUniformf(int location, boolean transpose, Matrix3f value) {
        GL20.glUniformMatrix3fv(location, transpose, value.get(BufferUtils.createFloatBuffer(3*3)));
    }

    /**
     * Converts the given matrix to a float buffer and uploads it to GL20
     * @param location  The uniform location of the matrix
     * @param transpose Transpose before uploading (does nothing to original matrix)
     * @param value     The value to upload
     */
    public void setUniformf(int location, boolean transpose, Matrix4f value) {
        GL20.glUniformMatrix4fv(location, transpose, value.get(BufferUtils.createFloatBuffer(4*4)));
    }

    // Convenience methods

    /**
     * Sets a uniform using the given parameters
     * @param name   The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param values The values to set
     */
    public void setUniformi(String name, int... values) {
        setUniformi(getUniformLocation(name), values);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y)
     */
    public void setUniformi(int location, Vector2i value) {
        setUniformi(location, value.x, value.y);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y,z)
     */
    public void setUniformi(int location, Vector3i value) {
        setUniformi(location, value.x, value.y, value.z);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y,z,w)
     */
    public void setUniformi(int location, Vector4i value) {
        setUniformi(location, value.x, value.y, value.z, value.w);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y)
     */
    public void setUniformi(String name, Vector2i value) {
        setUniformi(name, value.x, value.y);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y,z)
     */
    public void setUniformi(String name, Vector3i value) {
        setUniformi(name, value.x, value.y, value.z);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y,z,w)
     */
    public void setUniformi(String name, Vector4i value) {
        setUniformi(name, value.x, value.y, value.z, value.w);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name   The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param values The values to set
     */
    public void setUniformf(String name, float... values) {
        setUniformf(getUniformLocation(name), values);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y)
     */
    public void setUniformf(int location, Vector2f value) {
        setUniformf(location, value.x, value.y);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y,z)
     */
    public void setUniformf(int location, Vector3f value) {
        setUniformf(location, value.x, value.y, value.z);
    }

    /**
     * Sets a uniform using the given parameters
     * @param location  The vector to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The values to set (x,y,z,w)
     */
    public void setUniformf(int location, Vector4f value) {
        setUniformf(location, value.x, value.y, value.z, value.w);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y)
     */
    public void setUniformf(String name, Vector2f value) {
        setUniformf(name, value.x, value.y);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y,z)
     */
    public void setUniformf(String name, Vector3f value) {
        setUniformf(name, value.x, value.y, value.z);
    }

    /**
     * Sets a uniform using the given parameters
     * @param name  The name to look up with {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value The vector to set (x,y,z,w)
     */
    public void setUniformf(String name, Vector4f value) {
        setUniformf(name, value.x, value.y, value.z, value.w);
    }

    /**
     * Sets the given uniform matrix
     * @param location  The matrix to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The matrix value to upload
     */
    public void setUniformf(int location, Matrix3f value) {
        setUniformf(location, false, value);
    }

    /**
     * Sets the given uniform matrix
     * @param location  The matrix to set (given a uniform location {@link GLSLProgramContainer#getUniformLocation(String)}
     * @param value     The matrix value to upload
     */
    public void setUniformf(int location, Matrix4f value) {
        setUniformf(location, false, value);
    }

    /**
     * Sets the given uniform matrix
     * @param name      The matrix to set
     * @param transpose Transpose the matrix before uploading (doesn't change given matrix)
     * @param value     The matrix value to upload
     */
    public void setUniformf(String name, boolean transpose, Matrix3f value) {
        setUniformf(getUniformLocation(name), transpose, value);
    }

    /**
     * Sets the given uniform matrix
     * @param name      The matrix to set
     * @param value     The matrix value to upload
     */
    public void setUniformf(String name, Matrix3f value) {
        setUniformf(name, false, value);
    }

    /**
     * Sets the given uniform matrix
     * @param name      The matrix to set
     * @param transpose Transpose the matrix before uploading (doesn't change given matrix)
     * @param value     The matrix value to upload
     */
    public void setUniformf(String name, boolean transpose, Matrix4f value) {
        setUniformf(getUniformLocation(name), transpose, value);
    }

    /**
     * Sets the given uniform matrix
     * @param name      The matrix to set
     * @param value     The matrix value to upload
     */
    public void setUniformf(String name, Matrix4f value) {
        setUniformf(name, false, value);
    }

}
