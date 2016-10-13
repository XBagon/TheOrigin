package entity;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityOriginPearl extends EntityThrowable {
	
    private EntityLivingBase thrower;
    private World world;   
    private BlockPos origin;
    
    public EntityOriginPearl(World worldIn)
    {
        super(worldIn);
    }

    public EntityOriginPearl(World worldIn, EntityLivingBase throwerIn, BlockPos pos)
    {
        super(worldIn, throwerIn);
        this.thrower = throwerIn;
        this.world = worldIn;
        this.origin = pos;
    }

    @SideOnly(Side.CLIENT)
    public EntityOriginPearl(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
	@Override
	protected void onImpact(RayTraceResult result)
    {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (result.entityHit != null)
        {
            if (result.entityHit == this.thrower)
            {
                return;
            }
            //result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, entitylivingbase), 0.0F);
        }

        if (result.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            BlockPos blockpos = result.getBlockPos();
            
            List<Entity> entities = world.loadedEntityList;
            
            Entity closest = null;
            
            
            //for (Entity entity : entities) {
            //	if(entity instanceof EntityMob){
			//		if(closest == null){
			//			closest = entity;
			//		}
			//		else if(closest.getPosition().)
            //	}
			//} 
            
        }

        for (int i = 0; i < 32; ++i)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian(), new int[0]);
        }

        if (!this.worldObj.isRemote)
        {
            if (entitylivingbase instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)entitylivingbase;

                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.worldObj == this.worldObj && !entityplayermp.isPlayerSleeping())
                {
                    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
                    if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                    { // Don't indent to lower patch size
                    if (this.rand.nextFloat() < 0.05F && this.worldObj.getGameRules().getBoolean("doMobSpawning"))
                    {
                        EntityEndermite entityendermite = new EntityEndermite(this.worldObj);
                        entityendermite.setSpawnedByPlayer(true);
                        entityendermite.setLocationAndAngles(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, entitylivingbase.rotationYaw, entitylivingbase.rotationPitch);
                        this.worldObj.spawnEntityInWorld(entityendermite);
                    }

                    if (entitylivingbase.isRiding())
                    {
                        entitylivingbase.dismountRidingEntity();
                    }

                    entitylivingbase.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                    entitylivingbase.fallDistance = 0.0F;
                    entitylivingbase.attackEntityFrom(DamageSource.fall, event.getAttackDamage());
                    }
                }
            }
            else if (entitylivingbase != null)
            {
                entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                entitylivingbase.fallDistance = 0.0F;
            }

            this.setDead();
        }
    }
}
