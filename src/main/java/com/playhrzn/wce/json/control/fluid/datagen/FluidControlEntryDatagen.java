package com.playhrzn.wce.json.control.fluid.datagen;

import com.playhrzn.wce.json.control.fluid.FluidControlEntry;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FluidControlEntryDatagen {
    private final FluidControlEntry entry;
    private final List<ICondition> conditions;

    public FluidControlEntryDatagen(FluidControlEntry entry, ICondition... conditions) {
        this.entry = entry;
        this.conditions = new ArrayList<>();
        this.conditions.addAll(Arrays.asList(conditions));
    }

    public FluidControlEntry getEntry() {
        return entry;
    }

    public List<ICondition> getConditions() {
        return conditions;
    }
}
