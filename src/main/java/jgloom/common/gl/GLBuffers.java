package jgloom.common.gl;

import jgloom.gl.GLBuffer;
import org.lwjgl.opengl.GL15;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka:
 * the GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety
 * of other things.
 * {@link GLBuffer}
 */
public class GLBuffers {
    // Why is there no 'getCurrentBuffer' - Because there's no way to do that, sadly :(

    /**
     * Constructs a {@link GLBuffer} object with {@link GL15#glGenBuffers()}.
     */
    public static synchronized GLBuffer createBuffer(){
        return new GLBuffer(){
            @Override
            public int getBuffer() {
                return GL15.glGenBuffers();
            }
        };
    }
}
