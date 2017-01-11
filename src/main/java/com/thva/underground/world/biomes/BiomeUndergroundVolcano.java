package com.thva.underground.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeUndergroundVolcano extends BiomeUnderground
{
	public BiomeUndergroundVolcano(BiomeProperties properties)
	{
		super(properties);
		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x375034;
    }
}
