package xbagon.theorigin.proxy;

import entity.EntityOriginPearl;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import xbagon.theorigin.ModBlocks;
import xbagon.theorigin.ModItems;
import xbagon.theorigin.TheOrigin;

public class ClientProxy extends CommonProxy {

    private int modEntityID = 0;

    RenderManager rm;
    RenderItem ri;

    @Override
    public void registerModels() {
        registerModel(ModItems.originPearl, 0, new ModelResourceLocation(ModItems.originPearl.getRegistryName(), "inventory"));
        registerModel(ModBlocks.originGrass, 0, new ModelResourceLocation(ModBlocks.originGrass.getRegistryName(), "inventory"));
    }

    public void registerEntities() {
        rm = Minecraft.getMinecraft().getRenderManager();
        ri = Minecraft.getMinecraft().getRenderItem();
        registerEntity(EntityOriginPearl.class, "ThrownOriginalpearl", new RenderSnowball(rm, ModItems.originPearl, ri));
    }

    //----------------------------------------------------------------------------------------------------------------


    private void registerModel(Object obj, int meta, ModelResourceLocation loc) {
        Item item = null;
        if (obj instanceof Item) {
            item = (Item) obj;
        } else if (obj instanceof Block) {
            item = Item.getItemFromBlock((Block) obj);
        } else {
            throw new IllegalArgumentException();
        }
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
    }

    public void registerEntity(Class entityClass, String name, Render render) {
        EntityRegistry.registerModEntity(entityClass, name, ++modEntityID, TheOrigin.instance, 64, 10, true);
        rm.entityRenderMap.put(entityClass, render);
    }

}
