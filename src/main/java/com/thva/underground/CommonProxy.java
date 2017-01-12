package com.thva.underground;

import com.thva.underground.achievements.UGAchievements;
import com.thva.underground.blocks.UGBlocks;
import com.thva.underground.items.UGItems;
import com.thva.underground.misc.UGConfig;
import com.thva.underground.recipes.UGRecipes;
import com.thva.underground.world.UndergroundDimension;
import com.thva.underground.world.biomes.UGBiomes;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		UGConfig.loadConfig(event.getModConfigurationDirectory());
		
		UGBlocks.registerAllBlocks();
		UGItems.registerAllItems();
		UGBiomes.initBiomes();
		UndergroundDimension.initDimension();
		
		UGConfig.saveConfig();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		UGRecipes.registerShapedRecipes();
		UGRecipes.registerShapeLessRecipes();
		UGRecipes.registerFurnaceRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		UGAchievements.registerAllAchievements();
	}
}
