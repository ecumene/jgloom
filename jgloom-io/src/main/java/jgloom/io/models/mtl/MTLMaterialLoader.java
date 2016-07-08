package jgloom.io.models.mtl;

import java.io.IOException;

import jgloom.io.resources.Resource;
import jgloom.io.resources.ResourceReader;

public class MTLMaterialLoader {
    public static final String MTL_COMMENT = "#";
    public static final String MTL_NEWMTL = "newmtl ";
    public static final String MTL_AMBIENT = "Ka ";
    public static final String MTL_DIFFUSE = "Kd ";
    public static final String MTL_SPECULAR = "Ks ";
    
    
    
    public static MTLMaterials loadMaterials(Resource resource) throws IOException {
        MTLBuilder builder = new MTLBuilder();
        startReading(resource, builder);
        return new MTLMaterials(builder);
    }
    
    private static void startReading(Resource resource, MTLBuilder builder) throws IOException {
        ResourceReader.readIndividualLines(resource, (String line) -> {
            parseLine(line, builder);
        });
    }
    
    private static void parseLine(String line, MTLBuilder builder) {
        
    }
}
