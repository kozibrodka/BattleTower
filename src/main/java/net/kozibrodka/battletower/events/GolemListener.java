package net.kozibrodka.battletower.events;


import net.kozibrodka.battletower.entity.EntityGolem;
import net.kozibrodka.battletower.renderentity.RenderGolem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MobHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;
import net.modificationstation.stationapi.api.util.Null;

public class GolemListener {

    ///////////////ADD
    public static TemplateItemBase przedmiot2;
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
//        przedmiot2 = new Siekierka(Identifier.of(MOD_ID, "siekierka")).setTranslationKey(MOD_ID, "siekierka");
    }
    //////////////ADD


    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    private static void registerEntities(EntityRegister event) {
        event.register(EntityGolem.class, String.valueOf(Identifier.of(MOD_ID, "TowerGolem")));
    }

    @EventListener
    private static void registerMobHandlers(MobHandlerRegistryEvent event) {
        event.registry.register(Identifier.of(MOD_ID, "TowerGolem"), EntityGolem::new);
    }

    @EventListener
    private static void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(EntityGolem.class, new RenderGolem());
    }
}
