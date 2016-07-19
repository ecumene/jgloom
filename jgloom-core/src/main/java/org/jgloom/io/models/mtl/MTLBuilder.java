package org.jgloom.io.models.mtl;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;

/**
 * Simple class for retaining wavefront MTL data while reading and parsing the MTL file
 */
public class MTLBuilder {
    /**
     * List of already created materials (already committed)
     */
    private List<MTLMaterial> materials;
    
    /**
     * Name of the currently loading material
     */
    private String name = null;
    
    /**
     * Lighting values for the currently loading material
     */
    private Vector4f ambient, diffuse, specular;
    
    /**
     * Creates and empty MTL building object with an empty material list
     */
    public MTLBuilder() {
        materials = new ArrayList<MTLMaterial>();
    }
    
    /**
     * @param n Name of the currently loading MTL material
     */
    public void setName(String n) {
        name = n;
    }
    
    /**
     * @param a Ambient RGBA color value of the currently loading MTL material
     */
    public void setAmbient(Vector4f a) {
        ambient = a;
    }
    
    /**
     * @param d Diffuse RGBA color value of the currently loading MTL material
     */
    public void setDiffuse(Vector4f d) {
        diffuse = d;
    }
    
    /**
     * @param s Specular RGBA color value of the currently loading MTL material
     */
    public void setSpecular(Vector4f s) {
        specular = s;
    }
    
    /**
     * Writes the currently loading MTL material to the material list
     * @return Created MTL material
     */
    public MTLMaterial commit() {
        String n = name;
        Vector4f a = ambient;
        Vector4f d = diffuse;
        Vector4f s = specular;
        if (n == null)
            return null;
        
        MTLMaterial material = new MTLMaterial() {
            @Override
            public Vector4f getAmbient() {
                return a;
            }

            @Override
            public Vector4f getDiffuse() {
                return d;
            }

            @Override
            public Vector4f getSpecular() {
                return s;
            }

            @Override
            public String getName() {
                return n;
            }
        };
        
        materials.add(material);
        return material;
    }
    
    /**
     * @return Stored MTL material list
     */
    public List<MTLMaterial> getMaterials() {
        return materials;
    }
}
