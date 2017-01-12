package com.thva.underground.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeUndergroundForest extends BiomeUnderground
{
	public BiomeUndergroundForest(BiomeProperties properties)
	{
		super(properties);
		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x007700;
    }
}
