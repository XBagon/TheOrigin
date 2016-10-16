package xbagon.theorigin;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xbagon.theorigin.proxy.CommonProxy;
import xbagon.theorigin.worldgen.WorldGen;

@Mod(modid = TheOrigin.MOD_ID, name = TheOrigin.NAME, version = TheOrigin.VERSION, acceptedMinecraftVersions = TheOrigin.ACCEPTED_VERSIONS)
public class TheOrigin {

    public static final String MOD_ID = "theorigin";
    public static final String NAME = "The Origin";
    public static final String VERSION = "0.1";
    public static final String ACCEPTED_VERSIONS = "[1.10.2]";
    @Instance(MOD_ID)
    public static TheOrigin instance = new TheOrigin();

    @SidedProxy(clientSide = "xbagon.theorigin.proxy.ClientProxy", serverSide = "xbagon.theorigin.proxy.CommonProxy")
    public static CommonProxy proxy = new CommonProxy();

    public ModItems items;
    public ModBlocks blocks;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        items = new ModItems();
        items.init();
        items.register();
        blocks = new ModBlocks();
        blocks.init();
        blocks.register();
        System.out.println("Preinitialization completed.");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerModels();
        proxy.registerEntities();
        GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.registerHandlers();
    }
}
