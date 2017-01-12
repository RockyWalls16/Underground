package com.thva.underground.world.biomes.provider;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class UndergroundGenLayer extends GenLayer
{
	public UndergroundGenLayer(long seed)
	{
		super(seed);
	}

	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, ChunkProviderSettings settings)
    {
		GenLayer genlayer = new GenLayerUgIsland(1L);
        genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
        GenLayerUgAddIsland GenLayerUgAddIsland = new GenLayerUgAddIsland(1L, genlayer);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, GenLayerUgAddIsland);
        GenLayerUgAddIsland GenLayerUgAddIsland1 = new GenLayerUgAddIsland(2L, genlayerzoom);
        GenLayerUgAddIsland1 = new GenLayerUgAddIsland(50L, GenLayerUgAddIsland1);
        GenLayerUgAddIsland1 = new GenLayerUgAddIsland(70L, GenLayerUgAddIsland1);
        GenLayerUgRemoveTooMuchPlains genlayerremovetoomuchocean = new GenLayerUgRemoveTooMuchPlains(2L, GenLayerUgAddIsland1);
        GenLayerUgAddSnow genlayeraddsnow = new GenLayerUgAddSnow(2L, genlayerremovetoomuchocean);
        GenLayerUgAddIsland GenLayerUgAddIsland2 = new GenLayerUgAddIsland(3L, genlayeraddsnow);
        GenLayerUgEdge genlayeredge = new GenLayerUgEdge(2L, GenLayerUgAddIsland2, GenLayerUgEdge.Mode.COOL_WARM);
        genlayeredge = new GenLayerUgEdge(2L, genlayeredge, GenLayerUgEdge.Mode.HEAT_ICE);
        GenLayerZoom genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
        GenLayerUgAddIsland GenLayerUgAddIsland3 = new GenLayerUgAddIsland(4L, genlayerzoom1);
        GenLayer genlayer4 = GenLayerZoom.magnify(1000L, GenLayerUgAddIsland3, 0);
        int i = 3;
        int j = i;

        GenLayer lvt_7_1_ = GenLayerZoom.magnify(1000L, genlayer4, 0);
        GenLayerUgRiverInit genlayerriverinit = new GenLayerUgRiverInit(100L, lvt_7_1_);
        GenLayer lvt_9_1_ = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        GenLayer genlayerhills = new GenLayerUgHills(1000L, genlayer4, lvt_9_1_);
        GenLayer genlayer5 = GenLayerZoom.magnify(1000L, genlayerriverinit, 2);
        genlayer5 = GenLayerZoom.magnify(1000L, genlayer5, j);
        GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer5);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
        genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

        for (int k = 0; k < i; ++k)
        {
            genlayerhills = new GenLayerZoom((long)(1000 + k), genlayerhills);

            if (k == 0)
            {
                genlayerhills = new GenLayerUgAddIsland(3L, genlayerhills);
            }

            if (k == 1 || i == 1)
            {
                genlayerhills = new GenLayerShore(1000L, genlayerhills);
            }
        }

        GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(seed);
        genlayer3.initWorldGenSeed(seed);
        return new GenLayer[] {genlayerrivermix, genlayer3, genlayerrivermix};
    }
}
