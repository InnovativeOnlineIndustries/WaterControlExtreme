package com.playhrzn.wce.json.control.fluid.datagen.defaults;

import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import com.playhrzn.wce.json.control.fluid.datagen.FluidControlEntryDatagen;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map.Entry;
import java.util.function.Consumer;

public class NetherDefaults extends DefaultFluidControlEntries{

    public NetherDefaults(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        super(controlEntryDatagenConsumer);
    }

    @Override
    public void addFluidControls(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        for (Entry<RegistryKey<Biome>, Biome> biomeEntry : ForgeRegistries.BIOMES.getEntries()) {
            if (biomeEntry.getKey().equals(Biomes.NETHER_WASTES)  || biomeEntry.getKey().equals(Biomes.SOUL_SAND_VALLEY) ||
                biomeEntry.getKey().equals(Biomes.CRIMSON_FOREST) || biomeEntry.getKey().equals(Biomes.WARPED_FOREST)    ||
                biomeEntry.getKey().equals(Biomes.BASALT_DELTAS)
            ) {
                controlEntryDatagenConsumer.accept(
                    new FluidControlEntryDatagen(
                        new FluidControlEntry(biomeEntry.getValue(), new Fluid[]{}, new Fluid[]{Fluids.LAVA, Fluids.FLOWING_LAVA})
                    )
                );
            }
        }
    }
}
