package com.playhrzn.wce.json.control.fluid.datagen.defaults;

import com.google.common.collect.Lists;
import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import com.playhrzn.wce.json.control.fluid.datagen.FluidControlEntryDatagen;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

public class NetherDefaults extends DefaultFluidControlEntries{

    public NetherDefaults(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        super(controlEntryDatagenConsumer);
    }

    @Override
    public void addFluidControls(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        List<RegistryKey<Biome>> infiniteLavaBiomes = new ArrayList<>();
        for (Entry<RegistryKey<Biome>, Biome> biomeEntry : ForgeRegistries.BIOMES.getEntries()) {
            if (biomeEntry.getKey().equals(Biomes.NETHER_WASTES)  || biomeEntry.getKey().equals(Biomes.SOUL_SAND_VALLEY) ||
                biomeEntry.getKey().equals(Biomes.CRIMSON_FOREST) || biomeEntry.getKey().equals(Biomes.WARPED_FOREST)    ||
                biomeEntry.getKey().equals(Biomes.BASALT_DELTAS)
            ) {
                infiniteLavaBiomes.add(biomeEntry.getKey());
            }
        }
        DynamicRegistries.func_239770_b_().func_230521_a_(Registry.DIMENSION_TYPE_KEY).ifPresent(registry -> {
            Optional<DimensionType> nether = registry.getOptionalValue(DimensionType.THE_NETHER);
            controlEntryDatagenConsumer.accept(
                new FluidControlEntryDatagen(
                    new FluidControlEntry(new ResourceLocation("minecraft", "lava"), Fluids.LAVA, Lists.newArrayList(), infiniteLavaBiomes, Lists.newArrayList(), Lists.newArrayList(DimensionType.THE_NETHER))
                )
            );
        });
    }
}
