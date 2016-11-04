package xbagon.theorigin;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

/**
 * Created by XBagon on 04.11.2016.
 */
public class OriginWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.originDimensionType;
    }

    @Override
    public String getSaveFolder(){
        return "TheOrigin";
    }

    @Override
    public IChunkGenerator createChunkGenerator(){
        return new OriginChunkGenerator(worldObj);
    }

}
