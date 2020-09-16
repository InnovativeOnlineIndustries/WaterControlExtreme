package com.playhrzn.wce.json.control.fluid;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hrznstudio.titanium.json.IJsonProvider;
import com.playhrzn.wce.json.JsonHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

public class FluidControlJsonProvider implements IJsonProvider<FluidControlEntry> {

    @Override
    public FluidControlEntry provide(ResourceLocation resourceLocation, JsonObject jsonObject) throws JsonParseException {
        if (CraftingHelper.processConditions(jsonObject, "conditions")) {
            return JsonHelper.deserializeFluidControlEntry(resourceLocation, jsonObject);
        }
        return null;
    }
}
