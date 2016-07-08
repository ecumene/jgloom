package jgloom.io.models.mtl;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;

public class MTLBuilder {
    private List<MTLMaterial> materials;
    private String name = null;
    private Vector4f ambient, diffuse, specular;
    
    public MTLBuilder() {
        materials = new ArrayList<MTLMaterial>();
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public void setAmbient(Vector4f a) {
        ambient = a;
    }
    
    public void setDiffuse(Vector4f d) {
        diffuse = d;
    }
    
    public void setSpecular(Vector4f s) {
        specular = s;
    }
    
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
    
    public List<MTLMaterial> getMaterials() {
        return materials;
    }
}
