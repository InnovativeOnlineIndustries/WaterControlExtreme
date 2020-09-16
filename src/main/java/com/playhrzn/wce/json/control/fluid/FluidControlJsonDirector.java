package com.playhrzn.wce.json.control.fluid;

import com.hrznstudio.titanium.json.jsondirector.IJsonDirector;
import com.playhrzn.wce.WaterControlExtreme;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidControlJsonDirector implements IJsonDirector<FluidControlEntry> {

    private final Map<Biome, List<FluidControlEntry>> entries;

    public FluidControlJsonDirector() {
        this.entries = new HashMap<>();
    }

    @Override
    public void put(ResourceLocation resourceLocation, FluidControlEntry entry) {
        if (entry != null) {
            entries.computeIfPresent(entry.getBiome(), (biome, list) -> {
                if (list.stream().anyMatch(entry1 -> {
                    for (Fluid fluid : entry.getFiniteFluids()) {
                        for (Fluid fluid1 : entry1.getInfiniteFluids()) {
                            if (fluid.isEquivalentTo(fluid1)) {
                                WaterControlExtreme.LOGGER.error("Error: Error occurred reading FluidControlEntry with ID: {}", resourceLocation);
                                WaterControlExtreme.LOGGER.error("Error: Found Conflicting Entry between finite and infinite for Biome {}, Fluid was {}", entry.getBiome(), fluid.getRegistryName().toString());
                                return false;
                            }
                        }
                    }

                    for (Fluid fluid : entry.getInfiniteFluids()) {
                        for (Fluid fluid1 : entry.getFiniteFluids()) {
                            if (fluid.isEquivalentTo(fluid1)) {
                                WaterControlExtreme.LOGGER.error("Error: Error occurred reading FluidControlEntry with ID: {}", resourceLocation);
                                WaterControlExtreme.LOGGER.error("Error: Found Conflicting Entry between infinite and finite for Biome {}, Fluid was {}", entry.getBiome(), fluid.getRegistryName().toString());
                                return false;
                            }
                        }
                    }

                    for (Fluid fluid : entry.getFiniteFluids()) {
                        for (Fluid fluid1 : entry1.getFiniteFluids()) {
                            if (fluid.isEquivalentTo(fluid1)) {
                                WaterControlExtreme.LOGGER.error("Error: Error occurred reading FluidControlEntry with ID: {}", resourceLocation);
                                WaterControlExtreme.LOGGER.error("Error: Found Duplicate Finite Entry for Biome {}, Fluid was {}", entry.getBiome(), fluid.getRegistryName().toString());
                                return false;
                            }
                        }
                    }

                    for (Fluid fluid : entry.getInfiniteFluids()) {
                        for (Fluid fluid1 : entry1.getInfiniteFluids()) {
                            if (fluid.isEquivalentTo(fluid1)) {
                                WaterControlExtreme.LOGGER.error("Error: Error occurred reading FluidControlEntry with ID: {}", resourceLocation);
                                WaterControlExtreme.LOGGER.error("Error: Found Duplicate Infinite Entry for Biome {}, Fluid was {}", entry.getBiome(), fluid.getRegistryName().toString());
                                return false;
                            }
                        }
                    }

                    return true;
                })) list.add(entry);
                return list;
            });
        }
    }

    @Override
    public void clear() {
        entries.clear();
    }

    public Map<Biome, List<FluidControlEntry>> getEntries() {
        return entries;
    }
}
