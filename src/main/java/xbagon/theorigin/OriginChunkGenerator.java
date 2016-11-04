package xbagon.theorigin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by XBagon on 04.11.2016.
 */
public class OriginChunkGenerator implements IChunkGenerator {

    private final World worldObj;
    private Random random;
    private Biome[] biomesForGeneration;

    private List<Biome.SpawnListEntry> mobs = Lists.newArrayList(new Biome.SpawnListEntry(EntitySheep.class, 100, 5, 10));

    private MapGenBase caveGenerator = new MapGenCaves();
    private OriginTerrainGenerator terraingen = new OriginTerrainGenerator();

    public OriginChunkGenerator(World worldObj){
        this.worldObj = worldObj;
        long seed = worldObj.getSeed();
        random = new Random((seed + 516)*314);
        terraingen.setup(worldObj, random);
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, InitMapGenEvent.EventType.CAVE);
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        biomesForGeneration = worldObj.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, x*4 - 2, z * 4 - 2, 10, 10);
        terraingen.setBiomesForGeneration(biomesForGeneration);
        terraingen.generate(x, z, chunkprimer);

        biomesForGeneration = worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, biomesForGeneration);

        //caveGenerator.generate(worldObj, x, z, chunkprimer);

        Chunk chunk = new Chunk(worldObj, x, z);

        byte[] biomeArray = chunk.getBiomeArray();
        for (int i = 0; i < biomeArray.length; i++) {
            biomeArray[i] = (byte)Biome.getIdForBiome(biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        int i = x * 16;
        int j = z * 16;
        BlockPos blockPos = new BlockPos(i, 0, j);
        Biome biome = worldObj.getBiomeForCoordsBody(blockPos.add(16, 0, 16));

        biome.decorate(worldObj, random, blockPos);

        WorldEntitySpawner.performWorldGenSpawning(worldObj, biome, i+8, j+8, 16, 16, random);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        if(creatureType == EnumCreatureType.CREATURE){
            return mobs;
        }
        return ImmutableList.of();
    }

    @Nullable
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }
}
