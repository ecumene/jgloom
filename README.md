<img align="center" src="https://i.imgsafe.org/fa48aa1.png" width=600></img>

JGLOOm is a Java library for wrapping OpenGL objects in object-oriented wrappers. It
aims to contain the full functionality of OpenGL for all versions, accross all 
platforms and libraries. Utilities are included for file loading, object creation, 
and rendering.

Library-Agnostic File Loading
---------------------------------
One major goal of JGLOOm is to support file loading of several common formats. One 
perk of such a high-level design is library-agnostic support for loading file formats.
This means that any and all implementations of a given GL object type are treated the 
same! Both a LWJGL, JOGL, and WebGL texture would use the same process for texture 
loading.

Here are some libraries we plan to support:  
<a href="http://goharsha.com/WebGL4J/"><img src="http://goharsha.com/WebGL4J/logos/256/logo-blue-light.png" width="50"></img>
<a href="https://www.lwjgl.org/"><img src="https://1.bp.blogspot.com/_xki1C_H6jyM/SUWDhhkv4aI/AAAAAAAAABI/ZCrbNIQX3Lg/s200/lwjgl-logo" height="50">
<a href="http://jogamp.org/jogl/www/"><img src="https://i.imgur.com/56AwtdH.png" height="50"></a>

Here are some file formats we plan to support:
Images:
- [x] JPEG
- [x] PNG
- [x] TIFF
- [x] GIF
- [x] BMP    

Using [TwelveMonkey's](https://github.com/haraldk/TwelveMonkeys) ImageIO we support many more image formats as well.

Model Formats:
- [ ] MD5 - Skeletal Animations and all
- [ ] OBJ - Static Meshes
- [ ] FBX - Animations and full file support
- [ ] Collada - Using [JCollada](http://javacollada.sourceforge.net/)
- [ ] Blend - Possibly as a tech demo, these formats aren't meant for games

Object-Oriented OpenGL Objects
---------------------------------
OpenGL is notorious for its pointer system. You must use static methods to access 
objects, which isn't very object-oriented. JGLOOm provides object-oriented wrappers 
for OpenGL objects. This means that objects can be more easily managed. It also means 
that multiple OpenGL APIs can be unified to be almost interchangeable.
```
// GLFTextureImage2D is a group of functions that represent an OpenGL texture that supports Image2D.
// It's class extends from GLFTexture, with defines basic functions like bind and delete
// Containers implement these functions as a sign of what their library supports (some libraries might not support GLFTextureImage3D for example)
TextureLoader.load(GLFTextureImage2D, InputStream)
```
The code above displays the library's cross-library functionality. Here's anouther example showing how it would look per-library:
```
// LWJGL's textures, as well as WebGL, JOGL and everything else supports Image2D, so their supported in their containers:
GLTextureContainer lwjglTexture = new GLTextureContainer(LWJGLTextures.createTexture())
JGLTextureContainer joglTexture = new JGLTextureContainer(JOGLTextures.createTexture())
WGLTextureContainer webTexture = new WGLTextureContainer(WGLTextures.createTexture())

TextureLoader.load(lwjglTexture, new FileInputStream(...));
TextureLoader.load(joglTexture, new FileInputStream(...));
TextureLoader.load(lwebTexture, new FileInputStream(...));
```
