package net.kozibrodka.battletower.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.battletower.gen.TowerDestroyer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;

import java.util.ArrayList;
import java.util.List;

public class DestroyerSystem {

    private static List TowerDestroyers = new ArrayList();;



//    public boolean OnTickInGame(Minecraft mc) {
//        if(mc.player != null && mc.world != null) {
//            if(System.currentTimeMillis() > GolemListener.time + 1000L) {
////                this.checkServerSetting();
//
//                for(int i = 0; i < TowerDestroyers.size(); ++i) {
//                    AS_TowerDestroyer temp = (AS_TowerDestroyer)TowerDestroyers.get(i);
//                    temp.Update();
//                }
//            }
//
//            return true;
//        } else {
//            return true;
//        }
//    }

    @EventListener
    public void tickGame(GameTickEvent.End event){
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
            if(GolemListener.mc != null && GolemListener.mc.player != null && GolemListener.mc.world != null) {
                if(System.currentTimeMillis() > GolemListener.time + 1000L) {
                    for(int i = 0; i < TowerDestroyers.size(); ++i) {
                        TowerDestroyer temp = (TowerDestroyer)TowerDestroyers.get(i);
                        temp.Update();
                    }
                }
            }
        }else{
            if(GolemListener.mcServ != null) {
                if(System.currentTimeMillis() > GolemListener.time + 1000L) {
                    for(int i = 0; i < TowerDestroyers.size(); ++i) {
                        TowerDestroyer temp = (TowerDestroyer)TowerDestroyers.get(i);
                        temp.Update();
                    }
                }
            }
        }
    }

    public static void registerTowerDestroyer(TowerDestroyer td) {
        TowerDestroyers.add(td);
    }

    public static void unRegisterTowerDestroyer(TowerDestroyer td) {
        TowerDestroyers.remove(td);
    }
}
