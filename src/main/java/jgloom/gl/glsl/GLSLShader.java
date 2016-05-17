package jgloom.gl.glsl;

import org.lwjgl.opengl.GL43;

/**
 * A Shader is a user-defined program designed to run on some stage of a graphics processor. Its purpose is to execute
 * one of the programmable stages of the rendering pipeline.
 * @see <a href=https://www.opengl.org/wiki/Shader>opengl.org - GLSL Shader Objects</a>
 */
public interface GLSLShader {
    /** @return The shader identifier */
    public int getGLSLShader();

    /** The OpenGL shader object identifier */
    public static final int IDENTIFIER = GL43.GL_SHADER;
}
