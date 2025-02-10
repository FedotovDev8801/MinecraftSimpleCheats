
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.fedotovdev.simplemccheats.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import com.fedotovdev.simplemccheats.world.inventory.CheatsMenuMenu;
import com.fedotovdev.simplemccheats.McSimpleCheatsMod;

public class McSimpleCheatsModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, McSimpleCheatsMod.MODID);
	public static final RegistryObject<MenuType<CheatsMenuMenu>> CHEATS_MENU = REGISTRY.register("cheats_menu", () -> IForgeMenuType.create(CheatsMenuMenu::new));
}
