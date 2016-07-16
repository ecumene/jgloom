<img align="center" src="https://i.imgsafe.org/fa48aa1.png" width=600></img>

JGLOOm is a Java library for wrapping OpenGL objects in object-oriented wrappers. It
aims to contain the full functionality of OpenGL for all versions, accross all 
platforms. Utilities are included for file loading, object creation, and rendering.

Library-Agnostic File Loading
---------------------------------
One major goal of JGLOOm is to support file loading of several common formats. One 
perk of such a high-level design is agnostic support for loading file formats. This 
means that any and all implementations of a given GL object type are treated the 
same! Both a LWJGL and JOGL texture would use the same process for texture loading.

Object-Oriented OpenGL Objects
---------------------------------
OpenGL is notorious for its pointer system. You must use static methods to access 
objects, which isn't very object-oriented. JGLOOm provides object-oriented wrappers 
for OpenGL objects. This means that objects can be more easily managed. It also means 
that multiple OpenGL APIs can be unified to be almost interchangeable.