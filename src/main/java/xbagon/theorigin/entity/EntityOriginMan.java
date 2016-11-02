package xbagon.theorigin.entity;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xbagon.theorigin.TheOrigin;

public class EntityOriginMan extends EntityEnderman{

    public static final ResourceLocation LOOT = new ResourceLocation(TheOrigin.MOD_ID, "entities/originman");

    public EntityOriginMan(World worldIn) {
        super(worldIn);
    }

    public void onUpdate(){
        super.onUpdate();
    }

    protected ResourceLocation getLootTable() {
        return LOOT;
    }
}
