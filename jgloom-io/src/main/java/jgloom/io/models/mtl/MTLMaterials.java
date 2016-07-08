package jgloom.io.models.mtl;

import java.util.List;

public class MTLMaterials {
    private List<MTLMaterial> materials;
    
    public MTLMaterials(MTLBuilder builder) {
        this.materials = builder.getMaterials();
    }
    
    public List<MTLMaterial> getMaterials() {
        return materials;
    }
    
    public MTLMaterial getMaterial(String name) {
        for (MTLMaterial m : materials)
            if (m.getName().equals(name))
                return m;
        return null;
    }
}
