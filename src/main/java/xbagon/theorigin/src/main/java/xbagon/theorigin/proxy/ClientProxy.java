package xbagon.theorigin.proxy;

import xbagon.theorigin.ModItems;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerModels(){
		registerModel(ModItems.originPearl, 0, new ModelResourceLocation(ModItems.originPearl.getRegistryName(),"inventory"));
	}
	
	private void registerModel(Object obj, int meta, ModelResourceLocation loc){
		Item item = null;
		if(obj instanceof Item){
			item = (Item) obj;
		} else if(obj instanceof Block){
			Item.getItemFromBlock((Block) obj);
		} else {
			throw new IllegalArgumentException();
		}
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
	}

}
