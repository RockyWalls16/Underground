package com.thva.underground.world;

import com.thva.underground.world.biomes.provider.UndergroundBiomeProvider;
import com.thva.underground.world.gen.ChunkProviderUnderground;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderUnderground extends WorldProviderHell
{
	private static int skyX;
	private static int skyY;
	private static int skyZ;
	private static boolean skyInit;
	private static int skyRGBMultiplier;

	/**
	 * Get color blend for biome & Height
	 * @param world
	 * @param playerX
	 * @param playerY
	 * @param playerZ
	 * @return hexColor
	 */
	@SideOnly(Side.CLIENT)
	public static int getFogBlendColour(World world, int playerX, int playerY, int playerZ)
	{
		if (playerX == skyX && playerX == skyY && playerZ == skyZ && skyInit)
		{
			return skyRGBMultiplier;
		}
		skyInit = true;

		GameSettings settings = Minecraft.getMinecraft().gameSettings;
		int[] ranges = ForgeModContainer.blendRanges;
		int distance = 0;
		if (settings.fancyGraphics && settings.renderDistanceChunks >= 0 && settings.renderDistanceChunks < ranges.length)
		{
			distance = ranges[settings.renderDistanceChunks];
		}

		int r = 0;
		int g = 0;
		int b = 0;

		int divider = 0;
		for (int x = -distance; x <= distance; ++x)
		{
			for (int y = -distance; y <= distance; ++y)
			{
				for (int z = -distance; z <= distance; ++z)
				{
					Biome biome = world.getBiome(new BlockPos(playerX + x, playerY + y, playerZ + z));
					int color = playerY + y > 72 ? biome.getGrassColorAtPos(new BlockPos(playerX + x, playerY + y, playerZ + z)) : 0x4E96B1;
					r += (color & 0xFF0000) >> 16;
					g += (color & 0x00FF00) >> 8;
					b += color & 0x0000FF;
					divider++;
				}
			}
		}

		int multiplier = (r / divider & 255) << 16 | (g / divider & 255) << 8 | b / divider & 255;

		skyX = playerX;
		skyY = playerY;
		skyZ = playerZ;
		skyRGBMultiplier = multiplier;
		return skyRGBMultiplier;
	}
	
	@Override
    public void init()
    {
        this.biomeProvider = new UndergroundBiomeProvider(this.world.getWorldInfo());
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
        return 256;
    }
	
	@Override
	public boolean canRespawnHere()
    {
        return true;
    }
	
	@Override
	public DimensionType getDimensionType()
	{
		return UndergroundDimension.getDimensionType();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		int l = WorldProviderUnderground.getFogBlendColour(world, MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ));
		float r = ((l >> 16 & 255) / 255.0F);
		float g = ((l >> 8 & 255) / 255.0F);
		float b = ((l & 255) / 255.0F);
		return new Vec3d(r, g, b);
	}
}
