package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLRenderbuffer;

/**
 * Identical to {@link GLFramebufferContainer}, except binds when the state is changed
 */
public class StatefulGLRenderbufferContainer extends GLRenderbufferContainer {
    /** @param buffer The renderbuffer to track */
    public StatefulGLRenderbufferContainer(GLRenderbuffer buffer){
        super(buffer);
    }

    @Override
    public void bind() {
        super.bind();
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        bind();
    }

}
