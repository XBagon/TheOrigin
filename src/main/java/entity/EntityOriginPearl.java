package entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class EntityOriginPearl extends EntityThrowable {

    private EntityLivingBase thrower;
    EntityLiving closest = null;

    public EntityOriginPearl(World worldIn) {
        super(worldIn);
    }

    public EntityOriginPearl(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @SideOnly(Side.CLIENT)
    public EntityOriginPearl(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (result.entityHit != null && result.entityHit != thrower) {
            if (result.entityHit instanceof EntityLiving) {
                closest = (EntityLiving) result.entityHit;
            }
        }

        if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = result.getBlockPos();


            List<EntityLiving> entitiesWithinAABB = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(blockpos.getX() - 2, blockpos.getY() - 2, blockpos.getZ() - 2, blockpos.getX() + 2, blockpos.getY() + 2, blockpos.getZ() + 2));


            for (EntityLiving e : entitiesWithinAABB) {
                if (closest == null) {
                    closest = e;
                } else if (e.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ()) < closest.getDistance(blockpos.getX(), blockpos.getY(), blockpos.getZ())) {
                    closest = e;
                }
            }

        }


        if (!this.worldObj.isRemote) {
            if (entitylivingbase instanceof EntityPlayerMP && closest != null) {
                EntityPlayerMP entityplayermp = (EntityPlayerMP) entitylivingbase;
                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && closest.worldObj == this.worldObj && !closest.isPlayerSleeping()) {
                    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(closest, getThrower().getPosition().getX(), getThrower().getPosition().getY(), getThrower().getPosition().getZ(), 0.0F);
                    if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) { // Don't indent to lower patch size


                        if (closest.isRiding()) {
                            closest.dismountRidingEntity();
                        }

                        closest.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                        closest.fallDistance = 0.0F;
                        closest.attackEntityFrom(DamageSource.fall, event.getAttackDamage());
                    }
                }
            } else if (closest != null) {
                closest.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                closest.fallDistance = 0.0F;
            }

            this.setDead();
        }
    }
}
