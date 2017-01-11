package com.thva.underground.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeUndergroundFridge extends BiomeUnderground
{
	public BiomeUndergroundFridge(BiomeProperties properties)
	{
		super(properties);
		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xD9F2E4;
    }
}
