package xbagon.theorigin.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by XBagon on 01.11.2016.
 */
public class BlockOriginCatalyst extends Block {

    public BlockOriginCatalyst() {
        super(Material.ROCK, MapColor.RED);
        setHardness(2.5F);
        setHarvestLevel("pickaxe", 2);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
}
