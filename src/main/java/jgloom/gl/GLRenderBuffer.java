package jgloom.gl;

import org.lwjgl.opengl.GL30;

public interface GLRenderBuffer {
    public int getRenderBuffer();

    public static final int IDENTIFIER = GL30.GL_RENDERBUFFER;
}
