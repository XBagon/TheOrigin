package xbagon.theorigin.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOriginGrass extends Block {

    public BlockOriginGrass() {
        super(Material.GROUND, MapColor.LIME);
        setHardness(1.5F);
        setHarvestLevel("shovel", 1);
        setSoundType(SoundType.PLANT);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setTickRandomly(true);
    }


    //@SubscribeEvent
    //public void onEntity(EntityInteract event){
    //    if(event.getTarget() instanceof EntityEnderman && event.getItemStack().getItem() == this.){
    //        ((EntityEnderman)event.getTarget()).setHeldBlockState(this.getDefaultState());
    //    }
    //}



    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {

            for (int x = -1; x <= 1; ++x) {
                for (int y = -1; y <= 1 ; y++) {
                    for (int z = -1; z <= 1 ; z++) {

                        BlockPos blockpos = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock() == Blocks.DIRT || iblockstate.getBlock() == Blocks.GRASS)
                        {
                            if(rand.nextFloat()>0.99F)
                            worldIn.setBlockState(blockpos, this.getDefaultState());
                        }

                    }
                }


            }
        }
    }
}
