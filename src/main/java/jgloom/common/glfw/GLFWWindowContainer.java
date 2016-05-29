package jgloom.common.glfw;

import jgloom.glfw.GLFWWindow;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import java.nio.IntBuffer;

/**
 * A shell class containing functions for manipulating a given {@link GLFWWindow}
 */
public class GLFWWindowContainer implements GLFWWindow {
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
     */
    public void setTitle(String title){
        GLFW.glfwSetWindowTitle(windowInstance.getGLFWWindow(), title);
    }

    /**<b>May only be called from the main thread.</b>
     * @param newPointer The new value.
     */
    public void setUserPointer(long newPointer) {
        GLFW.glfwSetWindowUserPointer(windowInstance.getGLFWWindow(), newPointer);
    }

    /**
     * getFramebufferSize[0] = Frame buffer width
     * getFramebufferSize[1] = Frame buffer height
     * <b>May only be called from the main thread.</b>
     * @return The size, in pixels, of the framebuffer of the specified window, not the
     *         windows dimensions. If you wish to get the window dimensions check
     *         {@link GLFWWindowContainer#getSize()}
     */
    public IntBuffer[] getFramebufferSize() {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        IntBuffer width  = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(windowInstance.getGLFWWindow(), width, height);
        return new IntBuffer[]{width, height};
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
     */
    public IntBuffer[] getFrameSize() {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        IntBuffer left   = BufferUtils.createIntBuffer(1);
        IntBuffer top    = BufferUtils.createIntBuffer(1);
        IntBuffer right  = BufferUtils.createIntBuffer(1);
        IntBuffer bottom = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowFrameSize(windowInstance.getGLFWWindow(), left, top, right, bottom);
        return new IntBuffer[]{left, top, right, bottom};
    }

    /**
     * getFramebufferSize[0] = Window's width
     * getFramebufferSize[1] = Window's height
     * <b>May only be called from the main thread.</b>
     * @return The size, in screen coordinates, of the client area of the specified window.
     */
    public IntBuffer[] getSize() {
        // sizes of the window are not constant and the best way to retrieve them without
        // disturbing a callback is to query the API
        IntBuffer width  = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(windowInstance.getGLFWWindow(), width, height);
        return new IntBuffer[]{width, height};
    }

    /**
     * <b>May only be called from the main thread.</b>
     * @return the position, in screen coordinates, of the upper-left corner of the client area of the specified window.
     */
    public IntBuffer[] getPosition() {
        IntBuffer x = BufferUtils.createIntBuffer(1);
        IntBuffer y = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowPos(windowInstance.getGLFWWindow(), x, y);
        return new IntBuffer[]{x, y};
    }

    /**
     * This function swaps the front and back buffers of the specified window. If the swap interval is greater than
     * zero, the GPU driver waits the specified number of screen updates before swapping the buffers.
     * <b>May be called from any thread.</b>
     */
    public synchronized void swapBuffers() {
        GLFW.glfwSwapBuffers(windowInstance.getGLFWWindow());
    }

    /**
     * This function sets the value of the close flag of the specified window. This can be used to override the user's
     * attempt to close the window, or to signal that it should be closed.
     * <b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @param closeFlag The new value.
     */
    public synchronized void setShouldClose(boolean closeFlag)  {
        GLFW.glfwSetWindowShouldClose(windowInstance.getGLFWWindow(), closeFlag);
    }

    /**
     * @return If the GLFW Window is closing of not.
     */
    public boolean shouldClose() {
        boolean closeFlag = GLFW.glfwWindowShouldClose(windowInstance.getGLFWWindow());
        return closeFlag;
    }

    /**
     * This function makes the specified window visible if it was previously hidden. If the window is already visible or
     * is in full screen mode, this function does nothing.
     */
    public void show() {
        GLFW.glfwShowWindow(windowInstance.getGLFWWindow());
    }

    /**
     * This function restores the specified window if it was previously iconified (minimized). If the window is already
     * restored, this function does nothing
     * <b>May only be called from the main thread.</b>
     */
    public void restore() {
        GLFW.glfwRestoreWindow(windowInstance.getGLFWWindow());
    }

    /**
     * This function iconifies (minimizes) the specified window if it was previously restored. If the window is already
     * iconified, this function does nothing.
     * <b>May only be called from the main thread.</b>
     */
    public void iconify() {
        GLFW.glfwIconifyWindow(windowInstance.getGLFWWindow());
    }

    /**
     * This function hides the specified window if it was previously visible. If the window is already hidden or is in
     * full screen mode, this function does nothing.
     * <b>May only be called from the main thread.</b>
     */
    public void hide() {
        GLFW.glfwHideWindow(windowInstance.getGLFWWindow());
    }

    /**
     * <b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @return the current value of the user-defined pointer of the specified window. The initial value is NULL.
     */
    public synchronized long getUserPointer() {
        long pointer = GLFW.glfwGetWindowUserPointer(windowInstance.getGLFWWindow());
        return pointer;
    }

    /**
     * <b>May only be called from the main thread.</b>
     * @return The monitor, or NULL if the window is in windowed mode or an error occurred.
     */
    public long getMonitor() {
        long monitor = GLFW.glfwGetWindowMonitor(windowInstance.getGLFWWindow());
        return monitor;
    }

    /**
     * http://www.glfw.org/docs/latest/window.html#window_attribs
     * @param attrib The window attribute whose value to return.
     * @return the value of an attribute of the specified window or its OpenGL or OpenGL ES context.
     */
    public int getAttrib(int attrib) {
        int returnAttrib = GLFW.glfwGetWindowAttrib(windowInstance.getGLFWWindow(), attrib);
        return returnAttrib;
    }

    /**
     * Destroys the specified window and its context. On calling this function, no further callbacks will be called
     * for this window.
     */
    public void destroy() {
        GLFW.glfwDestroyWindow(windowInstance.getGLFWWindow());
    }

    @Override
    public long getGLFWWindow() {
        return windowInstance.getGLFWWindow();
    }

    /** @return The contained window */
    public GLFWWindow getWindowInstance() {
        return windowInstance;
    }
}
