package com.thva.underground.world.gen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ChunkProviderUnderground implements IChunkGenerator
{
	//Blocks
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState STONE = Blocks.STONE.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    protected static final IBlockState WATER = Blocks.WATER.getDefaultState();
    protected static final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    protected static final IBlockState DIRT = Blocks.DIRT.getDefaultState();
    protected static final IBlockState SAND = Blocks.SAND.getDefaultState();
    protected static final IBlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
    
    private final World world;
    private final boolean generateStructures;
    private final Random rand;
    private double[] depthBuffer = new double[256];
    private double[] buffer;
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    /** Determines whether something other than nettherack can be generated at a location */
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    private final WorldGenFire fireFeature = new WorldGenFire();
    private final WorldGenGlowStone1 lightGemGen = new WorldGenGlowStone1();
    private final WorldGenGlowStone2 hellPortalGen = new WorldGenGlowStone2();
    private final WorldGenerator quartzGen = new WorldGenMinable(Blocks.QUARTZ_ORE.getDefaultState(), 14, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator magmaGen = new WorldGenMinable(Blocks.MAGMA.getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenHellLava lavaTrapGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, true);
    private final WorldGenHellLava hellSpringGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, false);
    private final WorldGenBush brownMushroomFeature = new WorldGenBush(Blocks.BROWN_MUSHROOM);
    private final WorldGenBush redMushroomFeature = new WorldGenBush(Blocks.RED_MUSHROOM);
    private MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
    private MapGenBase genNetherCaves = new MapGenCavesHell();
    double[] pnr;
    double[] ar;
    double[] br;
    double[] noiseData4;
    double[] dr;

    public ChunkProviderUnderground(World worldIn, boolean p_i45637_2_, long seed)
    {
        this.world = worldIn;
        this.generateStructures = p_i45637_2_;
        this.rand = new Random(seed);
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        worldIn.setSeaLevel(63);
        
        this.genNetherBridge = (MapGenNetherBridge)TerrainGen.getModdedMapGen(genNetherBridge, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_BRIDGE);
        this.genNetherCaves = TerrainGen.getModdedMapGen(genNetherCaves, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.NETHER_CAVE);
    }

    /**
     * Generate world blocks
     * @param chunkX
     * @param chunkZ
     * @param primer
     */
    public void prepareHeights(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        int i = 4;
        int j = this.world.getSeaLevel();
        int k = 5;
        int l = 17;
        int i1 = 5;
        this.buffer = this.getHeights(this.buffer, chunkX * 4, 0, chunkZ * 4, 5, l, 5);

        for (int j1 = 0; j1 < 4; ++j1)
        {
            for (int k1 = 0; k1 < 4; ++k1)
            {
                for (int l1 = 0; l1 < 16; ++l1)
                {
                    double d0 = 0.0625D;
                    double d1 = this.buffer[((j1 + 0) * 5 + k1 + 0) * l + l1 + 0];
                    double d2 = this.buffer[((j1 + 0) * 5 + k1 + 1) * l + l1 + 0];
                    double d3 = this.buffer[((j1 + 1) * 5 + k1 + 0) * l + l1 + 0];
                    double d4 = this.buffer[((j1 + 1) * 5 + k1 + 1) * l + l1 + 0];
                    double d5 = (this.buffer[((j1 + 0) * 5 + k1 + 0) * l + l1 + 1] - d1) * d0;
                    double d6 = (this.buffer[((j1 + 0) * 5 + k1 + 1) * l + l1 + 1] - d2) * d0;
                    double d7 = (this.buffer[((j1 + 1) * 5 + k1 + 0) * l + l1 + 1] - d3) * d0;
                    double d8 = (this.buffer[((j1 + 1) * 5 + k1 + 1) * l + l1 + 1] - d4) * d0;

                    for (int i2 = 0; i2 < 16; ++i2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int j2 = 0; j2 < 4; ++j2)
                        {
                            double d14 = d9;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d9;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                IBlockState iblockstate = null;

                                if (l1 * 16 + i2 < j)
                                {
                                    iblockstate = WATER;
                                }
                                
                                if (d15 > 0.0D)
                                {
                                    iblockstate = STONE;
                                }

                                int l2 = j2 + j1 * 4;
                                int i3 = i2 + l1 * 16;//Manage height
                                int j3 = k2 + k1 * 4;
                                primer.setBlockState(l2, i3, j3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    /**
     * Generate Layers
     * @param chunkX
     * @param chunkY
     * @param primer
     */
    public void buildSurfaces(int chunkX, int chunkY, ChunkPrimer primer)
    {
        int i = this.world.getSeaLevel() + 1;
        double d0 = 0.03125D;
        this.depthBuffer = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.depthBuffer, chunkX * 16, chunkY * 16, 0, 16, 16, 1, 0.0625D, 0.0625D, 0.0625D);

        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                int l = (int)(this.depthBuffer[j + k * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
                int i1 = -1;
                IBlockState iblockstate = STONE;
                IBlockState iblockstate1 = STONE;

                for (int j1 = 0; j1 <= 255; ++j1)
                {
                    if (j1 < 255 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5))
                    {
                        IBlockState currentBlockState = primer.getBlockState(k, j1, j);
                        IBlockState over = primer.getBlockState(k, j1 + 1, j);
                        
                        //SAND
                        if (j1 < 72 - this.rand.nextInt(5) && j1 > this.rand.nextInt(5) + 20 && currentBlockState.getBlock() == STONE.getBlock())
                        {
                        	for(int kl = 0; kl < 4; kl++)
                        	{
	                        	IBlockState under = primer.getBlockState(k, j1 - kl, j);
	                        	if (j1 < 72 && j1 > 50 + this.rand.nextInt(5) && under.getMaterial().isSolid())
	                        	{
	                        		primer.setBlockState(k, j1, j, SAND);
	                        	}
	                        	else
	                        	{
	                        		primer.setBlockState(k, j1, j, SANDSTONE);
	                        	}
                        	}
                        }//Grass Layer
                        else if(j1 < 150 - this.rand.nextInt(5) && j1 >= 68 && currentBlockState.getBlock() == STONE.getBlock())
                        {
                        	if(!over.getMaterial().isSolid())
                        	{
                        		primer.setBlockState(k, j1, j, GRASS);
                        	}
                        	else
                        	{
                        		int depth = 4 + this.rand.nextInt(3) + (primer.getBlockState(k, j1 + 1, j).getBlock() == DIRT.getBlock() ? 2 : 0);
                        		for(int kl = 1; kl <= depth; kl++)
                        		{
                        			if(!primer.getBlockState(k, j1 + kl, j).getMaterial().isSolid())
                        			{
                        				primer.setBlockState(k, j1, j, DIRT);
                        				break;
                        			}
                        		}
                        	}
                        }//Generate Water columns
                        else if(j1 == world.getSeaLevel() - 1 && currentBlockState.getBlock() == WATER.getBlock() && over.getBlock() == AIR.getBlock() && this.rand.nextInt(150) == 0)
                        {
                        	for(int kl = j1 + 1; kl < 220; kl++)
                        	{
                        		if(primer.getBlockState(k, j1 + kl, j).getMaterial().isSolid())
                        		{
                        			primer.setBlockState(k, j1 + kl, j, WATER);
                        			break;
                        		}
                        	}
                        }
                    }
                    else
                    {
                        primer.setBlockState(k, j1, j, BEDROCK);
                    }
                }
            }
        }
    }

    @Override
    public Chunk provideChunk(int x, int z)
    {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.prepareHeights(x, z, chunkprimer);
        this.buildSurfaces(x, z, chunkprimer);
        this.genNetherCaves.generate(this.world, x, z, chunkprimer);

        if (this.generateStructures)
        {
            this.genNetherBridge.generate(this.world, x, z, chunkprimer);
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        Biome[] abiome = this.world.getBiomeProvider().getBiomes((Biome[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)Biome.getIdForBiome(abiome[i]);
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    /**
     * Generate heights using perlin noise
     * @return noise output
     */
    private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_)
    {
        if (p_185938_1_ == null)
        {
            p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
        }

        net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getNoisefield();

        double d0 = 684.412D;
        double d1 = 10053.236D;
        this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
        this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D, 100.0D);
        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 8.555150000000001D, 66.2206D, 8.555150000000001D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, d0, d1, d0);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, d0, d1, d0);
        int i = 0;
        double[] adouble = new double[p_185938_6_];

        for (int j = 0; j < p_185938_6_; ++j)
        {
            adouble[j] = Math.cos((double)j * Math.PI * 6.0D / (double)p_185938_6_) * 2.0D;
            double d2 = (double)j;

            //Max height
            if (j > 17 / 2)
            {
                d2 = (double)(14 - 1 - j);
            }

            //Min height
            if (d2 < 1.9D)
            {
                d2 = 1.9D - d2;
                adouble[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < p_185938_5_; ++l)
        {
            for (int i1 = 0; i1 < p_185938_7_; ++i1)
            {
                double d3 = 0.0D;

                for (int k = 0; k < p_185938_6_; ++k)
                {
                    double d4 = adouble[k];
                    double d5 = this.ar[i] / 512.0D;
                    double d6 = this.br[i] / 512.0D;
                    double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
                    double d8;

                    if (d7 < 0.0D)
                    {
                        d8 = d5;
                    }
                    else if (d7 > 1.0D)
                    {
                        d8 = d6;
                    }
                    else
                    {
                        d8 = d5 + (d6 - d5) * d7;
                    }

                    d8 = d8 - d4;

                    if (k > p_185938_6_ - 4)
                    {
                        double d9 = (double)((float)(k - (p_185938_6_ - 4)) / 3.0F);
                        d8 = d8 * (1.0D - d9) + -10.0D * d9;
                    }

                    if ((double)k < 0.0D)
                    {
                        double d10 = (0.0D - (double)k) / 4.0D;
                        d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
                        d8 = d8 * (1.0D - d10) + -10.0D * d10;
                    }

                    p_185938_1_[i] = d8;
                    ++i;
                }
            }
        }

        return p_185938_1_;
    }

    @Override
    public void populate(int x, int z)
    {
        BlockFalling.fallInstantly = true;
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        ChunkPos chunkpos = new ChunkPos(x, z);
        this.genNetherBridge.generateStructure(this.world, this.rand, chunkpos);

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA))
        for (int k = 0; k < 8; ++k)
        {
            this.hellSpringGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.FIRE))
        for (int i1 = 0; i1 < this.rand.nextInt(this.rand.nextInt(10) + 1) + 1; ++i1)
        {
            this.fireFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.GLOWSTONE))
        {
        for (int j1 = 0; j1 < this.rand.nextInt(this.rand.nextInt(10) + 1); ++j1)
        {
            this.lightGemGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
        }

        for (int k1 = 0; k1 < 10; ++k1)
        {
            this.hellPortalGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
        }
        }//Forge: End doGLowstone

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(this.world, this.rand, blockpos));

        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(this.world, this.rand, blockpos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM))
        {
        if (this.rand.nextBoolean())
        {
            this.brownMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
        }

        if (this.rand.nextBoolean())
        {
            this.redMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
        }
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.generateOre(this.world, this.rand, quartzGen, blockpos, net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.QUARTZ))
        for (int l1 = 0; l1 < 16; ++l1)
        {
            this.quartzGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
        }

        int i2 = this.world.getSeaLevel() / 2 + 1;

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_MAGMA))
        for (int l = 0; l < 4; ++l)
        {
            this.magmaGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), i2 - 5 + this.rand.nextInt(10), this.rand.nextInt(16)));
        }

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, false, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.NETHER_LAVA2))
        for (int j2 = 0; j2 < 16; ++j2)
        {
            this.lavaTrapGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
        }

        biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Post(this.world, this.rand, blockpos));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z)
    {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
    {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_)
    {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z){}
}
