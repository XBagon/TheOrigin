package xbagon.theorigin.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xbagon.theorigin.ModBlocks;
import xbagon.theorigin.ModItems;
import xbagon.theorigin.entity.EntityOriginMan;

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


    @SubscribeEvent
    public void onEntity(EntityInteract event){
        if (!event.getWorld().isRemote)
        {
            ItemStack stack = event.getEntityPlayer().getHeldItemMainhand();
        if(stack != null) {
            if(stack.getItem() instanceof ItemBlock) {
                if (event.getTarget() instanceof EntityEnderman && ((ItemBlock) stack.getItem()).getBlock() instanceof BlockOriginGrass && ((EntityEnderman) event.getTarget()).getHeldBlockState() != ModBlocks.originGrass.getDefaultState()) {
                    stack.stackSize--;
                    EntityEnderman eman = ((EntityEnderman) event.getTarget());
                    eman.setDead();
                    EntityOriginMan oman = new EntityOriginMan(event.getWorld());
                    oman.setFire(1000);
                    oman.setGlowing(true);
                }
            }
        }
        }
    }

    @SubscribeEvent
    public void onDrop(LootTableLoadEvent event){
        if(event.getName().equals(LootTableList.ENTITIES_ENDERMAN)){
        }
    }

    @SubscribeEvent
    public void onDrop(LivingDropsEvent event){
        Entity entity = event.getEntity();
        EntityLivingBase living = event.getEntityLiving();

        if (entity instanceof EntityEnderman){
            if (((EntityEnderman) living).getHeldBlockState() == ModBlocks.originGrass.getDefaultState()){
                System.out.println("TEST");
                living.dropItem(ModItems.originPearl, 1);
            }
        }

    }


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