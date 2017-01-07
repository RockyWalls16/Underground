package com.thva.underground.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UGCreativeTab extends CreativeTabs
{

	public UGCreativeTab()
	{
		super("underground_tab");
	}

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(Item.getItemFromBlock(Blocks.RED_FLOWER));
	}

}
