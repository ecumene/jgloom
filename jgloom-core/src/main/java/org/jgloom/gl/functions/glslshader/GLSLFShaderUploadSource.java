package org.jgloom.gl.functions.glslshader;

/**
 * Functions for GLSL shaders
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFShaderUploadSource extends GLSLFShader {
    /**
     * Sets the source code in shader​ to the source code in the array of strings specified by string​. Any source code
     * previously stored in the shader object is completely replaced. The number of strings in the array is specified
     * by count​. If length​ is NULL, each string is assumed to be null terminated. If length​ is a value other than NULL,
     * it points to an array containing a string length for each of the corresponding elements of string​. Each element in
     * the length​ array may contain the length of the corresponding string (the null character is not counted as part of
     * the string length) or a value less than 0 to indicate that the string is null terminated. The source code strings
     * are not scanned or parsed at this time; they are simply copied into the specified shader object.
     * @param source The source to replace the old shader with
     */
    void uploadSource(String source);
}
