package jgloom.io.models.obj;

import java.io.IOException;

import jgloom.io.models.Model;
import jgloom.io.models.mtl.MTLMaterial;
import jgloom.io.models.mtl.MTLMaterialLoader;
import jgloom.io.models.mtl.MTLMaterials;
import jgloom.io.resources.ClasspathResource;
import jgloom.io.resources.Resource;
import jgloom.io.resources.ResourceReader;

// FIXME Add support for multiple forms of split data lines
// FIXME Add support for material files
// FIXME Add support for texture coordinates
/**
 * Loads and parses an OBJ file into vertex, normal, and index data
 */
public class OBJModelLoader {
    public static final String OBJ_COMMENT = "#";
    public static final String OBJ_VERTEX = "v ";
    public static final String OBJ_NORMAL = "vn ";
    public static final String OBJ_INDEX = "f ";
    public static final String OBJ_MTLLIB = "mtllib ";
    public static final String OBJ_USEMTL = "usemtl ";
    
    /**
     * Collection of materials loaded while loading a model
     */
    private static MTLMaterials materials;
    
    /**
     * Current material as dictated by the usemtl keyword
     */
    @SuppressWarnings("unused")
    private static MTLMaterial material;

    /**
     * Loads and parses the given OBJ resource into a usable model object
     * @param resource Resource containing the OBJ data
     * @return Parsed OBJ model with loaded data
     * @throws IOException In case model loading fails
     */
    public static Model loadModel(Resource resource) throws IOException {
        materials = null; material = null;
        OBJBuilder builder = new OBJBuilder();
        startReading(resource, builder);
        return new OBJModel(builder, materials);
    }

    /**
     * Begins reading the OBJ file line by line in order to parse
     * @param resource
     * @param builder
     * @throws IOException
     */
    private static void startReading(Resource resource, OBJBuilder builder) throws IOException {
        ResourceReader.readIndividualLines(resource, (String line) -> {
            try {
                parseLine(line, builder);
            } catch (Exception e) {}
        });
    }

    /**
     * Parses the given line using the requisite methods
     * @param line
     * @param builder
     * @throws IOException
     */
    private static void parseLine(String line, OBJBuilder builder) throws IOException {
        if (line.replace(" ", "").replace("\t", "").equals(""))
            return;
        else if (line.startsWith(OBJ_COMMENT))
            return;
        String[] args = null;

        if (line.contains(" ")) {
            String argAssem = line.split(" ", 2)[1];
            args = argAssem.split(" ");
        }

        if (line.startsWith(OBJ_VERTEX))
            parseVertexLine(args, builder);
        else if (line.startsWith(OBJ_NORMAL))
            parseNormalLine(args, builder);
        else if (line.startsWith(OBJ_INDEX))
            parseIndexLine(args, builder);
        else if (line.startsWith(OBJ_MTLLIB))
            parseMTLLibLine(args, builder);
        else if (line.startsWith(OBJ_USEMTL))
            parseUseMTLLine(args, builder);
    }

    /**
     * Parses a line starting with 'v'
     * @param args
     * @param builder
     */
    private static void parseVertexLine(String[] args, OBJBuilder builder) {
        float x = Float.valueOf(args[0]);
        float y = Float.valueOf(args[1]);
        float z = Float.valueOf(args[2]);
        builder.appendVertex(x, y, z);
    }

    /**
     * Parses a line starting with 'vn'
     * @param args
     * @param builder
     */
    private static void parseNormalLine(String[] args, OBJBuilder builder) {
        float x = Float.valueOf(args[0]);
        float y = Float.valueOf(args[1]);
        float z = Float.valueOf(args[2]);
        builder.appendNormal(x, y, z);
    }

    /**
     * Parses a line starting with 'f' (face)
     * @param args
     * @param builder
     */
    private static void parseIndexLine(String[] args, OBJBuilder builder) {
        String[] i1 = args[0].split("//");
        String[] i2 = args[1].split("//");
        String[] i3 = args[2].split("//");
        int v1 = Integer.valueOf(i1[0]) - 1;
        int n1 = Integer.valueOf(i1[1]) - 1;
        int v2 = Integer.valueOf(i2[0]) - 1;
        int n2 = Integer.valueOf(i2[1]) - 1;
        int v3 = Integer.valueOf(i3[0]) - 1;
        int n3 = Integer.valueOf(i3[1]) - 1;
        builder.appendIndex(v1, n1);
        builder.appendIndex(v2, n2);
        builder.appendIndex(v3, n3);
    }
    
    /**
     * Parses a line starting with 'mtllib'
     * @param args
     * @param builder
     * @throws IOException
     */
    private static void parseMTLLibLine(String[] args, OBJBuilder builder) throws IOException {
        String fileName = args[0];
        Resource materialFile = ClasspathResource.createClasspathResource(fileName);
        materials = MTLMaterialLoader.loadMaterials(materialFile);
    }
    
    /**
     * Parses a line starting with 'usemtl'
     * @param args
     * @param builder
     */
    private static void parseUseMTLLine(String[] args, OBJBuilder builder) {
        if (materials != null) {
            material = materials.getMaterial(args[0]);
        }
    }
}
