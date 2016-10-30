package xbagon.theorigin.worldgen;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xbagon.theorigin.ModBlocks;


public class WorldGenOriginGrass {


    public boolean generate(World worldIn) {

        int bedrocklevel = 0;
        int toplevel = 0;

        for (int y = 0; y < 256; y++) {
            if (worldIn.getBlockState(new BlockPos(0, y, 0)) == Blocks.BEDROCK.getDefaultState()) {
                bedrocklevel = y;
            }
            if (worldIn.getBlockState(new BlockPos(0, y, 0)).isFullBlock()) {
                toplevel = y;
            }
        }

        for (int y = bedrocklevel + 1; y < toplevel + 1; y++) {
            worldIn.setBlockState(new BlockPos(0, y, 0), ModBlocks.originGrass.getDefaultState());
        }

        return true;
    }


}
