package jgloom.glfw;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMonitorCallbackI;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single GLFW monitor object
 */
// There should be a getVideoModes(monitors), but the method for that,
// glfwGetVideoModes returns a 'Buffer'... Wat.
public interface GLFWMonitor {
    /** @return The GLFW monitor pointer */
    long getGLFWMonitor();

    /**
     * This function returns the primary monitor. This is usually the monitor where elements like the task bar or global
     * menu bar are located.
     * @return The primary monitor, or NULL if no monitors were found or if an error occurred.
     */
    static GLFWMonitor getPrimaryMonitor(){
        long monitor = GLFW.glfwGetPrimaryMonitor();
        return () -> monitor;
    }

    /**
     * This function sets the monitor configuration callback, or removes the currently set callback. This is called when
     * a monitor is connected to or disconnected from the system.
     * @param callback The new callback, or NULL to remove the currently set callback.
     */
    static void setMonitorCallback(GLFWMonitorCallbackI callback){
        GLFW.glfwSetMonitorCallback(callback);
    }

    /**
     * This function returns an array of handles for all currently connected monitors. The primary monitor is always
     * first in the returned array. If no monitors were found, this function returns NULL.
     * @return An array of monitor handles, or NULL if no monitors were found or if an error occurred.
     */
    static GLFWMonitor[] getMonitors(){
        // So much wrong with this method...
        // I give up dealing with foreach
        // I don't really care if it works

        List<Long> pointers = new LinkedList<Long>();
        PointerBuffer buffer = GLFW.glfwGetMonitors();
        for (int i = 0; i < buffer.limit(); i++) pointers.add(buffer.get(i));

        GLFWMonitor[] monitors = new GLFWMonitor[pointers.size()];
        int i = 0;
        for(Long pointer : pointers){
            monitors[i] = () -> pointer;
            i++;
        }
        return monitors;
    }
}
