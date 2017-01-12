package com.thva.underground.items;

import com.thva.underground.world.TeleporterUnderground;
import com.thva.underground.world.UndergroundDimension;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemWarp extends BaseItem
{
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (!player.isRiding() && !player.isBeingRidden() && player.isNonBoss() && !worldIn.isRemote)
        {
			player.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, UndergroundDimension.getDimensionType().getId(), new TeleporterUnderground((WorldServer) player.getEntityWorld()));
        }
		return EnumActionResult.SUCCESS;
    }
}
