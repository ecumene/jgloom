package org.jgloom.lwjgl;

import org.jgloom.utils.enums.EnumStore;
import org.jgloom.utils.enums.GLEnumLookup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * This is a utility class that is to be used when an object needs LWJGL enumerators, such as GL_TRUE, GL_ZERO,
 * GL_TEXTURE2D. How it works is, given an object you use {@link #store(Object...)}. Then the store searches for any
 * fields matching the {@link org.jgloom.utils.enums.GLEnumLookup}, then sets it using LWJGL's enumerator database via
 * {@link org.lwjgl.opengl.GL11}, {@link org.lwjgl.opengl.GL12}, {@link org.lwjgl.opengl.GL13} and so on.
 */
public class LWJGLEnumStore implements EnumStore {

    private static LWJGLEnumStore INSTANCE = new LWJGLEnumStore();

    @Override
    public void store(Object ... objects) throws ReflectiveOperationException{
        for(Object object : objects){
            Field[] fields = object.getClass().getFields();
            for(Field field : fields) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations)
                    if (annotation instanceof GLEnumLookup) {
                        lwjglStore(((GLEnumLookup) annotation).glVersion(), ((GLEnumLookup) annotation).glEnum(),
                                object, field);
                    }
            }
        }
    }

    public String getStorePackage(){
        return "org.lwjgl.opengl";
    }

    protected void lwjglStore(String version, String enumName, Object object, Field field) throws IllegalAccessException {
        try {
            Class<?> clazz = Class.forName((getStorePackage() + ".GL" + version.toUpperCase()));
            field.setInt(object, clazz.getField("GL_" + enumName).getInt(null));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class not found in org.lwjgl.opengl: GL" + version);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Enum not found in class, GL" + version + ": GL_" + enumName);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException("Illegal access to: GL" + version + ": GL_" + enumName);
        }
    }

    public static LWJGLEnumStore getInstance() {
        return INSTANCE;
    }
}
