package com.playhrzn.wce.json.control.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class FluidControlEntry {

    private ResourceLocation id;
    private final Biome biome;
    private Fluid[] finiteFluids;
    private Fluid[] infiniteFluids;

    public FluidControlEntry (Biome biome, Fluid[] finiteFluids, Fluid[] infiniteFluids) {
        this.biome = biome;
        this.finiteFluids = finiteFluids;
        this.infiniteFluids = infiniteFluids;
    }

    public FluidControlEntry (ResourceLocation id, Biome biome, Fluid[] finiteFluids, Fluid[] infiniteFluids) {
        this.biome = biome;
        this.finiteFluids = finiteFluids;
        this.infiniteFluids = infiniteFluids;
    }

    public ResourceLocation getId() {
        return id == null ? biome.getRegistryName() : id;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setFiniteFluids(Fluid[] finiteFluids) {
        this.finiteFluids = finiteFluids;
    }

    public Fluid[] getFiniteFluids() {
        return finiteFluids;
    }

    public void setInfiniteFluids(Fluid[] infiniteFluids) {
        this.infiniteFluids = infiniteFluids;
    }

    public Fluid[] getInfiniteFluids() {
        return infiniteFluids;
    }
}
