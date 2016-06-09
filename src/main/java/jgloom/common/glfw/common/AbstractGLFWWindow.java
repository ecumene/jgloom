package jgloom.common.glfw.common;

import jgloom.common.glfw.GLFWWindowContainer;
import jgloom.glfw.GLFWWindow;

import java.nio.IntBuffer;

/**
 * A shell class containing functions for manipulating a given {@link GLFWWindow}
 */
public abstract class AbstractGLFWWindow implements GLFWWindow {
    private GLFWWindow windowInstance;

    /**
     * Initializes the GLFW window container
     * @param windowInstance The GLFW Window to track and contain
     */
    public AbstractGLFWWindow(GLFWWindow windowInstance){
        this.windowInstance = windowInstance;
    }

    /**<b>May only be called from the main thread.</b>
     * @param title The window's title*/
    public abstract void setTitle(String title);

    /**<b>May only be called from the main thread.</b>
     * @param newPointer The new value.*/
    public abstract void setUserPointer(long newPointer);

    /**getFramebufferSize[0] = Frame buffer width
     * getFramebufferSize[1] = Frame buffer height
     * <b>May only be called from the main thread.</b>
     * @return The size, in pixels, of the framebuffer of the specified window, not the
     *         windows dimensions. If you wish to get the window dimensions check
     *         {@link GLFWWindowContainer#getSize()}*/
    public abstract IntBuffer[] getFramebufferSize();

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
    public abstract IntBuffer[] getFrameSize();

    /**getFramebufferSize[0] = Window's width
     * getFramebufferSize[1] = Window's height
     * <b>May only be called from the main thread.</b>
     * @return The size, in screen coordinates, of the client area of the specified window.*/
    public abstract IntBuffer[] getSize();

    /**<b>May only be called from the main thread.</b>
     * @return the position, in screen coordinates, of the upper-left corner of the client area of the specified
     *         window.*/
    public abstract IntBuffer[] getPosition();

    /**This function swaps the front and back buffers of the specified window. If the swap interval is greater than
     * zero, the GPU driver waits the specified number of screen updates before swapping the buffers.
     * <b>May be called from any thread.</b>*/
    public abstract void swapBuffers();

    /**This function sets the value of the close flag of the specified window. This can be used to override the user's
     * attempt to close the window, or to signal that it should be closed.
     * <b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @param closeFlag The new value.*/
    public abstract void setShouldClose(boolean closeFlag);

    /**@return If the GLFW Window is closing of not.*/
    public abstract boolean shouldClose();

    /**This function makes the specified window visible if it was previously hidden. If the window is already visible or
     * is in full screen mode, this function does nothing.*/
    public abstract void show();

    /**This function restores the specified window if it was previously iconified (minimized). If the window is already
     * restored, this function does nothing
     * <b>May only be called from the main thread.</b>*/
    public abstract void restore();

    /**This function iconifies (minimizes) the specified window if it was previously restored. If the window is already
     * iconified, this function does nothing.
     * <b>May only be called from the main thread.</b>*/
    public abstract void iconify();

    /**This function hides the specified window if it was previously visible. If the window is already hidden or is in
     * full screen mode, this function does nothing.
     * <b>May only be called from the main thread.</b>*/
    public abstract void hide();

    /**<b>This function may be called from any thread. Access is not synchronized, but the API call is.</b>
     * @return the current value of the user-defined pointer of the specified window. The initial value is NULL.*/
    public abstract long getUserPointer();

    /**<b>May only be called from the main thread.</b>
     * @return The monitor, or NULL if the window is in windowed mode or an error occurred.*/
    public abstract long getMonitor();

    /**http://www.glfw.org/docs/latest/window.html#window_attribs
     * @param attrib The window attribute whose value to return.
     * @return the value of an attribute of the specified window or its OpenGL or OpenGL ES context.*/
    public abstract int getAttrib(int attrib);

    /**Destroys the specified window and its context. On calling this function, no further callbacks will be called
     * for this window.*/
    public abstract void destroy();

    @Override
    public long getGLFWWindow() {
        return windowInstance.getGLFWWindow();
    }

    /** @return The contained window */
    public GLFWWindow getWindowInstance() {
        return windowInstance;
    }
}
