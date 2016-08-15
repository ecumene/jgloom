package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLTexture;

/**
 * Identical to {@link GLTextureContainer}, except binds when the state is changed
 */
public class StatefulGLTextureContainer extends GLTextureContainer {
    private int lastTarget;

    /** @param object The texture to track */
    public StatefulGLTextureContainer(GLTexture object){
        super(object);
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
