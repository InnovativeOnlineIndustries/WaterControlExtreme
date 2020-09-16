package com.playhrzn.wce.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hrznstudio.titanium.recipe.serializer.JSONSerializableDataHandler;
import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static JsonObject serializeFluidControlEntry(FluidControlEntry entry, List<ICondition> conditions) {
        JsonObject object = new JsonObject();
        JsonArray jsonConditions = new JsonArray();
        JsonArray jsonFiniteFluids = new JsonArray();
        JsonArray jsonInfiniteFluids = new JsonArray();

        for (ICondition condition : conditions) {
            jsonConditions.add(CraftingHelper.serialize(condition));
        }

        if (jsonConditions.size() > 0) {
            object.add("conditions", jsonConditions);
        }

        object.addProperty("biome", entry.getBiome().getRegistryName().toString());

        for (Fluid fluid : entry.getFiniteFluids()) {
            jsonFiniteFluids.add(fluid.getRegistryName().toString());
        }

        if (jsonFiniteFluids.size() > 0) {
            object.add("finiteFluids", jsonFiniteFluids);
        }

        for (Fluid fluid : entry.getInfiniteFluids()) {
            jsonInfiniteFluids.add(fluid.getRegistryName().toString());
        }

        if (jsonInfiniteFluids.size() > 0) {
            object.add("infiniteFluids", jsonInfiniteFluids);
        }
        return object;
    }

    public static FluidControlEntry deserializeFluidControlEntry (ResourceLocation resourceLocation, JsonObject object) {
        Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(object.get("biome").getAsString()));
        List<Fluid> finiteFluids = new ArrayList<>();
        List<Fluid> infiniteFluids = new ArrayList<>();
        for (JsonElement element : object.get("finiteFluids").getAsJsonArray()) {
            String fluid = element.getAsString();
            finiteFluids.add(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluid)));
        }
        for (JsonElement element : object.get("infiniteFluids").getAsJsonArray()) {
            String fluid = element.getAsString();
            infiniteFluids.add(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluid)));
        }
        Fluid[] finiteArray = new Fluid[finiteFluids.size()];
        Fluid[] infiniteArray = new Fluid[infiniteFluids.size()];
        return new FluidControlEntry(resourceLocation, biome, finiteFluids.toArray(finiteArray), infiniteFluids.toArray(infiniteArray));
    }

}
