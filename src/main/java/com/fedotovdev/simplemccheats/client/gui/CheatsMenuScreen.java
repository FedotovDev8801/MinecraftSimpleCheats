package com.fedotovdev.simplemccheats.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

import com.fedotovdev.simplemccheats.world.inventory.CheatsMenuMenu;
import com.fedotovdev.simplemccheats.network.CheatsMenuButtonMessage;
import com.fedotovdev.simplemccheats.McSimpleCheatsMod;

public class CheatsMenuScreen extends AbstractContainerScreen<CheatsMenuMenu> {
	private final static HashMap<String, Object> guistate = CheatsMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_day;
	Button button_sunset;
	Button button_night;
	Button button_midnight;
	Button button_give_barrier;
	Button button_clear_inventory;

	public CheatsMenuScreen(CheatsMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 219;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("mc_simple_cheats:textures/screens/cheats_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.mc_simple_cheats.cheats_menu.label_day_cycle"), 8, 6, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.mc_simple_cheats.cheats_menu.label_inventory"), 109, 6, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_day = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_day"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(0, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 20, 40, 20).build();
		guistate.put("button:button_day", button_day);
		this.addRenderableWidget(button_day);
		button_sunset = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_sunset"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(1, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 41, 56, 20).build();
		guistate.put("button:button_sunset", button_sunset);
		this.addRenderableWidget(button_sunset);
		button_night = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_night"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(2, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 62, 51, 20).build();
		guistate.put("button:button_night", button_night);
		this.addRenderableWidget(button_night);
		button_midnight = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_midnight"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(3, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 83, 67, 20).build();
		guistate.put("button:button_midnight", button_midnight);
		this.addRenderableWidget(button_midnight);
		button_give_barrier = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_give_barrier"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(4, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 110, this.topPos + 20, 87, 20).build();
		guistate.put("button:button_give_barrier", button_give_barrier);
		this.addRenderableWidget(button_give_barrier);
		button_clear_inventory = Button.builder(Component.translatable("gui.mc_simple_cheats.cheats_menu.button_clear_inventory"), e -> {
			if (true) {
				McSimpleCheatsMod.PACKET_HANDLER.sendToServer(new CheatsMenuButtonMessage(5, x, y, z));
				CheatsMenuButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}).bounds(this.leftPos + 110, this.topPos + 41, 103, 20).build();
		guistate.put("button:button_clear_inventory", button_clear_inventory);
		this.addRenderableWidget(button_clear_inventory);
	}
}
