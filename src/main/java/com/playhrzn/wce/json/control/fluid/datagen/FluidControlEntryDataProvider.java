package com.playhrzn.wce.json.control.fluid.datagen;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playhrzn.wce.WaterControlExtreme;
import com.playhrzn.wce.json.JsonHelper;
import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class FluidControlEntryDataProvider implements IDataProvider {

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private final DataGenerator generator;

    public FluidControlEntryDataProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        Path pathIn = this.generator.getOutputFolder();
        Set<ResourceLocation> set = Sets.newHashSet();
        Consumer<FluidControlEntryDatagen> consumer = (entry) -> {
            if (!set.add(entry.getEntry().getId())) {
                throw new IllegalStateException("Duplicate Fluid Control Entry ID " + entry.getEntry().getId());
            } else {
                Path path1 = pathIn.resolve("data/" + entry.getEntry().getId().getNamespace() + "/entries/" + entry.getEntry().getId().getPath() + ".json");
                try {
                    IDataProvider.save(GSON, cache, JsonHelper.serializeFluidControlEntry(entry.getEntry(), entry.getConditions()), path1);
                } catch (IOException ioException) {
                    WaterControlExtreme.LOGGER.error("Couldn't save miner entry {}", path1, ioException);
                }
            }
        };

    }

    protected void addFluidControlEntries(Consumer<FluidControlEntryDatagen> consumer) {}

    @Override
    public String getName() {
        return "WCE: Fluid Control";
    }
}
