package net.kozibrodka.battletower.gen;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.battletower.events.GeneratorStarter;

import java.util.Random;

public class MobTable {

    private static boolean mocreatures = FabricLoader.getInstance().isModLoaded("mocreatures");

    public static String getTowerMobType(Random random) {
        if (mocreatures && GeneratorStarter.config.mocr_monsters) {
            switch (random.nextInt(8)) {
                case 0, 7:
                    return "Skeleton";
                case 1:
                    return "Zombie";
                case 2:
                    return "Spider";
                case 3:
                    return "mocreatures:FlameWraith";
                case 4:
                    return "mocreatures:Wraith";
                case 5:
                    return "mocreatures:Mummy";
                case 6:
                    return "mocreatures:Scorpion";
                default:
                    return "";
            }
        } else {
            return switch (random.nextInt(4)) {
                case 0 -> "Skeleton";
                case 1, 2 -> "Zombie";
                case 3 -> "Spider";
                default -> "";
            };
        }
    }
}
