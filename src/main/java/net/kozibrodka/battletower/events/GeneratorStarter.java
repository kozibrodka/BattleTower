package net.kozibrodka.battletower.events;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.kozibrodka.battletower.gen.GenerateTower_old;
import net.kozibrodka.battletower.gen.WorldGenTower;
import net.kozibrodka.battletower.glasscfg.BattleTowerCFG;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorStarter {

    @ConfigRoot(value = "BattleTowerCFG", visibleName = "BattleTower Config")
    public static final BattleTowerCFG config = new BattleTowerCFG();

    private int towercount = 200;
//    public static int rarity = 1;


    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

//    @EventListener
//    public void populate_old(WorldGenEvent.ChunkDecoration event) {
//
//        if (towercount >= config.tower_rarity * 100) {
//            if (random.nextInt(2) == 0) {
//                int k = event.x + random.nextInt(16) + 8;
//                int l = random.nextInt(16) + 64;
//                int i1 = event.z + random.nextInt(16) + 8;
//                System.out.println("PROBUJE ");
//                if ((new GenerateTower_old()).generate(event.world, random, k, l, i1)) {
//                    System.out.println("STWORZONA " + k + " " + i1);
//                    towercount = 0;
//                }
//            }
//        } else {
//            towercount++;
//            System.out.println("DZIAŁAM " + towercount);
//        }
//    }






    Random random = new Random();
    static boolean isWorking = false;
    private List TowerPositions = new ArrayList();;
//    private double minDistanceBetweenTowers = config.mintowerdistance;
//    private static int rarity = config.towerrarity;
//    private int raritycounter = rarity * 100;
    private int spawncounter = 200;

    @EventListener
    public void populate(WorldGenEvent.ChunkDecoration event) {

        if (event.world.dimension.id != 0) {
            return;
            /// Tylko Overworld narazie...
        }

        if(!isWorking) {
            isWorking = true;
            if(this.spawncounter > (config.tower_rarity * 100)) {
                boolean spawn = true;
                double mindist = 100.0D;

                for(int temp = 0; temp < this.TowerPositions.size(); ++temp) {
                    Vec3i temp1 = (Vec3i)this.TowerPositions.get(temp);
                    double dist = temp1.distanceTo(event.x, 64, event.z);
                    if(dist < mindist) {
                        mindist = dist;
                    }

                    if(dist < config.min_tower_distance) {
                        spawn = false;
                        break;
                    }
                }

                if(spawn && this.AttemptToSpawnTower(event.world, random, event.x, event.z)) {
                    if(this.TowerPositions.size() > 30) {
                        this.TowerPositions.remove(0);
                    }

                    Vec3i var12 = new Vec3i(event.x, 64, event.z);
                    this.TowerPositions.add(var12);
                    this.spawncounter = 0;
                }
            } else {
                ++this.spawncounter;
            }

            isWorking = false;
        }
    }

    private boolean AttemptToSpawnTower(World world, Random random, int x, int z) {
//        System.out.println("NASTEPUJE PROBA");
        int y = this.GetSurfaceBlockHeight(world, x, z);
        return y != 49 && (new WorldGenTower()).generate(world, random, x, y, z);
//        return y != 49 && (new GenerateTower_old()).generate(world, random, x, y, z);
    }

    private int GetSurfaceBlockHeight(World world, int x, int z) {
        int h = 50;

        do {
            ++h;
        } while(world.getBlockId(x, h, z) != 0);

        return h - 1;
    }
















}

// Based seed: 2415029562135091566


