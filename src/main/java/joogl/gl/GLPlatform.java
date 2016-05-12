package joogl.gl;

/**
 * Signals the JOOGL library which platform the library is running on,
 * and allows for high-level loaders to do platform-specific tasks.
 */
public enum GLPlatform {
    OpenGL,
    GLES,
    WebGL;
}
