package com.playhrzn.wce.json.control.fluid.datagen.defaults;

import com.playhrzn.wce.json.control.fluid.datagen.FluidControlEntryDatagen;

import java.util.function.Consumer;

public abstract class DefaultFluidControlEntries {

    public DefaultFluidControlEntries(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer) {
        addFluidControls(controlEntryDatagenConsumer);
    }

    public abstract void addFluidControls(Consumer<FluidControlEntryDatagen> controlEntryDatagenConsumer);
}
