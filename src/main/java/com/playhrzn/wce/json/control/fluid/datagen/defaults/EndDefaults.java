package com.playhrzn.wce.json.control.fluid.datagen.defaults;

import com.google.common.collect.Lists;
import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import com.playhrzn.wce.json.control.fluid.datagen.FluidControlEntryDatagen;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class EndDefaults extends DefaultFluidControlEntries {

    public EndDefaults(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        super(controlEntryDatagenConsumer);
    }

    @Override
    public void addFluidControls(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        for (Entry<RegistryKey<Biome>, Biome> biomeEntry : ForgeRegistries.BIOMES.getEntries()) {
            if (biomeEntry.getKey().equals(Biomes.THE_END)          || biomeEntry.getKey().equals(Biomes.END_BARRENS)     ||
                biomeEntry.getKey().equals(Biomes.END_HIGHLANDS)    || biomeEntry.getKey().equals(Biomes.END_MIDLANDS)    ||
                biomeEntry.getKey().equals(Biomes.SMALL_END_ISLANDS)
            ) {
                List<Fluid> fluids = new ArrayList<>(ForgeRegistries.FLUIDS.getValues());
                for (Fluid fluid : fluids) {
                    controlEntryDatagenConsumer.accept(
                        new FluidControlEntryDatagen(
                            new FluidControlEntry(fluid.getRegistryName(), fluid, Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(DimensionType.THE_END), Lists.newArrayList())
                        )
                    );
                }
                Fluid[] finiteArray = new Fluid[finiteFluids.size()];
                controlEntryDatagenConsumer.accept(
                    new FluidControlEntryDatagen(
                        new FluidControlEntry(biomeEntry.getValue(), finiteFluids.toArray(finiteArray), new Fluid[]{Fluids.LAVA, Fluids.FLOWING_LAVA})
                    )
                );
            }
        }
    }
}
