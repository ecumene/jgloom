package org.jgloom.gl.glsl;

/**
 * A Shader is a user-defined program designed to run on some stage of a graphics processor. Its purpose is to execute
 * one of the programmable stages of the rendering pipeline.
 * @see <a href=https://www.opengl.org/wiki/Shader>opengl.org - GLSL Shader Objects</a>
 */
@FunctionalInterface
public interface GLSLShader {
    /** @return The shader identifier */
    int getGLSLShader();
}
