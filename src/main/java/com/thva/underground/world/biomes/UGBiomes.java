package com.thva.underground.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UGBiomes
{
	public static final Biome UNDERGROUND_PLAINS = new BiomeUndergroundPlains(new BiomeProperties("Under. Plains"));
	
	/**
	 * Used to register biomes
	 */
	public static void initBiomes()
	{
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(UNDERGROUND_PLAINS, 10));
	}
}
