package com.thva.underground.world;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterUnderground extends Teleporter
{
	public TeleporterUnderground(WorldServer worldIn)
	{
		super(worldIn);
	}
	
	@Override
    public void placeInPortal(Entity entityIn, float rotationYaw)
    {
        entityIn.setLocationAndAngles((double)0, (double)260, (double)0, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;
    }

	@Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw)
    {
    	return false;
    }

	@Override
    public boolean makePortal(Entity entityIn)
    {
    	return false;
    }
}
