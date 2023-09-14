package de.sakurajin.evenbetterarcheology.structures;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.sakurajin.sakuralib.util.DatagenModContainer;
import de.sakurajin.sakuralib.json.worldgen.processor.JProcessor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class ModStructureDataBuilder {

    protected final String name;

    protected final String                type;
    protected       Identifier            structureBiomeID           = null;
    protected       String                terrain_adaptation         = "beard_thin";
    protected       int                   start_height               = 0;
    protected       String                project_start_to_heightmap = "WORLD_SURFACE_WG";
    protected       String                structure_step             = "surface_structures";
    protected       int                   structure_size             = 1;
    protected       int                   max_distance_from_center   = 80;
    protected       String                placement_type             = "minecraft:random_spread";
    protected       int                   placement_spacing          = 96;
    protected       int                   placement_separation       = 64;
    protected       int                   structure_weight           = 1;
    protected       int                   salt                       = 0;
    protected       int                   exclusion_radius           = 16;
    protected       String                exculision_structure       = null;
    protected       String                structure_pool_fallback    = "minecraft:empty";
    protected       int                   structure_pool_weight      = 1;
    protected       String                structure_projection       = "rigid";
    protected       ArrayList<JProcessor> processors                 = new ArrayList<>();

    protected ModStructureDataBuilder(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static ModStructureDataBuilder create(@NotNull String name, @NotNull String type) {
        return new ModStructureDataBuilder(name, type);
    }

    public ModStructureDataBuilder structureWeight(int structure_weight) {
        this.structure_weight = structure_weight;
        return this;
    }

    public ModStructureDataBuilder salt(int salt) {
        this.salt = salt;
        return this;
    }

    public ModStructureDataBuilder placementType(String placement_type) {
        this.placement_type = placement_type;
        return this;
    }

    public ModStructureDataBuilder placementSpacing(int placement_spacing) {
        this.placement_spacing = placement_spacing;
        return this;
    }

    public ModStructureDataBuilder placementSeparation(int placement_separation) {
        this.placement_separation = placement_separation;
        return this;
    }

    public ModStructureDataBuilder exclusionRadius(int exclusion_radius) {
        this.exclusion_radius = exclusion_radius;
        return this;
    }

    public ModStructureDataBuilder exculisionStructure(String exculision_structure) {
        this.exculision_structure = exculision_structure;
        return this;
    }

    public ModStructureDataBuilder structurePoolFallback(String structure_pool_fallback) {
        this.structure_pool_fallback = structure_pool_fallback;
        return this;
    }

    public ModStructureDataBuilder structurePoolWeight(int structure_pool_weight) {
        this.structure_pool_weight = structure_pool_weight;
        return this;
    }

    public ModStructureDataBuilder structureProjection(String structure_projection) {
        this.structure_projection = structure_projection;
        return this;
    }

    public ModStructureDataBuilder structureStep(String structure_step) {
        this.structure_step = structure_step;
        return this;
    }

    public ModStructureDataBuilder structureBiomeID(Identifier structureBiomeID) {
        this.structureBiomeID = structureBiomeID;
        return this;
    }

    public ModStructureDataBuilder addProcessor(JProcessor processor) {
        return addProcessors(processor);
    }

    public ModStructureDataBuilder addProcessors(JProcessor... processors) {
        this.processors.addAll(Arrays.asList(processors));
        return this;
    }

    public ModStructureDataBuilder terrainAdaptation(String terrain_adaptation) {
        this.terrain_adaptation = terrain_adaptation;
        return this;
    }

    public ModStructureDataBuilder startHeight(int start_height) {
        this.start_height = start_height;
        return this;
    }

    public ModStructureDataBuilder projectStartToHeightmap(String project_start_to_heightmap) {
        this.project_start_to_heightmap = project_start_to_heightmap;
        return this;
    }

    public ModStructureDataBuilder structureSize(int structure_size) {
        this.structure_size = structure_size;
        return this;
    }

    public ModStructureDataBuilder maxDistanceFromCenter(int max_distance_from_center) {
        this.max_distance_from_center = max_distance_from_center;
        return this;
    }


    //create the structure file
    public ModStructureDataBuilder buildStructure(DatagenModContainer dataContainer) {
        if (name == null || type == null) throw new IllegalStateException("Name or type is somehow null");

        String     nameId    = dataContainer.getStringID(name);
        JsonObject structure = new JsonObject();
        if (structureBiomeID == null) {
            structureBiomeID = dataContainer.getSimpleID(name, "#has_structure");
        }

        // set the start pool to the structure file
        structure.addProperty("start_pool", nameId);
        structure.addProperty("type", dataContainer.getStringID(type));
        structure.addProperty("biomes", structureBiomeID.toString());
        structure.addProperty("terrain_adaptation", terrain_adaptation);
        var start_height_json = new JsonObject();
        start_height_json.addProperty("absolute", start_height);
        structure.add("start_height", start_height_json);

        structure.addProperty("project_start_to_heightmap", project_start_to_heightmap);
        structure.addProperty("size", structure_size);
        structure.addProperty("max_distance_from_center", max_distance_from_center);
        structure.addProperty("step", structure_step);

        structure.add("spawn_overrides", new JsonObject());

        // actually generate the data files and add them to the resource pack
        dataContainer.RESOURCE_PACK.addData(
            dataContainer.getSimpleID(name + ".json", "worldgen/structure"),
            structure.toString().getBytes()
        );

        return this;
    }

    // generate the json for the structure set
    public ModStructureDataBuilder buildStructureSet(DatagenModContainer dataContainer) {
        if (name == null || type == null) throw new IllegalStateException("Name or type is somehow null");
        if (exclusion_radius < 1 || exclusion_radius > 16)
            throw new IllegalStateException("Exclusion radius is not between 1 and 16");

        String nameId = dataContainer.getStringID(name);
        if (salt <= 0) {
            salt = nameId.hashCode();
        }

        JsonObject structure_set = new JsonObject();

        // create the structure list
        structure_set.add("structures", new JsonArray());
        var structureObj = new JsonObject();
        structureObj.addProperty("structure", nameId);
        structureObj.addProperty("weight", structure_weight);

        // create the placement object
        var placement = new JsonObject();
        placement.addProperty("type", placement_type);
        placement.addProperty("salt", salt);
        placement.addProperty("spacing", placement_spacing);
        placement.addProperty("separation", placement_separation);

        var exculsion = new JsonObject();
        exculsion.addProperty("chunk_count", exclusion_radius);
        exculsion.addProperty("other_set", exculision_structure);
        placement.add("exclusion_zone", exculsion);

        // add the structure and placement to the structure set
        structure_set.getAsJsonArray("structures").add(structureObj);
        structure_set.add("placement", placement);

        // actually generate the data files and add them to the resource pack
        dataContainer.RESOURCE_PACK.addData(
            dataContainer.getSimpleID(name + ".json", "worldgen/structure_set"),
            structure_set.toString().getBytes()
        );

        return this;
    }

    // generate the json for the structure template pool
    public ModStructureDataBuilder buildStructurePool(DatagenModContainer dataContainer) {
        if (name == null || type == null) throw new IllegalStateException("Name or type is somehow null");

        JsonObject structure_pool = new JsonObject();

        //create the element object
        var structureElement = new JsonObject();
        structureElement.addProperty("location", dataContainer.getStringID(name));
        structureElement.addProperty("projection", structure_projection);
        structureElement.addProperty("element_type", "minecraft:single_pool_element");

        JProcessor.addToResourcePack(dataContainer, name + "_processor", processors.toArray(JProcessor[]::new));
        structureElement.addProperty("processors", dataContainer.getStringID(name + "_processor"));

        //create a new object containing the weight for the element
        var element = new JsonObject();
        element.addProperty("weight", structure_pool_weight);
        element.add("element", structureElement);

        //add the element to the elements array
        var elements = new JsonArray();
        elements.add(element);

        //add the fallback and elements to the structure pool
        structure_pool.addProperty("fallback", structure_pool_fallback);
        structure_pool.add("elements", elements);

        // actually generate the data files and add them to the resource pack
        dataContainer.RESOURCE_PACK.addData(
            dataContainer.getSimpleID(name + ".json", "worldgen/template_pool"),
            structure_pool.toString().getBytes()
        );

        return this;
    }

}
