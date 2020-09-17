package com.playhrzn.wce.json.control.fluid.datagen.defaults;

import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import com.playhrzn.wce.json.control.fluid.datagen.FluidControlEntryDatagen;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class OverworldDefaults extends DefaultFluidControlEntries {

    public OverworldDefaults(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        super(controlEntryDatagenConsumer);
    }

    @Override
    public void addFluidControls(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        List<RegistryKey<Biome>> blacklistedBiomesWater = new ArrayList<>();
        for (Entry<RegistryKey<Biome>, Biome> biomeEntry : ForgeRegistries.BIOMES.getEntries()) {
            if (!biomeEntry.getKey().equals(Biomes.OCEAN)           || !biomeEntry.getKey().equals(Biomes.DEEP_OCEAN)           ||
                !biomeEntry.getKey().equals(Biomes.LUKEWARM_OCEAN)  || !biomeEntry.getKey().equals(Biomes.DEEP_LUKEWARM_OCEAN)  ||
                !biomeEntry.getKey().equals(Biomes.WARM_OCEAN)      || !biomeEntry.getKey().equals(Biomes.DEEP_WARM_OCEAN)      ||
                !biomeEntry.getKey().equals(Biomes.COLD_OCEAN)      || !biomeEntry.getKey().equals(Biomes.DEEP_COLD_OCEAN)      ||
                !biomeEntry.getKey().equals(Biomes.FROZEN_OCEAN)    || !biomeEntry.getKey().equals(Biomes.DEEP_FROZEN_OCEAN)    ||
                !biomeEntry.getKey().equals(Biomes.RIVER)           || !biomeEntry.getKey().equals(Biomes.FROZEN_RIVER)
            ) {
                blacklistedBiomesWater.add(biomeEntry.getKey());
            }
        }
        controlEntryDatagenConsumer.accept(
            new FluidControlEntryDatagen(
                new FluidControlEntry(new ResourceLocation("minecraft", "water"), Fluids.WATER, blacklistedBiomesWater)
            )
        );
    }
}