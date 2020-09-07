package com.playhrzn.wce;

import com.playhrzn.wce.util.WCEConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Collectors;

@Mod("wce")
public class WaterControlExtreme {

    public static final String MODID = "wce";
    public static final String NAME = "Water Control Extreme";
    public static final String VERSION = "";
    private static final Logger LOGGER = LogManager.getLogger();

    public WaterControlExtreme() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Nonnull
    private String getFluidName(@Nonnull final Block block) {
        if (block instanceof IFluidBlock) {
            return ((IFluidBlock)block).getFluid().getRegistryName().getPath();
        }
        if (block == Blocks.WATER) {
            return Fluids.WATER.getRegistryName().getPath();
        }
        if (block == Blocks.LAVA) {
            return Fluids.LAVA.getRegistryName().getPath();
        }
        return "null";
    }

    private ResourceLocation getKeyFromType (DimensionType type) {
        Optional<MutableRegistry<DimensionType>> types = DynamicRegistries.func_239770_b_().func_230521_a_(Registry.DIMENSION_TYPE_KEY);
        if (types.isPresent()) {
            return types.map(registry -> registry.getKey(type)).orElse(null);
        }
        return null;
    }

    private boolean areDimensionTypesEqual(DimensionType type, String secondType) {

        return false;
    }

    private void handleFiniteFluids(final BlockEvent.CreateFluidSourceEvent event) {
        final BlockState state = event.getState();
        final String name = this.getFluidName(state.getBlock());
        if (name.equalsIgnoreCase("null")) {
            return;
        }
        final BlockPos pos = event.getPos();
        final int height = pos.getY();
        if (height >= WCEConfig.minHeight && height <= WCEConfig.maxHeight) {
            final IWorldReader world = event.getWorld();
            final Biome biome = world.getBiome(pos);
            final String biomeName = biome.getRegistryName().toString();
            for (int i1 = 0; i1 < WCEConfig.infiniteBiomes.length; ++i1) {
                final String configName = WCEConfig.infiniteBiomes[i1];
                if (biomeName.equals(configName)) {
                    return;
                }
            }
            final ResourceLocation dimId = getKeyFromType(world.func_230315_m_());
            for (int i2 = 0; i2 < WCEConfig.infiniteDimensions.length; ++i2) {
                final ResourceLocation configDim = new ResourceLocation(WCEConfig.infiniteDimensions[i2]);
                if (configDim.equals(dimId)) {
                    return;
                }
            }
            for (int i2 = 0; i2 < WCEConfig.finiteFluids.length; ++i2) {
                final String fluid = WCEConfig.finiteFluids[i2];
                if (fluid.equals(name)) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }
        }
        else {
            for (int i3 = 0; i3 < WCEConfig.finiteFluids.length; ++i3) {
                final String fluid2 = WCEConfig.finiteFluids[i3];
                if (fluid2.equals(name)) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
