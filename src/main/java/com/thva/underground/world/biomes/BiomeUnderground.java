package com.thva.underground.world.biomes;

import com.thva.underground.world.gen.UndergroundBiomeDecorator;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public abstract class BiomeUnderground extends Biome
{
	public BiomeUnderground(BiomeProperties properties)
	{
		super(properties);
	}
	
	@Override
	public BiomeDecorator createBiomeDecorator()
    {
        return new UndergroundBiomeDecorator();
    }
}
