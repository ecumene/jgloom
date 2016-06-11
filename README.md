<img align="center" src="https://i.imgsafe.org/fa48aa1.png" width=600></img>
 
JGLOOm Java openGL: Object Oriented is Java library that wraps the functionality of the OpenGL API (LWJGL) in a more object-oriented package. It puts OpenGL objects into Java interfaces, and their functions into static factories /
container utils, allowing for code to be reactive to the user's OpenGL version/platform dependency.
 
How to use the facilities so far
---------------------------------
 
Here's an example of how the library is structured.
 
**Making GLFW windows**
 
```
GLFWWindow.init();
GLFWWindow.defaultWindowHints();                                                // Set the default window hints 
GLFWWindow.hint(GLFW.GLFW_SAMPLES, 4);                                          // Samples
GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);                            // Context version: 3.2
GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
GLFWWindow.hint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);                 // Forward compatible opengl
GLFWWindow.hint(GLFW.GLFW_OPENGL_PROFILE,        GLFW.GLFW_OPENGL_CORE_PROFILE);// Core profile
GLFWWindow.hint(GLFW.GLFW_RESIZABLE,             GL11.GL_FALSE);                // Not resizable
GLFWWindow.hint(GLFW.GLFW_VISIBLE,               GL11.GL_TRUE);                 // Make the window visible
GLFWWindow.setErrorCallback(new GLFWErrorCallback() {                           // Set error callback
    @Override
    public void invoke(int error, long description) {
        throw new GLNativeException("GLFW Error: " + error + " description: " + description);
    }
});
```
 
Look familiar? It should! We plan to implement most or all of the functions of OpenGL to be more easily interacted with standard java code. In the future there may be an OpenAL version.
 
**Manipulating the java objects - the power of OO**
```
// GLFWWindow is an interface (functional using Java 8) and has one simple integer, getWindow()
GFWWindow window = new GLFWWindowContainer(GLFWWindow.createWindow(640, 480, "GLFW Window", 0L, 0L));
window.shouldClose(); // Would have been glfwWindowShouldClose(window)
window.swapBuffers(); // Would have been glfwSwapBuffers(window)
window.iconify();     // Would have been glfwIconifyWindow(window)
GLFWWindow.makeContextCurrent(window); // Make the window current
GL.createCapabilities();               // Make OpenGL
```
 
By doing this the library allows for high-level functions to be made that's reactive to the container's functions. Such as something like a container that tracks the windows size with java integers, reducing costly API calls (Although you can revert back to using the API calls by using the default container!).
 
The library has similar objects to the one above already contributed. Here's a list of OpenGL objects we have completed (but are still in active development on) 
 
***OpenGL***
- GLBuffers (1.5)
- GLFrameBuffers (3.0)
- GLRenderBuffers (3.0)
- GLTextures
- GLVertexArrays (3.0)

***OpenGL - GLSL***
- GLSLPrograms (2.0)
- GLSLShaders (2.0)

***OpenGL - GLFW***
- GLFWWindows (lwjgl-3)
- GLFWMonitors (lwjgl-3)

Join us in the great wrapping! Currently this is in open-development and once the initial model is finished there will be a maven repository.
