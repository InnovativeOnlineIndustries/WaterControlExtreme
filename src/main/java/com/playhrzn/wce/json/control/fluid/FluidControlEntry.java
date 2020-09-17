package com.playhrzn.wce.json.control.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class FluidControlEntry {

    private ResourceLocation id;
    private final Fluid fluid;
    private List<RegistryKey<Biome>> finiteBiomes;
    private List<RegistryKey<Biome>> infiniteBiomes;
    private List<RegistryKey<DimensionType>> finiteDimensionsOverride;
    private List<RegistryKey<DimensionType>> infiniteDimensionsOverride;

    public FluidControlEntry (ResourceLocation id, Fluid fluid) {
        this.id = id;
        this.fluid = fluid;
    }

    public FluidControlEntry (ResourceLocation id, Fluid fluid, List<RegistryKey<Biome>> finiteBiomes) {
        this.id = id;
        this.fluid = fluid;
        this.finiteBiomes = finiteBiomes;
    }

    public FluidControlEntry (ResourceLocation id, Fluid fluid, List<RegistryKey<Biome>> finiteBiomes, List<RegistryKey<Biome>> infiniteBiomes) {
        this.id = id;
        this.fluid = fluid;
        this.finiteBiomes = finiteBiomes;
        this.infiniteBiomes = infiniteBiomes;
    }

    public FluidControlEntry (ResourceLocation id, Fluid fluid, List<RegistryKey<Biome>> finiteBiomes, List<RegistryKey<Biome>> infiniteBiomes, List<RegistryKey<DimensionType>> finiteDimensionsOverride) {
        this.id = id;
        this.fluid = fluid;
        this.finiteBiomes = finiteBiomes;
        this.infiniteBiomes = infiniteBiomes;
        this.finiteDimensionsOverride = finiteDimensionsOverride;
    }

    public FluidControlEntry (ResourceLocation id, Fluid fluid, List<RegistryKey<Biome>> finiteBiomes, List<RegistryKey<Biome>> infiniteBiomes, List<RegistryKey<DimensionType>> finiteDimensionsOverride, List<RegistryKey<DimensionType>> infiniteDimensionsOverride) {
        this.id = id;
        this.fluid = fluid;
        this.finiteBiomes = finiteBiomes;
        this.infiniteBiomes = infiniteBiomes;
        this.finiteDimensionsOverride = finiteDimensionsOverride;
        this.infiniteDimensionsOverride = infiniteDimensionsOverride;
    }

    public ResourceLocation getId() {
        return id == null ? fluid.getRegistryName() : id;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public List<RegistryKey<Biome>> getFiniteBiomes() {
        return finiteBiomes;
    }

    public List<RegistryKey<Biome>> getInfiniteBiomes() {
        return infiniteBiomes;
    }

    public List<RegistryKey<DimensionType>> getFiniteDimensionsOverride() {
        return finiteDimensionsOverride;
    }

    public List<RegistryKey<DimensionType>> getInfiniteDimensionsOverride() {
        return infiniteDimensionsOverride;
    }
}
