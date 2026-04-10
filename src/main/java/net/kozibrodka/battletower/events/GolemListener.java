package net.kozibrodka.battletower.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.battletower.entity.EntityGolem;
import net.kozibrodka.battletower.entity.EntityGolemFireball;
import net.kozibrodka.battletower.entity.EntityGolem_old;
import net.kozibrodka.battletower.network.CoordsPacket;
import net.kozibrodka.battletower.renderentity.RenderGolem;
import net.kozibrodka.battletower.renderentity.RenderGolemFireball;
import net.kozibrodka.battletower.renderentity.RenderGolem_old;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.FireballEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.EntityHandlerRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import java.lang.invoke.MethodHandles;

public class GolemListener {

    static {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    ///////////////ADD
    public static Item przedmiot2;
    @EventListener
    private void registerItems(ItemRegistryEvent event) {
        przedmiot2 = new Siekierka(Identifier.of(MOD_ID, "siekierka")).setTranslationKey(MOD_ID, "siekierka");
//        horsesaddle = new HorseSaddle(Identifier.of(MOD_ID, "horsesaddle")).setTranslationKey(MOD_ID, "horsesaddle");
    }
    //////////////ADD


    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();
    public static long time;

    @Environment(EnvType.CLIENT)
    public static Minecraft mc;

    @Environment(EnvType.SERVER)
    public static MinecraftServer mcServ;

    @EventListener
    private static void registerEntities(EntityRegister event) {
        event.register(EntityGolem_old.class, String.valueOf(Identifier.of(MOD_ID, "TowerGolem_old")));
        event.register(EntityGolem.class, String.valueOf(Identifier.of(MOD_ID, "TowerGolem")));
        event.register(EntityGolemFireball.class, String.valueOf(Identifier.of(MOD_ID, "GolemFireball")));

        time = System.currentTimeMillis();
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
            mc = (Minecraft) FabricLoader.getInstance().getGameInstance();
        }else{
            mcServ = (MinecraftServer) FabricLoader.getInstance().getGameInstance();
        }
    }

    @EventListener
    private static void registerMobHandlers(MobHandlerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("TowerGolem_old") , EntityGolem_old::new);
        Registry.register(event.registry, MOD_ID.id("TowerGolem") , EntityGolem::new);
    }

    @EventListener
    private static void registerEntityHandlers(EntityHandlerRegistryEvent event) {
        Registry.register(event.registry, MOD_ID.id("GolemFireball") , EntityGolemFireball::new);
    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityGolem_old.class, new RenderGolem_old());
        event.renderers.put(EntityGolem.class, new RenderGolem());
        event.renderers.put(EntityGolemFireball.class, new RenderGolemFireball());
    }

    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, MOD_ID.id("coords"), CoordsPacket.TYPE);
    }
}
