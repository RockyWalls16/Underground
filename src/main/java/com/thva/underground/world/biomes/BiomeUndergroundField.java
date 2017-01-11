package com.thva.underground.world.biomes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerGrass;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeUndergroundField extends BiomeUnderground
{
	public BiomeUndergroundField(BiomeProperties properties)
	{
		super(properties);
		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x2FB01C;
    }
}
