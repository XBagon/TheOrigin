package xbagon.theorigin;

import xbagon.theorigin.init.ModItems;
import xbagon.theorigin.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TheOrigin.MOD_ID, name = TheOrigin.NAME, version = TheOrigin.VERSION, acceptedMinecraftVersions = TheOrigin.ACCEPTED_VERSIONS)
public class TheOrigin {
	
	public static final String MOD_ID = "theorigin";
	public static final String NAME = "The Origin";
	public static final String VERSION = "0.1";
	public static final String ACCEPTED_VERSIONS = "[1.10.2]";
	
	public static final String CLIENT_PROXY_CLASS = "xbagon.theorigin.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "xbagon.theorigin.proxy.ServerProxy";

	@Instance(MOD_ID)
	public static TheOrigin instance = new TheOrigin();
	
	@SidedProxy(clientSide = TheOrigin.CLIENT_PROXY_CLASS, serverSide = TheOrigin.SERVER_PROXY_CLASS)
	public static CommonProxy proxy = new CommonProxy();
	
	public ModItems items;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		items = new ModItems();
		items.init();
		items.register();
		System.out.println("Preinitialization completed.");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
