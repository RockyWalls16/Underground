package com.thva.underground.world;

import com.thva.underground.Underground;
import com.thva.underground.misc.UGConfig;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

public class UndergroundDimension
{
	private static DimensionType undergroundDimensionType;
	
	public static void initDimension()
	{
		int dimensionId = UGConfig.getConfig().getInt("dimensionId", "dimension", DimensionManager.getNextFreeDimId(), Integer.MIN_VALUE, Integer.MAX_VALUE, "Change if you have any conflict with dimension ids");
		
		undergroundDimensionType = DimensionType.register("underground", Underground.MODID, dimensionId, WorldProviderUnderground.class, false);
		DimensionManager.registerDimension(dimensionId, undergroundDimensionType);
	}
	
	public static DimensionType getDimensionType()
	{
		return undergroundDimensionType;
	}
}
