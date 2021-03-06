package xbagon.theorigin.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xbagon.theorigin.ModBlocks;
import xbagon.theorigin.ModItems;
import xbagon.theorigin.TheOrigin;
import xbagon.theorigin.entity.EntityOriginMan;
import xbagon.theorigin.entity.EntityOriginPearl;

public class ClientProxy extends CommonProxy {

    private int modEntityID = 0;

    RenderManager rm;
    RenderItem ri;

    @Override
    public void registerModels() {
        registerModel(ModItems.originPearl, 0, new ModelResourceLocation(ModItems.originPearl.getRegistryName(), "inventory"));
        registerModel(ModBlocks.originGrass, 0, new ModelResourceLocation(ModBlocks.originGrass.getRegistryName(), "inventory"));
        registerModel(ModBlocks.originStone, 0, new ModelResourceLocation(ModBlocks.originStone.getRegistryName(), "inventory"));
        registerModel(ModBlocks.originCatalyst, 0, new ModelResourceLocation(ModBlocks.originCatalyst.getRegistryName(), "inventory"));
    }

    public void registerEntities() {
        rm = Minecraft.getMinecraft().getRenderManager();
        ri = Minecraft.getMinecraft().getRenderItem();
        registerEntity(EntityOriginPearl.class, "ThrownOriginpearl", new RenderSnowball(rm, ModItems.originPearl, ri));
        registerEntity(EntityOriginMan.class, "Originman", new RenderEnderman(rm));
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

    public void registerEntity(Class entityClass, String name, Render render, int primaryColor, int secondaryColor) {
        EntityRegistry.registerModEntity(entityClass, name, ++modEntityID, TheOrigin.instance, 64, 10, true, primaryColor, secondaryColor);
        rm.entityRenderMap.put(entityClass, render);
    }

    public void registerLootTables(){
        LootTableList.register(EntityOriginMan.LOOT);
    }

    public void registerRecipes(){
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.originCatalyst),
        "AAA",
        "ABA",
        "AAA",
        'A', ModBlocks.originStone,
        'B', ModItems.originPearl
        );
        GameRegistry.addSmelting(ModBlocks.originGrass,new ItemStack(ModBlocks.originStone),1);
    }

}
