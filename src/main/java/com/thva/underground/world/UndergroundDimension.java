package com.thva.underground.world;

import com.thva.underground.Underground;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class UndergroundDimension
{
	//TODO add configurable dimension id
	public static final DimensionType UNDERGROUND_DIMENSION_TYPE = DimensionType.register("underground", Underground.MODID, 47, WorldProviderUnderground.class, false);
	
	public static void initDimension()
	{
		DimensionManager.registerDimension(47, UNDERGROUND_DIMENSION_TYPE);
	}
	
}
