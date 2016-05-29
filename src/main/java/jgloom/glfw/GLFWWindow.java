package jgloom.glfw;

import jgloom.GLNativeException;
import jgloom.common.glfw.GLFWVersion;
import jgloom.common.glfw.GLFWWindowContainer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import java.nio.IntBuffer;

/**
 * Represents a single GLFW window object
 */
public interface GLFWWindow {
    /** @return The GLFW window pointer */
    long getGLFWWindow();

    /**
     * This function sets the error callback, which is called with an error code and a human-readable description each
     * time a GLFW error occurs.
     * The error callback is called on the thread where the error occurred. If you are using GLFW from multiple threads,
     * your error callback needs to be written accordingly.
     * @param callback The new callback, or NULL to remove the currently set callback
     * @return The previously set callback, or NULL if no callback was set.
     */
    static GLFWErrorCallback setErrorCallback(GLFWErrorCallback callback) {
        GLFWErrorCallback oldCallback = GLFW.glfwSetErrorCallback(callback);
        return oldCallback;
    }

    /**
     * This function makes the OpenGL or OpenGL ES context of the specified window current on the calling thread. A
     * context can only be made current on a single thread at a time and each thread can have only a single current
     * context at a time.
     *<b>This function may be called from any thread.</b>
     * @param window The window whose context to make current, or NULL to detach the current context.
     */
    static void makeContextCurrent(GLFWWindow window) {
        GLFW.glfwMakeContextCurrent(window.getGLFWWindow());
    }

    /**
     * This function returns the window whose OpenGL or OpenGL ES context is current on the calling thread.
     *<b>This function may be called from any thread.</b>
     * @return the window whose OpenGL or OpenGL ES context is current on the calling thread.
     */
    static GLFWWindow getCurrentContext() {
        long current = GLFW.glfwGetCurrentContext();
        return () -> current;
    }

    /**
     * This function initializes the GLFW library. Before most GLFW functions can be used, GLFW must be initialized,
     * and before an application terminates GLFW should be terminated in order to free any resources allocated during or
     * after initialization.
     * <b>May only be called from the main thread.</b>
     * {@link GLFWWindow#terminate()} can be called before {@link GLFWWindow#init()}
     * @return true if successful false if not
     */
    static boolean init() {
        return GLFW.glfwInit();
    }

    /**
     * This function sets the swap interval for the current context, i.e. the number of screen updates to wait from the
     * time {@link GLFWWindowContainer#swapBuffers()} was called before swapping the buffers and returning. This is
     * sometimes called vertical synchronization, vertical retrace synchronization or just vsync.
     * @param interval The minimum number of screen updates to wait for until the buffers are swapped by
     *                 {@link GLFWWindowContainer#swapBuffers()}
     */
    static void setSwapInterval(int interval) {
        GLFW.glfwSwapInterval(interval);
    }

    /**
     * This function destroys all remaining windows and cursors, restores any modified gamma ramps and frees any other
     * allocated resources. Once this function is called, you must again call {@link GLFWWindow#init()} successfully \
     * before you will be able to use most GLFW functions.
     * <b>May only be called from the main thread.</b>
     * {@link GLFWWindow#terminate()} can be called before {@link GLFWWindow#init()}
     */
    static void terminate() {
        GLFW.glfwTerminate();
    }

    /**
     * This function retrieves the major, minor and revision numbers of the GLFW library. It is intended for when you
     * are using GLFW as a shared library and want to ensure that you are using the minimum required version.
     * <b>This function may be called from any thread, synchronized</b>
     * This function may be called before {@link GLFW#glfwInit()}.
     * {@link GLFWWindow#getVersion()} can be called before {@link GLFWWindow#init()}
     * @return  the major, minor and revision numbers of the GLFW library. It is intended for when you are using GLFW
     *          as a shared library and want to ensure that you are using the minimum required version
     */
    static GLFWVersion getVersion() {
        // Ugly... But it works!
        final IntBuffer major = BufferUtils.createIntBuffer(1);
        final IntBuffer minor = BufferUtils.createIntBuffer(1);
        final IntBuffer patch = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetVersion(major, minor, patch);
        GLNativeException.checkOGL();

        return new GLFWVersion() {
            @Override
            public int getMajorVersion() {
                return major.get(0);
            }

            @Override
            public int getMinorVersion() {
                return minor.get(0);
            }

            @Override
            public int getPatchVersion() {
                return patch.get(0);
            }
        };
    }

    /**
     * This function returns the compile-time generated version string of the GLFW library binary. It describes the
     * version, platform, compiler and any platform-specific compile-time options.
     * {@link GLFWWindow#getVersionString()} can be called before {@link GLFWWindow#init()}
     * @return The GLFW version string.
     */
    static String getVersionString() {
        return GLFW.glfwGetVersionString();
    }

    /**
     * This function creates a window and its associated OpenGL or OpenGL ES context. Most of the options controlling
     * how the window and its context should be created are specified with window hints.
     * <b>May only be called from the main thread.</b>
     * @param width   The desired width, in screen coordinates, of the window. This must be greater than zero.
     * @param height  The desired height, in screen coordinates, of the window. This must be greater than zero.
     * @param title   The initial, UTF-8 encoded window title.
     * @param monitor The monitor to use for full screen mode, or NULL to use windowed mode.
     * @param share   The window whose context to share resources with, or NULL to not share resources.
     * @return The handle of the created window, or NULL if an error occurred.
     */
    static GLFWWindow createWindow(int width, int height, String title, long monitor, long share) {
        long window = GLFW.glfwCreateWindow(width, height, title, monitor, share);
        return () -> window;
    }

    /**
     * This function sets hints for the next call to {@link GLFWWindow#createWindow(int, int, String, long, long)}. The
     * hints, once set, retain their values until changed by a call to {@link GLFWWindow#hint(int, int)} or
     * {@link GLFWWindow#defaultWindowHints()}, or until the library is terminated.
     * <b>May only be called from the main thread.</b>
     * @param target The window hint to set
     * @param hint   The new value to set
     */
    static void hint(int target, int hint) {
        GLFW.glfwWindowHint(target, hint);
    }

    /**
     * Resets all windows to their default values
     * http://www.glfw.org/docs/latest/window.html#window_hints_values
     * <b>May only be called from the main thread.</b>
     */
    static void defaultWindowHints() {
        GLFW.glfwDefaultWindowHints();
    }

    /**
     * This function processes only those events that are already in the event queue and then returns immediately.
     * Processing events will cause the window and input callbacks associated with those events to be called
     * <b>May only be called from the main thread.</b>
     */
    static void pollEvents() {
        GLFW.glfwPollEvents();
    }

    /**
     * This function posts an empty event from the current thread to the event queue, causing {@link GLFWWindow#waitEvents()} to return.
     * <b>May only be called from the main thread.</b>
     */
    static void postEmptyEvent() {
        GLFW.glfwPostEmptyEvent();
    }

    /**
     * This function puts the calling thread to sleep until at least one event is available in the event queue. Once
     * one or more events are available, it behaves exactly like {@link GLFWWindow#pollEvents()}, i.e. the events in
     * the queue are processed and the function then returns immediately. Processing events will cause the window and
     * input callbacks associated with those events to be called.
     * <b>May only be called from the main thread.</b>
     */
    static void waitEvents() {
        GLFW.glfwWaitEvents();
    }
}
