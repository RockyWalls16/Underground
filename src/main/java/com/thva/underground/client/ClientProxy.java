package com.thva.underground.client;

import com.thva.underground.CommonProxy;
import com.thva.underground.blocks.UGBlocks;
import com.thva.underground.client.block.BlockRenderer;
import com.thva.underground.client.item.ItemRenderer;
import com.thva.underground.items.UGItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		super.init(event);

		for (Item bi : UGItems.ITEM_LIST)
		{
			ItemRenderer.registerItemRender(bi);
		}

		for (Block bo : UGBlocks.BLOCK_LIST)
		{
			BlockRenderer.registerBlockRender(bo);
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
