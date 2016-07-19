package org.jgloom.lwjgl.gl.glsl;

import org.jgloom.GLNativeException;
import org.jgloom.gl.glsl.GLSLProgram;
import org.jgloom.gl.glsl.GLSLShader;
import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

/**
 * A shell class containing LWJGL functions for manipulating a given
 * {@link GLSLProgram}
 */
public class GLSLProgramContainer extends AbstractGLSLProgram {
    /**
     * Initializes the LWJGL GLSL program container
     * @param programInstance The GLSL program to track and contain
     */
    public GLSLProgramContainer(GLSLProgram programInstance) {
        super(programInstance);
    }

    @Override
    public void use(){
        GL20.glUseProgram(getGLSLProgram());
    }

    @Override
    public void attachGLSLShader(GLSLShader shader) {
        GL20.glAttachShader(getGLSLProgram(), shader.getGLSLShader());
    }

    @Override
    public void detachGLSLShader(GLSLShader shader) {
        GL20.glDetachShader(getGLSLProgram(), shader.getGLSLShader());
    }

    @Override
    public void validate(){
        GL20.glValidateProgram(getGLSLProgram());
    }

    @Override
    public void link() throws GLSLLinkException {
        GL20.glLinkProgram(getGLSLProgram());
    }

    @Override
    public String getLog(){
        return GL20.glGetProgramInfoLog(getGLSLProgram(), GL20.glGetProgrami(getGLSLProgram(), GL20.GL_INFO_LOG_LENGTH));
    }

    @Override
    public void delete() {
        GL20.glDeleteProgram(getGLSLProgram());
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
