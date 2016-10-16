package xbagon.theorigin.proxy;

import net.minecraftforge.common.MinecraftForge;
import xbagon.theorigin.blocks.BlockOriginGrass;

public class CommonProxy {


    public void registerModels() {

    }

    public void registerEntities() {

    }

    public void registerHandlers(){
        MinecraftForge.EVENT_BUS.register(new BlockOriginGrass());
    }

}
