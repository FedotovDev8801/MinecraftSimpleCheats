
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.fedotovdev.simplemccheats.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import com.fedotovdev.simplemccheats.network.CheatsMenuOpenMessage;
import com.fedotovdev.simplemccheats.McSimpleCheatsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class McSimpleCheatsModKeyMappings {
	public static final KeyMapping CHEATS_MENU_OPEN = new KeyMapping("key.mc_simple_cheats.cheats_menu_open", GLFW.GLFW_KEY_Y, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuOpenMessage(0, 0));
				CheatsMenuOpenMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(CHEATS_MENU_OPEN);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				CHEATS_MENU_OPEN.consumeClick();
			}
		}
	}
}
