package com.playhrzn.wce;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.List;

public class Config {
    private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    public static final CategoryGeneral GENERAL = new CategoryGeneral();
    public static final CategoryControl CONTROL = new CategoryControl();

    public static ForgeConfigSpec SERVER_CONFIG;

    public static final class CategoryGeneral {
        public final ForgeConfigSpec.IntValue maxHeight;
        public final ForgeConfigSpec.IntValue minHeight;

        private CategoryGeneral() {
            SERVER_BUILDER.comment("General settings").push("general");

            maxHeight = SERVER_BUILDER.comment("Minimum y-level in which fluids can form new source blocks.")
                    .defineInRange("maxHeight", 255, 0, 255);

            minHeight = SERVER_BUILDER.comment("Maximum y-level in which fluids can form new source blocks.")
                    .defineInRange("minHeight", 0, 0, 255);

            SERVER_BUILDER.pop();
            SERVER_CONFIG = SERVER_BUILDER.build();
        }
    }

    public static final class CategoryControl {
        public final ForgeConfigSpec.ConfigValue<List<String>> finiteFluids;
        public final ForgeConfigSpec.ConfigValue<List<String>> infiniteBiomes;
        public final ForgeConfigSpec.ConfigValue<List<String>> infiniteDimensions;

        private CategoryControl() {
            SERVER_BUILDER.comment("Fluid Control").push("control");

            finiteFluids = SERVER_BUILDER.comment("Block registry names for fluids that should be made finite")
                    .define("finiteFluids", Arrays.asList(
                            "minecraft:water"
                    ));

            infiniteBiomes = SERVER_BUILDER.comment("Biomes in which these fluids should be infinite")
                    .define("infiniteBiomes", Arrays.asList(
                            "minecraft:ocean",
                            "minecraft:deep_ocean",
                            "minecraft:beach",
                            "minecraft:stone_beach",
                            "minecraft:cold_beach",
                            "minecraft:beaches",
                            "minecraft:river"
                    ));

            infiniteDimensions = SERVER_BUILDER.comment("Dimensions in which these fluids should be infinite")
                    .define("infiniteDimensions", Arrays.asList(
                            "minecraft:end"
                    ));

            SERVER_BUILDER.pop();
            SERVER_CONFIG = SERVER_BUILDER.build();
        }
    }
//    @Config.Comment({ "Block registry names for fluids that should be made finite" })
//    public static String[] finiteFluids;
//    @Config.Comment({ "Biomes in which these fluids should be infinite" })
//    public static String[] infiniteBiomes;
//    @Config.Comment({ "Dimensions in which these fluids should be infinite" })
//    public static int[] infiniteDimensions;
//    @Config.Comment({ "Minimum y-level in which fluids can form new source blocks." })
//    @Config.RangeInt(min = 0, max = 255)
//    public static int maxHeight;
//    @Config.Comment({ "Maximum y-level in which fluids can form new source blocks." })
//    @Config.RangeInt(min = 0, max = 255)
//    public static int minHeight;
//
//    static {
//        WCEConfig.finiteFluids = new String[] { "water" };
//        WCEConfig.infiniteBiomes = new String[] { "minecraft:ocean", "minecraft:deep_ocean", "minecraft:beach", "minecraft:stone_beach", "minecraft:cold_beach", "minecraft:beaches", "minecraft:river" };
//        WCEConfig.infiniteDimensions = new int[0];
//        WCEConfig.maxHeight = 255;
//        WCEConfig.minHeight = 0;
//    }
}
