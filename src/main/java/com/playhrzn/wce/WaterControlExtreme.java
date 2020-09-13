package com.playhrzn.wce;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Optional;

@Mod(WaterControlExtreme.MODID)
public class WaterControlExtreme {

    public static final String MODID = "wce";

    // Likely no longer needed
    public static final String NAME = "Water Control Extreme";
    private static final Logger LOGGER = LogManager.getLogger();

    public WaterControlExtreme() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private ResourceLocation getFluidName(@Nonnull final Block block) {
        if (block instanceof IFluidBlock) {
            return ((IFluidBlock) block).getFluid().getRegistryName();
        }
        if (block == Blocks.WATER) {
            return Fluids.WATER.getRegistryName();
        }
        if (block == Blocks.LAVA) {
            return Fluids.LAVA.getRegistryName();
        }

        return null;
    }

    private ResourceLocation getKeyFromType(DimensionType type) {
        Optional<MutableRegistry<DimensionType>> types = DynamicRegistries.func_239770_b_().func_230521_a_(Registry.DIMENSION_TYPE_KEY);
        if (types.isPresent()) {
            return types.map(registry -> registry.getKey(type)).orElse(null);
        }
        return null;
    }

    private boolean areDimensionTypesEqual(DimensionType type, String secondType) {

        return false;
    }

    @SubscribeEvent
    public void handleFiniteFluids(final BlockEvent.CreateFluidSourceEvent event) {
        final BlockState state = event.getState();
        final ResourceLocation name = this.getFluidName(state.getBlock());
        if (name == null) {
            return;
        }

        final BlockPos pos = event.getPos();
        final int height = pos.getY();
        if (height >= Config.GENERAL.minHeight.get() && height <= Config.GENERAL.maxHeight.get()) {
            final IWorldReader eventWorld = event.getWorld();
            if (!(eventWorld instanceof ServerWorld)) {
                return;
            }

            final ServerWorld world = (ServerWorld) eventWorld;
            ResourceLocation biome = world.func_241828_r().func_243612_b(Registry.BIOME_KEY).getKey(world.getBiome(pos));
            if (biome == null) {
                return;
            }

            for (int i1 = 0; i1 < Config.CONTROL.infiniteBiomes.get().size(); ++i1) {
                if (biome.equals(new ResourceLocation(Config.CONTROL.infiniteBiomes.get().get(i1)))) {
                    return;
                }
            }

            final ResourceLocation dimId = getKeyFromType(eventWorld.func_230315_m_());
            for (int i2 = 0; i2 < Config.CONTROL.infiniteDimensions.get().size(); ++i2) {
                final ResourceLocation configDim = new ResourceLocation(Config.CONTROL.infiniteDimensions.get().get(i2));
                if (configDim.equals(dimId)) {
                    return;
                }
            }
            for (int i2 = 0; i2 < Config.CONTROL.finiteFluids.get().size(); ++i2) {
                final ResourceLocation fluid = new ResourceLocation(Config.CONTROL.finiteFluids.get().get(i2));
                if (fluid.equals(name)) {
                    event.setResult(Event.Result.DENY);
                    return;
                }
            }
        } else {
            for (int i3 = 0; i3 < Config.CONTROL.finiteFluids.get().size(); ++i3) {
                final ResourceLocation fluid2 = new ResourceLocation(Config.CONTROL.finiteFluids.get().get(i3));
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
