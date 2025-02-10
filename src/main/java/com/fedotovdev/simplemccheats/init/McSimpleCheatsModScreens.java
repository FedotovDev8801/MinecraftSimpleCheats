
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.fedotovdev.simplemccheats.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import com.fedotovdev.simplemccheats.client.gui.CheatsMenuScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class McSimpleCheatsModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(McSimpleCheatsModMenus.CHEATS_MENU.get(), CheatsMenuScreen::new);
		});
	}
}
