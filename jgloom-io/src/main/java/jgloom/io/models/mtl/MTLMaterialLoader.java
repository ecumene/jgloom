package jgloom.io.models.mtl;

import java.io.IOException;

import org.joml.Vector4f;

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
        builder.commit();
        return new MTLMaterials(builder);
    }
    
    private static void startReading(Resource resource, MTLBuilder builder) throws IOException {
        ResourceReader.readIndividualLines(resource, (String line) -> {
            parseLine(line, builder);
        });
    }
    
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
