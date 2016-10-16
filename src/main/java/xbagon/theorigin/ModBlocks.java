package xbagon.theorigin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xbagon.theorigin.blocks.BlockOriginGrass;

public class ModBlocks {

    public static Block originGrass;

    public void init() {
        originGrass = new BlockOriginGrass();
        NameUtils.setNames(originGrass, "originGrass");
    }

    public void register() {
        registerBlock(originGrass);
    }

    private void registerBlock(Block block) {
        GameRegistry.register(block);
        ItemBlock itemblock = new ItemBlock(block);
        itemblock.setUnlocalizedName(block.getUnlocalizedName()).setRegistryName(block.getRegistryName());
        GameRegistry.register(itemblock);
    }

}
