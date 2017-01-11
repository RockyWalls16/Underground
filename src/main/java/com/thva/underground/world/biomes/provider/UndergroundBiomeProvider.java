package com.thva.underground.world.biomes.provider;

import java.util.List;

import com.google.common.collect.Lists;
import com.thva.underground.world.biomes.UGBiomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public class UndergroundBiomeProvider extends BiomeProvider
{
    /** The biome list. */
    public static List<Biome> allowedBiomes = Lists.newArrayList(UGBiomes.UNDERGROUND_PLAINS, UGBiomes.UNDERGROUND_FOREST, UGBiomes.UNDERGROUND_SHROOM);
    
    public UndergroundBiomeProvider(WorldInfo worldInfo)
	{
		super(worldInfo);
	}
    
	@Override
    public List<Biome> getBiomesToSpawnIn()
    {
        return this.allowedBiomes;
    }
	
	@Override
	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        return UndergroundGenLayer.initializeAllBiomeGenerators(seed, worldType, null);
    }

    @Override
    public boolean isFixedBiome()
    {
        return false;
    }
}
