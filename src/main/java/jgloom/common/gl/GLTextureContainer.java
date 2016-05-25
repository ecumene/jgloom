package jgloom.common.gl;

import jgloom.gl.GLTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public class GLTextureContainer implements GLTexture {
    private GLTexture textureInstance;

    /**
     * Contains a single texture object to be manipulated by OpenGL
     * @param textureInstance The texture to contain
     */
    public GLTextureContainer(GLTexture textureInstance){
        this.textureInstance = textureInstance;
    }

    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glBindTexture.xhtml">OpenGL SDK Reference</a></p>
     *
     * Binds the a texture to a texture target and texture unit
     *
     * <p>While a texture object is bound, GL operations on the target to which it is bound affect the bound object, and
     * queries of the target to which it is bound return state from the bound object. If texture mapping of the
     * dimensionality of the target to which a texture object is bound is enabled, the state of the bound texture object
     * directs the texturing operation.</p>
     *
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glActiveTexture.xhtml">OpenGL SDK Reference</a></p>
     *
     * Selects which texture unit subsequent texture state calls will affect. The number of texture units an implementation supports is implementation
     * dependent.
     *
     * @param unit    which texture unit to make active. One of:<br>{@link GL13#GL_TEXTURE0 TEXTURE0}, GL_TEXTURE[1-31]
     * @param target  the texture target. One of:<br>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}, {@link GL11#GL_TEXTURE_2D
     *                TEXTURE_2D}, {@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}, {@link GL31#GL_TEXTURE_RECTANGLE
     *                TEXTURE_RECTANGLE}, {@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}, {@link GL12#GL_TEXTURE_3D
     *                TEXTURE_3D}, {@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}, {@link
     *                GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}, {@link GL31#GL_TEXTURE_BUFFER
     *                TEXTURE_BUFFER}, {@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}, {@link
     *                GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}
     */
    public void bind(int target, int unit){
        GL13.glActiveTexture(unit);
        bind(target);
    }

    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glBindTexture.xhtml">OpenGL SDK Reference</a></p>
     *
     * Binds the a texture to a texture target.
     *
     * <p>While a texture object is bound, GL operations on the target to which it is bound affect the bound object, and
     * queries of the target to which it is bound return state from the bound object. If texture mapping of the
     * dimensionality of the target to which a texture object is bound is enabled, the state of the bound texture object
     * directs the texturing operation.</p>
     *
     * @param target  the texture target. One of:<br>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}, {@link GL11#GL_TEXTURE_2D
     *                TEXTURE_2D}, {@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}, {@link GL31#GL_TEXTURE_RECTANGLE
     *                TEXTURE_RECTANGLE}, {@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}, {@link GL12#GL_TEXTURE_3D
     *                TEXTURE_3D}, {@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}, {@link
     *                GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}, {@link GL31#GL_TEXTURE_BUFFER
     *                TEXTURE_BUFFER}, {@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}, {@link
     *                GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}
     */
    public void bind(int target){
        GL11.glBindTexture(target, textureInstance.getTexture());
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, float parameter){
        GL11.glTexParameterf(target, param, parameter);
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, int parameter){
        GL11.glTexParameteri(target, param, parameter);
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, float[] parameter){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(parameter.length);
        buffer.put(parameter);
        buffer.flip();
        GL11.glTexParameterfv(target, param, buffer);
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, int[] parameter){
        IntBuffer buffer = BufferUtils.createIntBuffer(parameter.length);
        buffer.put(parameter);
        buffer.flip();
        GL11.glTexParameteriv(target, param, buffer);
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, FloatBuffer parameter){
        GL11.glTexParameterfv(target, param, parameter);
    }

    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    public void parameter(int target, int param, IntBuffer parameter){
        GL11.glTexParameteriv(target, param, parameter);
    }

    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glTexImage1D.xhtml">OpenGL SDK Reference</a></p>
     *
     * @param target         the texture target. One of:<br>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}, {@link GL11#GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D}
     * @param level          the level-of-detail number
     * @param internalformat the texture internal format
     * @param width          the texture width
     * @param border         the texture border width
     * @param format         the texel data format
     * @param type           the texel data type
     * @param pixels         the texel data
     */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    /** Buffer object offset version of: {@link #image1D(int, int, int, int, int, int, int, ByteBuffer)} */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixelsOffset){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixelsOffset);
    }

    /** ShortBuffer version of: {@link #image1D(int, int, int, int, int, int, int, ByteBuffer)} */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    /** IntBuffer version of: {@link #image1D(int, int, int, int, int, int, int, ByteBuffer)} */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    /** FloatBuffer version of: {@link #image1D(int, int, int, int, int, int, int, ByteBuffer)} */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    /** DoubleBuffer version of: {@link #image1D(int, int, int, int, int, int, int, ByteBuffer)} */
    public void image1D(int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels){
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glTexImage2D.xhtml">OpenGL SDK Reference</a></p>
     *
     * @param target         the texture target. One of:<br>{@link GL11#GL_TEXTURE_2D TEXTURE_2D}, {@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}, {@link GL31#GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE}, {@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}, {@link GL11#GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D}, {@link GL30#GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY}, {@link GL31#GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE}, {@link GL13#GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP}
     * @param level          the level-of-detail number
     * @param internalformat the texture internal format. One of:<br>{@link GL11#GL_RED RED}, {@link GL30#GL_RG RG}, {@link GL11#GL_RGB RGB}, {@link GL11#GL_RGBA RGBA}, {@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}, {@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}, {@link GL30#GL_R8 R8}, {@link GL31#GL_R8_SNORM R8_SNORM}, {@link GL30#GL_R16 R16}, {@link GL31#GL_R16_SNORM R16_SNORM}, {@link GL30#GL_RG8 RG8}, {@link GL31#GL_RG8_SNORM RG8_SNORM}, {@link GL30#GL_RG16 RG16}, {@link GL31#GL_RG16_SNORM RG16_SNORM}, {@link GL11#GL_R3_G3_B2 R3_G3_B2}, {@link GL11#GL_RGB4 RGB4}, {@link GL11#GL_RGB5 RGB5}, {@link GL41#GL_RGB565 RGB565}, {@link GL11#GL_RGB8 RGB8}, {@link GL31#GL_RGB8_SNORM RGB8_SNORM}, {@link GL11#GL_RGB10 RGB10}, {@link GL11#GL_RGB12 RGB12}, {@link GL11#GL_RGB16 RGB16}, {@link GL31#GL_RGB16_SNORM RGB16_SNORM}, {@link GL11#GL_RGBA2 RGBA2}, {@link GL11#GL_RGBA4 RGBA4}, {@link GL11#GL_RGB5_A1 RGB5_A1}, {@link GL11#GL_RGBA8 RGBA8}, {@link GL31#GL_RGBA8_SNORM RGBA8_SNORM}, {@link GL11#GL_RGB10_A2 RGB10_A2}, {@link GL33#GL_RGB10_A2UI RGB10_A2UI}, {@link GL11#GL_RGBA12 RGBA12}, {@link GL11#GL_RGBA16 RGBA16}, {@link GL31#GL_RGBA16_SNORM RGBA16_SNORM}, {@link GL21#GL_SRGB8 SRGB8}, {@link GL21#GL_SRGB8_ALPHA8 SRGB8_ALPHA8}, {@link GL30#GL_R16F R16F}, {@link GL30#GL_RG16F RG16F}, {@link GL30#GL_RGB16F RGB16F}, {@link GL30#GL_RGBA16F RGBA16F}, {@link GL30#GL_R32F R32F}, {@link GL30#GL_RG32F RG32F}, {@link GL30#GL_RGB32F RGB32F}, {@link GL30#GL_RGBA32F RGBA32F}, {@link GL30#GL_R11F_G11F_B10F R11F_G11F_B10F}, {@link GL30#GL_RGB9_E5 RGB9_E5}, {@link GL30#GL_R8I R8I}, {@link GL30#GL_R8UI R8UI}, {@link GL30#GL_R16I R16I}, {@link GL30#GL_R16UI R16UI}, {@link GL30#GL_R32I R32I}, {@link GL30#GL_R32UI R32UI}, {@link GL30#GL_RG8I RG8I}, {@link GL30#GL_RG8UI RG8UI}, {@link GL30#GL_RG16I RG16I}, {@link GL30#GL_RG16UI RG16UI}, {@link GL30#GL_RG32I RG32I}, {@link GL30#GL_RG32UI RG32UI}, {@link GL30#GL_RGB8I RGB8I}, {@link GL30#GL_RGB8UI RGB8UI}, {@link GL30#GL_RGB16I RGB16I}, {@link GL30#GL_RGB16UI RGB16UI}, {@link GL30#GL_RGB32I RGB32I}, {@link GL30#GL_RGB32UI RGB32UI}, {@link GL30#GL_RGBA8I RGBA8I}, {@link GL30#GL_RGBA8UI RGBA8UI}, {@link GL30#GL_RGBA16I RGBA16I}, {@link GL30#GL_RGBA16UI RGBA16UI}, {@link GL30#GL_RGBA32I RGBA32I}, {@link GL30#GL_RGBA32UI RGBA32UI}, {@link GL14#GL_DEPTH_COMPONENT16 DEPTH_COMPONENT16}, {@link GL14#GL_DEPTH_COMPONENT24 DEPTH_COMPONENT24}, {@link GL14#GL_DEPTH_COMPONENT32 DEPTH_COMPONENT32}, {@link GL30#GL_DEPTH24_STENCIL8 DEPTH24_STENCIL8}, {@link GL30#GL_DEPTH_COMPONENT32F DEPTH_COMPONENT32F}, {@link GL30#GL_DEPTH32F_STENCIL8 DEPTH32F_STENCIL8}, {@link GL30#GL_COMPRESSED_RED COMPRESSED_RED}, {@link GL30#GL_COMPRESSED_RG COMPRESSED_RG}, {@link GL13#GL_COMPRESSED_RGB COMPRESSED_RGB}, {@link GL13#GL_COMPRESSED_RGBA COMPRESSED_RGBA}, {@link GL21#GL_COMPRESSED_SRGB COMPRESSED_SRGB}, {@link GL21#GL_COMPRESSED_SRGB_ALPHA COMPRESSED_SRGB_ALPHA}, {@link GL30#GL_COMPRESSED_RED_RGTC1 COMPRESSED_RED_RGTC1}, {@link GL30#GL_COMPRESSED_SIGNED_RED_RGTC1 COMPRESSED_SIGNED_RED_RGTC1}, {@link GL30#GL_COMPRESSED_RG_RGTC2 COMPRESSED_RG_RGTC2}, {@link GL30#GL_COMPRESSED_SIGNED_RG_RGTC2 COMPRESSED_SIGNED_RG_RGTC2}, {@link GL42#GL_COMPRESSED_RGBA_BPTC_UNORM COMPRESSED_RGBA_BPTC_UNORM}, {@link GL42#GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM COMPRESSED_SRGB_ALPHA_BPTC_UNORM}, {@link GL42#GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT COMPRESSED_RGB_BPTC_SIGNED_FLOAT}, {@link GL42#GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT}, {@link GL43#GL_COMPRESSED_RGB8_ETC2 COMPRESSED_RGB8_ETC2}, {@link GL43#GL_COMPRESSED_SRGB8_ETC2 COMPRESSED_SRGB8_ETC2}, {@link GL43#GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2}, {@link GL43#GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2}, {@link GL43#GL_COMPRESSED_RGBA8_ETC2_EAC COMPRESSED_RGBA8_ETC2_EAC}, {@link GL43#GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC COMPRESSED_SRGB8_ALPHA8_ETC2_EAC}, {@link GL43#GL_COMPRESSED_R11_EAC COMPRESSED_R11_EAC}, {@link GL43#GL_COMPRESSED_SIGNED_R11_EAC COMPRESSED_SIGNED_R11_EAC}, {@link GL43#GL_COMPRESSED_RG11_EAC COMPRESSED_RG11_EAC}, {@link GL43#GL_COMPRESSED_SIGNED_RG11_EAC COMPRESSED_SIGNED_RG11_EAC}, see {@link EXTTextureCompressionS3TC}, see {@link EXTTextureCompressionLATC}, see {@link ATITextureCompression3DC}
     * @param width          the texture width
     * @param height         the texture height
     * @param border         the texture border width
     * @param format         the texel data format. One of:<br>{@link GL11#GL_STENCIL_INDEX STENCIL_INDEX}, {@link GL11#GL_DEPTH_COMPONENT DEPTH_COMPONENT}, {@link GL30#GL_DEPTH_STENCIL DEPTH_STENCIL}, {@link GL11#GL_RED RED}, {@link GL11#GL_GREEN GREEN}, {@link GL11#GL_BLUE BLUE}, {@link GL11#GL_ALPHA ALPHA}, {@link GL30#GL_RG RG}, {@link GL11#GL_RGB RGB}, {@link GL11#GL_RGBA RGBA}, {@link GL12#GL_BGR BGR}, {@link GL12#GL_BGRA BGRA}, {@link GL11#GL_LUMINANCE LUMINANCE}, {@link GL11#GL_LUMINANCE_ALPHA LUMINANCE_ALPHA}, {@link GL30#GL_RED_INTEGER RED_INTEGER}, {@link GL30#GL_GREEN_INTEGER GREEN_INTEGER}, {@link GL30#GL_BLUE_INTEGER BLUE_INTEGER}, {@link GL30#GL_ALPHA_INTEGER ALPHA_INTEGER}, {@link GL30#GL_RG_INTEGER RG_INTEGER}, {@link GL30#GL_RGB_INTEGER RGB_INTEGER}, {@link GL30#GL_RGBA_INTEGER RGBA_INTEGER}, {@link GL30#GL_BGR_INTEGER BGR_INTEGER}, {@link GL30#GL_BGRA_INTEGER BGRA_INTEGER}
     * @param type           the texel data type. One of:<br>{@link GL11#GL_UNSIGNED_BYTE UNSIGNED_BYTE}, {@link GL11#GL_BYTE BYTE}, {@link GL11#GL_UNSIGNED_SHORT UNSIGNED_SHORT}, {@link GL11#GL_SHORT SHORT}, {@link GL11#GL_UNSIGNED_INT UNSIGNED_INT}, {@link GL11#GL_INT INT}, {@link GL30#GL_HALF_FLOAT HALF_FLOAT}, {@link GL11#GL_FLOAT FLOAT}, {@link GL12#GL_UNSIGNED_BYTE_3_3_2 UNSIGNED_BYTE_3_3_2}, {@link GL12#GL_UNSIGNED_BYTE_2_3_3_REV UNSIGNED_BYTE_2_3_3_REV}, {@link GL12#GL_UNSIGNED_SHORT_5_6_5 UNSIGNED_SHORT_5_6_5}, {@link GL12#GL_UNSIGNED_SHORT_5_6_5_REV UNSIGNED_SHORT_5_6_5_REV}, {@link GL12#GL_UNSIGNED_SHORT_4_4_4_4 UNSIGNED_SHORT_4_4_4_4}, {@link GL12#GL_UNSIGNED_SHORT_4_4_4_4_REV UNSIGNED_SHORT_4_4_4_4_REV}, {@link GL12#GL_UNSIGNED_SHORT_5_5_5_1 UNSIGNED_SHORT_5_5_5_1}, {@link GL12#GL_UNSIGNED_SHORT_1_5_5_5_REV UNSIGNED_SHORT_1_5_5_5_REV}, {@link GL12#GL_UNSIGNED_INT_8_8_8_8 UNSIGNED_INT_8_8_8_8}, {@link GL12#GL_UNSIGNED_INT_8_8_8_8_REV UNSIGNED_INT_8_8_8_8_REV}, {@link GL12#GL_UNSIGNED_INT_10_10_10_2 UNSIGNED_INT_10_10_10_2}, {@link GL12#GL_UNSIGNED_INT_2_10_10_10_REV UNSIGNED_INT_2_10_10_10_REV}, {@link GL30#GL_UNSIGNED_INT_24_8 UNSIGNED_INT_24_8}, {@link GL30#GL_UNSIGNED_INT_10F_11F_11F_REV UNSIGNED_INT_10F_11F_11F_REV}, {@link GL30#GL_UNSIGNED_INT_5_9_9_9_REV UNSIGNED_INT_5_9_9_9_REV}, {@link GL30#GL_FLOAT_32_UNSIGNED_INT_24_8_REV FLOAT_32_UNSIGNED_INT_24_8_REV}, {@link GL11#GL_BITMAP BITMAP}
     * @param pixels         the texel data
     */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    /** Buffer object offset version of: {@link #image2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixelsOffset){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixelsOffset);
    }

    /** ShortBuffer version of: {@link #image2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    /** IntBuffer version of: {@link #image2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    /** FloatBuffer version of: {@link #image2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    /** DoubleBuffer version of: {@link #image2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    public void image2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels){
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    /**<p><a href="http://www.opengl.org/sdk/docs/man/html/glDeleteTextures.xhtml">OpenGL SDK Reference</a></p>
     *
     * Deletes texture objects. After a texture object is deleted, it has no contents or dimensionality, and its name is
     * again unused. If a texture that is currently bound to any of the target bindings of {@link GL11#glBindTexture
     * BindTexture} is deleted, it is as though {@link GL11#glBindTexture BindTexture} had been executed with the same
     * target and texture zero. Additionally, special care must be taken when deleting a texture if any of the images of
     * the texture are attached to a framebuffer object.
     *
     * <p>Unused names in textures that have been marked as used for the purposes of {@link GL11#glGenTextures
     * GenTextures} are marked as unused again. Unused names in textures are silently ignored, as is the name zero.</p>
     */
    public void delete(){
        GL11.glDeleteTextures(textureInstance.getTexture());
    }

    @Override
    public int getTexture() {
        return textureInstance.getTexture();
    }
}
