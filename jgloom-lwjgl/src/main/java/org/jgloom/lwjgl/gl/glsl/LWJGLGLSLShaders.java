package org.jgloom.lwjgl.gl.glsl;

import org.jgloom.gl.glsl.GLSLShader;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL43;

/**
 * A Shader is a user-defined program designed to run on some stage of a graphics processor. Its purpose is to execute
 * one of the programmable stages of the rendering pipeline.
 * @see <a href=https://www.opengl.org/wiki/Shader>opengl.org - GLSL Shader Objects</a>
 */
public class LWJGLGLSLShaders {
    /** The OpenGL shader object identifier */
    public static int IDENTIFIER = GL43.GL_SHADER;

    /**
     * Creates an empty {@link GLSLShader} of the given type
     * @param type
     * @return New {@link GLSLShader} object
     */
    public static GLSLShader createShader(int type) {
        int shader = GL20.glCreateShader(type);
        return () -> shader;
    }
}
