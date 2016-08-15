package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLBuffer;
import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link org.jgloom.gl.GLBuffer} objects
 */

public class GLBufferContainer extends AbstractGLBuffer {

    /** @param buffer The buffer to track */
    public GLBufferContainer(GLBuffer buffer){
        super(buffer);
    }

    @Override
    public void bind(int target) {
        GL15.glBindBuffer(target, getBuffer());
    }

    @Override
    public void delete() {
        onStateChanged();
        GL15.glDeleteBuffers(getBuffer());
    }

    @Override
    public void data(int target, long size, int usage) {
        onStateChanged();
        GL15.glBufferData(target, size, usage);
    }

    @Override
    public void data(int target, ByteBuffer data, int usage) {
        onStateChanged();
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void data(int target, ShortBuffer data, int usage) {
        onStateChanged();
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void data(int target, IntBuffer data, int usage) {
        onStateChanged();
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void data(int target, FloatBuffer data, int usage) {
        onStateChanged();
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void data(int target, DoubleBuffer data, int usage) {
        onStateChanged();
        GL15.glBufferData(target, data, usage);
    }

    @Override
    public void subData(int target, long offset, ByteBuffer data) {
        onStateChanged();
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void subData(int target, long offset, ShortBuffer data) {
        onStateChanged();
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void subData(int target, long offset, IntBuffer data) {
        onStateChanged();
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void subData(int target, long offset, FloatBuffer data) {
        onStateChanged();
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void subData(int target, long offset, DoubleBuffer data) {
        onStateChanged();
        GL15.glBufferSubData(target, offset, data);
    }

    @Override
    public void getSubData(int target, long offset, ByteBuffer data) {
        onStateChanged();
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void getSubData(int target, long offset, ShortBuffer data) {
        onStateChanged();
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void getSubData(int target, long offset, IntBuffer data) {
        onStateChanged();
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void getSubData(int target, long offset, FloatBuffer data) {
        onStateChanged();
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void storage(int target, ByteBuffer data, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, data, flags);
    }

    @Override
    public void storage(int target, ShortBuffer data, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, data, flags);
    }

    @Override
    public void storage(int target, IntBuffer data, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, data, flags);
    }

    @Override
    public void storage(int target, FloatBuffer data, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, data, flags);
    }

    @Override
    public void storage(int target, DoubleBuffer data, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, data, flags);
    }

    @Override
    public void storage(int target, long size, int flags) {
        onStateChanged();
        GL44.glBufferStorage(target, size, flags);
    }

    @Override
    public void clear(int target, int internalformat, int format, int type, ByteBuffer data) {
        onStateChanged();
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void clear(int target, int internalformat, int format, int type, ShortBuffer data) {
        onStateChanged();
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void clear(int target, int internalformat, int format, int type, IntBuffer data) {
        onStateChanged();
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void clear(int target, int internalformat, int format, int type, FloatBuffer data) {
        onStateChanged();
        GL43.glClearBufferData(target, internalformat, format, type, data);
    }

    @Override
    public void subClear(int target, int internalformat, long offset, long size, int format, int type, ByteBuffer data) {
        onStateChanged();
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void subClear(int target, int internalformat, long offset, long size, int format, int type, ShortBuffer data) {
        onStateChanged();
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void subClear(int target, int internalformat, long offset, long size, int format, int type, IntBuffer data) {
        onStateChanged();
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void subClear(int target, int internalformat, long offset, long size, int format, int type, FloatBuffer data) {
        onStateChanged();
        GL43.glClearBufferSubData(target, internalformat, offset, size, format, type, data);
    }

    @Override
    public void getSubData(int target, long offset, DoubleBuffer data) {
        onStateChanged();
        GL15.glGetBufferSubData(target, offset, data);
    }

    @Override
    public void map(int target, int access) {
        onStateChanged();
        GL15.glMapBuffer(target, access);
    }

    @Override
    public boolean unmap(int target) {
        onStateChanged();
        return GL15.glUnmapBuffer(target);
    }

    @Override
    public void invalidate() {
        onStateChanged();
        GL43.glInvalidateBufferData(getBuffer());
    }

    @Override
    public void invalidateRange(int offset, int size) {
        onStateChanged();
        GL43.glInvalidateBufferSubData(getBuffer(), offset, size);
    }

    @Override
    public IntBuffer getParameters(int target, int pname) {
        IntBuffer out = null;
        GL15.glGetBufferParameteriv(target, pname, out);
        return out;
    }

    @Override
    public int getParameter(int target, int pname) {
        return GL15.glGetBufferParameteri(target, pname);
    }

    @Override
    public void onStateChanged() {
        // Do nothing, stateful containers bind.
    }
}
