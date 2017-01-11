package com.thva.underground.world.biomes;

import com.thva.underground.misc.UGConfig;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UGBiomes
{
	public static final Biome UNDERGROUND_PLAINS = new BiomeUndergroundPlains(new BiomeProperties("Under. Plains"));
	public static final Biome UNDERGROUND_FIELD = new BiomeUndergroundField(new BiomeProperties("Under. Field"));
	public static final Biome UNDERGROUND_FOREST = new BiomeUndergroundForest(new BiomeProperties("Under. Forest"));
	public static final Biome UNDERGROUND_SHROOM = new BiomeUndergroundForest(new BiomeProperties("Under. Shroom Forest"));
	public static final Biome UNDERGROUND_FRIDGE = new BiomeUndergroundFridge(new BiomeProperties("Under. Fridge"));
	public static final Biome UNDERGROUND_VOLCANO = new BiomeUndergroundVolcano(new BiomeProperties("Under. Volcano"));
	
	/**
	 * Used to register biomes
	 */
	public static void initBiomes()
	{
		Biome.registerBiome(getBiomeId("Ug_Plains"), "Ug_Plains", UNDERGROUND_PLAINS);
		Biome.registerBiome(getBiomeId("Ug_Field"), "Ug_Field", UNDERGROUND_FIELD);
		Biome.registerBiome(getBiomeId("Ug_Forest"), "Ug_Forest", UNDERGROUND_FOREST);
		Biome.registerBiome(getBiomeId("Ug_Shroom"), "Ug_Shroom", UNDERGROUND_SHROOM);
		Biome.registerBiome(getBiomeId("Ug_Fridge"), "Ug_Fridge", UNDERGROUND_FRIDGE);
		Biome.registerBiome(getBiomeId("Ug_Volcano"), "Ug_Volcano", UNDERGROUND_VOLCANO);
	}
	
	/**
	 * Get biome id from config
	 * @param key config key name
	 * @return biome id from config
	 */
	private static int getBiomeId(String key)
	{
		return UGConfig.getConfig().getInt(key, "biomes", getNewBiomeId(), Integer.MIN_VALUE, Integer.MAX_VALUE, "");
	}
	
	/**
	 * Get a new biome id currently not used
	 * @return unique biome id
	 */
	private static int getNewBiomeId()
	{
		Biome foundBiome = null;
		int id = -1;
		
		do
		{
			id++;
			foundBiome = Biome.getBiomeForId(id);
			
		} while(foundBiome != null);
		
		return id;
	}
}
