package com.thva.underground.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BaseBlock extends Block
{
	public BaseBlock(Material materialIn)
	{
		super(materialIn);
	}

	/*
	 * Protected method bypass
	 */
	@Override
	public BaseBlock setSoundType(SoundType stepSound)
	{
		super.setSoundType(stepSound);
		return this;
	}
}
