package org.jgloom.io.models.mtl;

import java.util.List;

/**
 * A class containing a collection of MTL materials
 */
public class MTLMaterials {
    /**
     * All materials loaded and stored in the collection
     */
    private List<MTLMaterial> materials;
    
    /**
     * Creates a collection from the given builder
     * @param builder Builder to take loaded materials from
     */
    public MTLMaterials(MTLBuilder builder) {
        this.materials = builder.getMaterials();
    }
    
    /**
     * @return MTL materials in the collection
     */
    public List<MTLMaterial> getMaterials() {
        return materials;
    }
    
    /**
     * Finds the MTL material with the given name
     * @param name Name of the material to find
     * @return Material corresponding to the given name
     */
    public MTLMaterial getMaterial(String name) {
        for (MTLMaterial m : materials)
            if (m.getName().equals(name))
                return m;
        return null;
    }
}
