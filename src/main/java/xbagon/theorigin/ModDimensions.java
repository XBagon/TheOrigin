package xbagon.theorigin;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by XBagon on 04.11.2016.
 */
public class ModDimensions {
    public static DimensionType originDimensionType;

    public static void init() {
        registerDimensionTypes();
        registerDimensions();
    }

    private static void registerDimensionTypes() {
        originDimensionType = DimensionType.register(TheOrigin.MOD_ID, "_theOrigin", -10, OriginWorldProvider.class, false);
    }

    private static void registerDimensions() {
        DimensionManager.registerDimension(-10, originDimensionType);
    }
}

