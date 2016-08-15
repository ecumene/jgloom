package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLBuffer;

/**
 * Identical to {@link GLBufferContainer}, except binds when the state is changed
 */
public class StatefulGLBufferContainer extends GLBufferContainer {
    private int lastTarget;

    /** @param buffer The buffer to track */
    public StatefulGLBufferContainer(GLBuffer buffer){
        super(buffer);
    }

    @Override
    public void bind(int target) {
        this.lastTarget = target;
        super.bind(target);
    }

    /**
     * Binds using the last target used in {@link #lastTarget}
     */
    public void bindLast(){
        bind(lastTarget);
    }

    /**
     * @return The last target either set via {@link #setLastTarget(int)} or {@link #bind(int)}
     */
    public int getLastTarget() {
        return lastTarget;
    }

    /**
     * @param lastTarget The target to use next time the object is {@link #bindLast()}
     */
    public void setLastTarget(int lastTarget) {
        this.lastTarget = lastTarget;
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        bindLast();
    }
}
