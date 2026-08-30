package net.kozibrodka.battletower.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class GolemHelperCL {

    @Environment(EnvType.CLIENT)
    public static Minecraft mc = (Minecraft) FabricLoader.getInstance().getGameInstance();

}
