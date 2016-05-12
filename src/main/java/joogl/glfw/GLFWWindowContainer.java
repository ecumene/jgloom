package joogl.glfw;

import joogl.GLNativeException;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

/**
 * A shell class containing functions for manipulating a given {@link GLFWWindow}
 */
public class GLFWWindowContainer {
    private GLFWWindow windowInstance;

    /**
     * Initializes the GLFW window container
     * @param windowInstance The GLFW Window to track and contain
     */
    public GLFWWindowContainer(GLFWWindow windowInstance){
        this.windowInstance = windowInstance;
    }

    /**<b>May only be called from the main thread.</b>
     * @param title The window's title
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void setTitle(String title){
        GLFW.glfwSetWindowTitle(windowInstance.getGLFWWindow(), title);
    }

    /**<b>May only be called from the main thread.</b>
     * @param newPointer The new value.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void setUserPointer(long newPointer) throws GLNativeException {
        GLFW.glfwSetWindowUserPointer(windowInstance.getGLFWWindow(), newPointer);
        GLNativeException.checkOGL();
    }

    /**
     * getFramebufferSize[0] = Frame buffer width
     * getFramebufferSize[1] = Frame buffer height
     * <b>May only be called from the main thread.</b>
     * @return The size, in pixels, of the framebuffer of the specified window, not the
     *         windows dimensions. If you wish to get the window dimensions check
     *         {@link GLFWWindowContainer#getSize()}
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public ByteBuffer[] getFramebufferSize() throws GLNativeException {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        ByteBuffer width  = BufferUtils.createByteBuffer(4);
        ByteBuffer height = BufferUtils.createByteBuffer(4);
        GLFW.glfwGetWindowSize(windowInstance.getGLFWWindow(), width, height);
        GLNativeException.checkOGL();
        return new ByteBuffer[]{width, height};
    }

    /**
     * getFrameSize[0] = left edge of the window frame, or NULL
     * getFrameSize[1] = top edge of the window frame, or NULL
     * getFrameSize[2] = right edge of the window frame, or NULL
     * getFrameSize[3] = bottom edge of the window frame, or NULL
     * Any or all of the size arguments may be NULL. If an error occurs, all non-NULL size arguments will be set to
     * zero.
     * <b>May only be called from the main thread.</b>
     * @return the size, in screen coordinates, of each edge of the frame of the specified window. This size includes
     *         the title bar, if the window has one. The size of the frame may vary depending on the window-related
     *         hints used to create it.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public ByteBuffer[] getFrameSize() throws GLNativeException {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        ByteBuffer left   = BufferUtils.createByteBuffer(4);
        ByteBuffer top    = BufferUtils.createByteBuffer(4);
        ByteBuffer right  = BufferUtils.createByteBuffer(4);
        ByteBuffer bottom = BufferUtils.createByteBuffer(4);
        GLFW.glfwGetWindowFrameSize(windowInstance.getGLFWWindow(), left, top, right, bottom);
        GLNativeException.checkOGL();
        return new ByteBuffer[]{left, top, right, bottom};
    }

    /**
     * getFramebufferSize[0] = Window's width
     * getFramebufferSize[1] = Window's height
     * <b>May only be called from the main thread.</b>
     * @return The size, in screen coordinates, of the client area of the specified window.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public ByteBuffer[] getSize() throws GLNativeException {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        ByteBuffer width  = BufferUtils.createByteBuffer(4);
        ByteBuffer height = BufferUtils.createByteBuffer(4);
        GLFW.glfwGetWindowSize(windowInstance.getGLFWWindow(), width, height);
        GLNativeException.checkOGL();
        return new ByteBuffer[]{width, height};
    }

    /**
     * <b>May only be called from the main thread.</b>
     * @return the position, in screen coordinates, of the upper-left corner of the client area of the specified window.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public ByteBuffer[] getPosition() throws GLNativeException {
        ByteBuffer x = BufferUtils.createByteBuffer(4);
        ByteBuffer y = BufferUtils.createByteBuffer(4);
        GLFW.glfwGetWindowPos(windowInstance.getGLFWWindow(), x, y);
        GLNativeException.checkOGL();
        return new ByteBuffer[]{x, y};
    }

    /**
     * This function swaps the front and back buffers of the specified window. If the swap interval is greater than
     * zero, the GPU driver waits the specified number of screen updates before swapping the buffers.
     * <b>May be called from any thread.</b>
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public synchronized void swapBuffers() throws GLNativeException {
        GLFW.glfwSwapBuffers(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

    /**
     * This function sets the value of the close flag of the specified window. This can be used to override the user's
     * attempt to close the window, or to signal that it should be closed.
     * <b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @param closeFlag The new value. (GL_TRUE / GL_FALSE)
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public synchronized void setShouldClose(int closeFlag) throws GLNativeException {
        GLFW.glfwSetWindowShouldClose(windowInstance.getGLFWWindow(), closeFlag);
        GLNativeException.checkOGL();
    }

    /**
     * @return If the GLFW Window is closing of not.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public boolean shouldClose() throws GLNativeException {
        int closeFlag = GLFW.glfwWindowShouldClose(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
        return closeFlag != GL11.GL_FALSE;
    }

    /**
     * This function makes the specified window visible if it was previously hidden. If the window is already visible or
     * is in full screen mode, this function does nothing.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void show() throws GLNativeException {
        GLFW.glfwShowWindow(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

    /**
     * This function restores the specified window if it was previously iconified (minimized). If the window is already
     * restored, this function does nothing
     * <b>May only be called from the main thread.</b>
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void restore() throws GLNativeException {
        GLFW.glfwRestoreWindow(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

    /**
     * This function iconifies (minimizes) the specified window if it was previously restored. If the window is already
     * iconified, this function does nothing.
     * <b>May only be called from the main thread.</b>
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void iconify() throws GLNativeException {
        GLFW.glfwIconifyWindow(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

    /**
     * This function hides the specified window if it was previously visible. If the window is already hidden or is in
     * full screen mode, this function does nothing.
     * <b>May only be called from the main thread.</b>
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void hide() throws GLNativeException {
        GLFW.glfwHideWindow(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

    /**
     * <b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @return the current value of the user-defined pointer of the specified window. The initial value is NULL.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public synchronized long getUserPointer() throws GLNativeException {
        long pointer = GLFW.glfwGetWindowUserPointer(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
        return pointer;
    }

    /**
     * <b>May only be called from the main thread.</b>
     * @return The monitor, or NULL if the window is in windowed mode or an error occurred.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public long getMonitor() throws GLNativeException {
        long monitor = GLFW.glfwGetWindowMonitor(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
        return monitor;
    }

    /**
     * http://www.glfw.org/docs/latest/window.html#window_attribs
     * @param attrib The window attribute whose value to return.
     * @return the value of an attribute of the specified window or its OpenGL or OpenGL ES context.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public int getAttrib(int attrib) throws GLNativeException {
        int returnAttrib = GLFW.glfwGetWindowAttrib(windowInstance.getGLFWWindow(), attrib);
        GLNativeException.checkOGL();
        return returnAttrib;
    }

    /**
     * Destroys the specified window and its context. On calling this function, no further callbacks will be called
     * for this window.
     * @throws GLNativeException In the case the GLFW call created an error
     */
    public void destroy() throws GLNativeException {
        GLFW.glfwDestroyWindow(windowInstance.getGLFWWindow());
        GLNativeException.checkOGL();
    }

}
