package com.thva.underground.world;

import com.thva.underground.Underground;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderUnderground extends WorldProviderHell
{
	
	@Override
    public void init()
    {
        this.biomeProvider = new BiomeProviderSingle(Biomes.PLAINS);
        this.hasNoSky = true;
    }
	
	@Override
	public IChunkGenerator createChunkGenerator()
    {
        return new ChunkProviderUnderground(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed());
    }
	
	@Override
	protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }
	
	@Override
	public WorldBorder createWorldBorder()
    {
        return new WorldBorder();
    }
	
	@Override
	public int getActualHeight()
    {
        return hasNoSky ? 128 : 256;
    }
	
	@Override
	public DimensionType getDimensionType()
	{
		return UndergroundDimension.UNDERGROUND_DIMENSION_TYPE;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0.8D, 0.9D, 1.0D);
    }
}
