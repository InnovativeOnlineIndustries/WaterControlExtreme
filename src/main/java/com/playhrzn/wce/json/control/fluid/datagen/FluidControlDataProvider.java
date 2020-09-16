package com.playhrzn.wce.json.control.fluid.datagen;

import com.playhrzn.wce.json.control.fluid.datagen.defaults.EndDefaults;
import com.playhrzn.wce.json.control.fluid.datagen.defaults.NetherDefaults;
import com.playhrzn.wce.json.control.fluid.datagen.defaults.OverworldDefaults;
import net.minecraft.data.DataGenerator;

import java.util.function.Consumer;

public class FluidControlDataProvider extends FluidControlEntryDataProvider {

    public FluidControlDataProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addFluidControlEntries(Consumer<FluidControlEntryDatagen> consumer) {
        new OverworldDefaults(consumer);
        new NetherDefaults(consumer);
        new EndDefaults(consumer);
    }

    @Override
    public String getName() {
        return "WCE: Defaults";
    }
}
