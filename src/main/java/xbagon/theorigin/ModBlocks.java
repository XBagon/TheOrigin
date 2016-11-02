package xbagon.theorigin;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xbagon.theorigin.blocks.BlockOriginGrass;
import xbagon.theorigin.blocks.BlockOriginStone;

public class ModBlocks {

    public static Block originGrass;
    public static Block originStone;
    public static Block originCatalyst;

    public void init() {
        originGrass = new BlockOriginGrass();
        NameUtils.setNames(originGrass, "originGrass");
        originStone = new BlockOriginStone();
        NameUtils.setNames(originStone, "originStone");
        originCatalyst = new BlockOriginStone();
        NameUtils.setNames(originCatalyst, "originCatalyst");
    }

    public void register() {
        registerBlock(originGrass);
        registerBlock(originStone);
        registerBlock(originCatalyst);
    }

    private void registerBlock(Block block) {
        GameRegistry.register(block);
        ItemBlock itemblock = new ItemBlock(block);
        itemblock.setUnlocalizedName(block.getUnlocalizedName()).setRegistryName(block.getRegistryName());
        GameRegistry.register(itemblock);
    }

}
