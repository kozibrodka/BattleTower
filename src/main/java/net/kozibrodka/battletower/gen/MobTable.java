package net.kozibrodka.battletower.gen;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.battletower.events.GeneratorStarter;

import java.util.Random;

public class MobTable {

    private static boolean mocreatures = FabricLoader.getInstance().isModLoaded("mocreatures");

    public static String getTowerMobType(Random random) {
        if (mocreatures && GeneratorStarter.config.mocr_monsters) {
            switch (random.nextInt(4)) {
                case 0:
                    return "Skeleton";
                case 1:
                    return "Zombie";
                case 2:
                    return "Spider";
                case 3:
                    return "mocreatures:XXX"; //todo
                case 4:
                    return "mocreatures:XXX";
                case 5:
                    return "mocreatures:XXX";
                case 6:
                    return "mocreatures:XXX";
                case 7:
                    return "mocreatures:XXX";
                default:
                    return "";
            }
        } else {
            switch (random.nextInt(4)) {
                case 0:
                    return "Skeleton";
                case 1:
                    return "Zombie";
                case 2:
                    return "Zombie";
                case 3:
                    return "Spider";
                default:
                    return "";
            }
        }
    }
}
