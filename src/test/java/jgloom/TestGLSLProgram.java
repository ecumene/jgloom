package jgloom;

import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GLContext;

import jgloom.common.SharedLibraryLoader;
import jgloom.common.gl.glsl.GLSLProgramContainer;
import jgloom.common.gl.glsl.GLSLPrograms;
import jgloom.common.gl.glsl.GLSLShaderContainer;
import jgloom.common.gl.glsl.GLSLShaders;
import jgloom.common.glfw.GLFWWindowContainer;
import jgloom.common.glfw.GLFWWindows;

public class TestGLSLProgram {
	String fragmentSrc = "#version 110\n void main() { gl_FragColor = vec4(0, 1, 0, 1); }";
	GLFWWindowContainer window;
	GLSLShaderContainer fragment;
	GLSLProgramContainer program;

	@Test
	public void testGLSLProgram() {
		SharedLibraryLoader.load();
		GLFWWindows.init();
		window = new GLFWWindowContainer(GLFWWindows.createWindow(800, 450, "Window 1", 0L, 0L));
		GLFWWindows.makeContextCurrent(window);
		GLContext.createFromCurrent();

		program = new GLSLProgramContainer(GLSLPrograms.createProgram());
		fragment = new GLSLShaderContainer(GLSLShaders.createShader(GL20.GL_FRAGMENT_SHADER));
		fragment.uploadSource(fragmentSrc);
		fragment.compileShader();
		program.attachGLSLShader(fragment);
		program.link();
		GLSLPrograms.useProgram(program);

		while (!window.shouldClose()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glVertex2f(-1, -1);
			GL11.glVertex2f(1, -1);
			GL11.glVertex2f(0, 1);
			GL11.glEnd();

			window.swapBuffers();
			GLFWWindows.pollEvents();
		}

		program.destroy();
		fragment.destroy();
		window.destroy();
		GLFWWindows.terminate();
	}
}
