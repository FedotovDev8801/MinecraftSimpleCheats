
package com.fedotovdev.simplemccheats.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import com.fedotovdev.simplemccheats.world.inventory.CheatsMenuMenu;
import com.fedotovdev.simplemccheats.procedures.SunsetStartProcedure;
import com.fedotovdev.simplemccheats.procedures.NightStartProcedure;
import com.fedotovdev.simplemccheats.procedures.MidnightStartProcedure;
import com.fedotovdev.simplemccheats.procedures.GiveBarrierProcedure;
import com.fedotovdev.simplemccheats.procedures.DayStartProcedure;
import com.fedotovdev.simplemccheats.procedures.ClearInventoryProcedure;
import com.fedotovdev.simplemccheats.McSimpleCheatsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CheatsMenuButtonMessage {
	private final int buttonID, x, y, z;

	public CheatsMenuButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public CheatsMenuButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(CheatsMenuButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(CheatsMenuButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = CheatsMenuMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			DayStartProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			SunsetStartProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			NightStartProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			MidnightStartProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			GiveBarrierProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			ClearInventoryProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		McSimpleCheatsMod.addNetworkMessage(CheatsMenuButtonMessage.class, CheatsMenuButtonMessage::buffer, CheatsMenuButtonMessage::new, CheatsMenuButtonMessage::handler);
	}
}
