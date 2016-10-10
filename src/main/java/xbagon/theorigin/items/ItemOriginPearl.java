package xbagon.theorigin.items;

import xbagon.theorigin.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemOriginPearl extends Item {
	
	public ItemOriginPearl() {
		this.setCreativeTab(CreativeTabs.MISC);
		setUnlocalizedName(Reference.TheOriginItems.ORIGINPEARL.getUnlocalizedName());
		setRegistryName(Reference.TheOriginItems.ORIGINPEARL.getRegistryName());
	}
}
