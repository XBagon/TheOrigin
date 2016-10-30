package xbagon.theorigin.worldgen;


import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGen implements IWorldGenerator {

    private WorldGenOriginGrass originGrass;

    public WorldGen() {
        originGrass = new WorldGenOriginGrass();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.getDimension()) {
            case 0:
                originGrass.generate(world);
                break;
            case -1:
                break;
            case 1:
                break;
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Minimum or Maximum Height out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chanceToSpawn; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);
            generator.generate(world, random, new BlockPos(x, y, z));
        }
    }
}
