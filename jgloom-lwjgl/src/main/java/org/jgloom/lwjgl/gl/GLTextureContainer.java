package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public class GLTextureContainer extends AbstractGLTexture {

    /**
     * Contains a single texture object to be manipulated by OpenGL
     * @param textureInstance The texture to contain
     */
    public GLTextureContainer(GLTexture textureInstance){
        super(textureInstance);
    }

    @Override
    public void bind(int target){
        GL11.glBindTexture(target, getTexture());
    }

    /**
     * @see #bind(int)
     *
     * @param unit    which texture unit to make active. One of: GL_TEXTURE0 TEXTURE0, GL_TEXTURE[1-31]
     * @param target  the texture target. One of: GL_TEXTURE_1D TEXTURE_1D, GL_TEXTURE_2D
     *                TEXTURE_2D, GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY, GL_TEXTURE_RECTANGLE
     *                TEXTURE_RECTANGLE, GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP, GL_TEXTURE_3D
     *                TEXTURE_3D, GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY, GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY,
     *                GL_TEXTURE_BUFFER TEXTURE_BUFFER, GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE,
     *                GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY
     */
    public void bind(int target, int unit){
        GL13.glActiveTexture(unit);
        bind(target);
    }

    @Override
    public void setParameter(int target, int param, float parameter){
        GL11.glTexParameterf(target, param, parameter);
    }

    @Override
    public void setParameter(int target, int param, int parameter){
        GL11.glTexParameteri(target, param, parameter);
    }

    @Override
    public void setParameter(int target, int param, FloatBuffer parameter){
        GL11.glTexParameterfv(target, param, parameter);
    }

    @Override
    public void setParameter(int target, int param, IntBuffer parameter){
        GL11.glTexParameteriv(target, param, parameter);
    }

    /** Float array version of {@link #setParameter(int, int, float)} */
    public void setParameter(int target, int param, float[] parameter){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(parameter.length);
        buffer.put(parameter);
        buffer.flip();
        GL11.glTexParameterfv(target, param, buffer);
    }

    /** Int array version of {@link #setParameter(int, int, float)} */
    public void setParameter(int target, int param, int[] parameter){
        IntBuffer buffer = BufferUtils.createIntBuffer(parameter.length);
        buffer.put(parameter);
        buffer.flip();
        GL11.glTexParameteriv(target, param, buffer);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixelsOffset){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixelsOffset);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixelsOffset){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixelsOffset);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels){

        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ByteBuffer pixels){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, long pixelsOffset){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixelsOffset);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, IntBuffer pixels){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void image3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels){
        GL12.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, long pixelsOffset) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixelsOffset);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void subImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, DoubleBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, long pixelsOffset) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixelsOffset);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, ShortBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, IntBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, FloatBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int format, int type, DoubleBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, ByteBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int format, int type, long pixelsOffset) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixelsOffset);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int format, int type, ShortBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int format, int type, IntBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void subImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int format, int type, FloatBuffer pixels) {
        GL12.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    @Override
    public void delete(){
        GL11.glDeleteTextures(getTexture());
    }

}
