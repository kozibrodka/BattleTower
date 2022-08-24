package net.kozibrodka.battletower.events;

import net.kozibrodka.battletower.gen.GenerateTower;
import net.kozibrodka.battletower.glasscfg.BattleTowerCFG;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.level.gen.LevelGenEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

import java.util.Random;

public class GeneratorStarter {

    @GConfig(value = "BattleTowerCFG", visibleName = "BattleTower Config")
    public static final BattleTowerCFG config = new BattleTowerCFG();

    private int towercount = 200;
//    public static int rarity = 1;
    Random random = new Random();


    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void populate(LevelGenEvent.ChunkDecoration event) {

        if (towercount >= config.towerrarity * 100) {
            if (random.nextInt(2) == 0) {
                int k = event.x + random.nextInt(16) + 8;
                int l = random.nextInt(16) + 64;
                int i1 = event.z + random.nextInt(16) + 8;
                if ((new GenerateTower()).generate(event.level, random, k, l, i1)) {
                    towercount = 0;
                }
            }
        } else {
            towercount++;
        }
    }
}

// Based seed: 2415029562135091566


