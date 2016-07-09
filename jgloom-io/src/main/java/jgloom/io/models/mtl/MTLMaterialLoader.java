package jgloom.io.models.mtl;

import java.io.IOException;

import org.joml.Vector4f;

import jgloom.io.resources.Resource;
import jgloom.io.resources.ResourceReader;

/**
 * Loads and parses a MTL file into separate MTL materials usable in models and other
 */
public class MTLMaterialLoader {
    public static final String MTL_COMMENT = "#";
    public static final String MTL_NEWMTL = "newmtl ";
    public static final String MTL_AMBIENT = "Ka ";
    public static final String MTL_DIFFUSE = "Kd ";
    public static final String MTL_SPECULAR = "Ks ";
    
    /**
     * Loads material data from the given MTL file and returns it as a collection of MTL materials
     * @param resource Resource leading to the MTL file containing material data
     * @return Created collection of MTL material information
     * @throws IOException In case MTL loading fails
     */
    public static MTLMaterials loadMaterials(Resource resource) throws IOException {
        MTLBuilder builder = new MTLBuilder();
        startReading(resource, builder);
        builder.commit();
        return new MTLMaterials(builder);
    }
    
    /**
     * Reads the given resource as individual lines and calls the parse method for each one
     * @param resource
     * @param builder
     * @throws IOException
     */
    private static void startReading(Resource resource, MTLBuilder builder) throws IOException {
        ResourceReader.readIndividualLines(resource, (String line) -> {
            parseLine(line, builder);
        });
    }
    
    /**
     * Parses arguments and passes them to their requisite methods
     * @param line
     * @param builder
     */
    private static void parseLine(String line, MTLBuilder builder) {
        if (line.replace(" ", "").replace("\t", "").equals(""))
            return;
        else if (line.startsWith(MTL_COMMENT))
            return;
        String[] args = null;

        if (line.contains(" ")) {
            String argAssem = line.split(" ", 2)[1];
            args = argAssem.split(" ");
        }
        
        if (line.startsWith(MTL_NEWMTL)) {
            builder.commit();
            builder.setName(args[0]);
        } else if (line.startsWith("K")) {
            float r = Float.valueOf(args[0]);
            float g = Float.valueOf(args[1]);
            float b = Float.valueOf(args[2]);
            float a = 1;
            Vector4f c = new Vector4f(r, g, b, a);
            
            switch (line.charAt(1)) {
            case 'a':
                builder.setAmbient(c);
                break;
            case 'd':
                builder.setDiffuse(c);
                break;
            case 's':
                builder.setSpecular(c);
                break;
            }
        }
    }
}
