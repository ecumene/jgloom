package jgloom.io.models.mtl;

import jgloom.io.models.ModelMaterial;

/**
 * Interface extending {@link ModelMaterial} which adds a name parameter
 */
public interface MTLMaterial extends ModelMaterial {
    /**
     * @return Name of the MTL material, specified in the MTL file
     */
    String getName();
}
