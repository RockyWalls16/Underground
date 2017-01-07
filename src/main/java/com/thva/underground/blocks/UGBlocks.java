package com.thva.underground.blocks;

import java.util.ArrayList;

import com.thva.underground.UnderGround;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UGBlocks
{
	// List which contains all ug blocks
	public static final ArrayList<Block> BLOCK_LIST = new ArrayList<Block>();

	public static final BaseBlock TEST_BLOCK = (BaseBlock) new BaseBlock(Material.ROCK).setSoundType(SoundType.METAL).setHardness(1.0F).setResistance(2.0F).setUnlocalizedName("test_block").setCreativeTab(UnderGround.UG_TAB);

	/**
	 * Used to register all blocks in game
	 */
	public static void registerAllBlocks()
	{
		registerBaseBlock(TEST_BLOCK);
	}

	/**
	 * Register a base block in the GameRegistry
	 * 
	 * @param block
	 *            BaseBlock to register
	 */
	private static void registerBaseBlock(Block block)
	{
		BLOCK_LIST.add(block);

		ResourceLocation rl = new ResourceLocation(UnderGround.MODID, block.getUnlocalizedName().substring(5));

		GameRegistry.register(block, rl);
		GameRegistry.register(new ItemBlock(block), rl);
	}
}
