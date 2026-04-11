package net.kozibrodka.battletower.events;

import net.danygames2014.elementalarrows.ElementalArrows;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.events.mod_mocreatures;
import net.kozibrodka.wolves.events.ItemListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TowerLoot {

    public static List<int[]> FLOOR_TOP = new ArrayList<>();
    public static List<int[]> FLOOR_1 = new ArrayList<>();
    public static List<int[]> FLOOR_2 = new ArrayList<>();
    public static List<int[]> FLOOR_3 = new ArrayList<>();
    public static List<int[]> FLOOR_4 = new ArrayList<>();
    public static List<int[]> FLOOR_5 = new ArrayList<>();
    public static List<int[]> FLOOR_6 = new ArrayList<>();
    public static List<int[]> FLOOR_7 = new ArrayList<>();
    public static List<int[]> FLOOR_8 = new ArrayList<>();
    public static List<int[]> FLOOR_9 = new ArrayList<>();

    public static int floor_top_size;
    public static int floor_1_size;
    public static int floor_2_size;
    public static int floor_3_size;
    public static int floor_4_size;
    public static int floor_5_size;
    public static int floor_6_size;
    public static int floor_7_size;
    public static int floor_8_size;
    public static int floor_9_size;

    private Random random = new Random();

    @EventListener(priority = ListenerPriority.LOWEST)
    public void registerAchievements(AchievementRegisterEvent event) {

        /// MODS more to be added.
        if(FabricLoader.getInstance().isModLoaded("mocreatures") && GeneratorStarter.config.mod_loot.loot_mocreatures){
            FLOOR_9.add(new int[] {mod_mocreatures.whip.id, 1, 1, 0});
            FLOOR_8.add(new int[] {mod_mocreatures.horsesaddle.id, 1, 1, 0});
            FLOOR_7.add(new int[] {mod_mocreatures.medallion.id, 1, 1, 0});
            FLOOR_6.add(new int[] {mod_mocreatures.sheepbell.id, 1, 1, 0});
            FLOOR_5.add(new int[] {mod_mocreatures.rope.id, 2, 1, 0});
            FLOOR_4.add(new int[] {mod_mocreatures.petfood.id, 4, 1, 0});
            FLOOR_3.add(new int[] {mod_mocreatures.sugarlump.id, 3, 1, 0});
            FLOOR_2.add(new int[] {mod_mocreatures.woolball.id, 1, 1, 0});
        }

        if(FabricLoader.getInstance().isModLoaded("elementalarrows") && GeneratorStarter.config.mod_loot.loot_elementalarrows){
            FLOOR_8.add(new int[] {ElementalArrows.lightingArrow.id, 1, 1, 0});
            FLOOR_7.add(new int[] {ElementalArrows.explosiveArrow.id, 2, 2, 0});
            FLOOR_6.add(new int[] {ElementalArrows.fireArrow.id, 3, 2, 0});
            FLOOR_5.add(new int[] {ElementalArrows.iceArrow.id, 3, 2, 0});
            FLOOR_4.add(new int[] {ElementalArrows.torchArrow.id, 5, 1, 0});
            FLOOR_3.add(new int[] {ElementalArrows.eggArrow.id, 2, 1, 0});
        }

        if(FabricLoader.getInstance().isModLoaded("wolves") && GeneratorStarter.config.mod_loot.loot_betterthanwolves){
            FLOOR_9.add(new int[] {ItemListener.steel.id, 1, 1, 0});
            FLOOR_8.add(new int[] {ItemListener.hempCloth.id, 1, 1, 0});
            FLOOR_7.add(new int[] {ItemListener.ropeItem.id, 1, 1, 0});
            FLOOR_6.add(new int[] {ItemListener.hemp.id, 1, 1, 0});
            FLOOR_5.add(new int[] {ItemListener.hempFibers.id, 2, 1, 0});
            FLOOR_4.add(new int[] {ItemListener.strap.id, 1, 1, 0});
            FLOOR_3.add(new int[] {ItemListener.scouredLeather.id, 1, 1, 0});
            FLOOR_2.add(new int[] {ItemListener.hempSeeds.id, 1, 1, 0});
            FLOOR_1.add(new int[] {ItemListener.flour.id, 1, 1, 0});
        }


        /// { Item.ID -> Random() -> +ILE -> META }
//        FLOOR_TOP.add(new int[] {Item.XXX.id, 1, 1, 0});
//        FLOOR_TOP.add(new int[] {Block.XXX.id, 1, 1, 0});
        /// TOP FLOOR
        FLOOR_TOP.add(new int[] {Item.CAKE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_PICKAXE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_PICKAXE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_AXE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_AXE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_SHOVEL.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_SHOVEL.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.MAP.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.CLOCK.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.IRON_DOOR.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Block.JUKEBOX.id, 1, 1, 0});

        floor_top_size = FLOOR_TOP.size();
//        System.out.println(FLOOR_TOP.size() + " KURWA  " + TowerLoot.FLOOR_TOP.get(0)[0] );
        /// 1st FLOOR
        FLOOR_1.add(new int[] {Item.STICK.id, 5, 2, 0});
        FLOOR_1.add(new int[] {Item.SEEDS.id, 5, 2, 0});
        FLOOR_1.add(new int[] {Block.COBBLESTONE.id, 5, 4, 0});
        FLOOR_1.add(new int[] {Block.SAND.id, 5, 4, 0});
        FLOOR_1.add(new int[] {Item.SNOWBALL.id, 3, 6, 0});
        FLOOR_1.add(new int[] {Item.BOWL.id, 1, 1, 0});
        FLOOR_1.add(new int[] {Block.WOOL.id, 3, 2, 0});
        FLOOR_1.add(new int[] {Block.GRAVEL.id, 5, 4, 0});

        floor_1_size = FLOOR_1.size();
        /// 2st FLOOR
        FLOOR_2.add(new int[] {Item.COAL.id, 3, 6, 0});
        FLOOR_2.add(new int[] {Item.WOODEN_PICKAXE.id, 1, 1, 0});
        FLOOR_2.add(new int[] {Block.LOG.id, 3, 4, 0});
        FLOOR_2.add(new int[] {Block.LOG.id, 3, 2, 2});
        FLOOR_2.add(new int[] {Item.SUGAR.id, 3, 6, 0});
        FLOOR_2.add(new int[] {Item.WOODEN_SHOVEL.id, 1, 1, 0});
        FLOOR_2.add(new int[] {Block.WOOL.id, 4, 3, random.nextInt(16)});
        FLOOR_2.add(new int[] {Item.DYE.id, 6, 1, 7});

        floor_2_size = FLOOR_2.size();
        /// 3st FLOOR
        FLOOR_3.add(new int[] {Item.FEATHER.id, 3, 6, 0});
        FLOOR_3.add(new int[] {Item.BREAD.id, 1, 1, 0});
        FLOOR_3.add(new int[] {Block.GLASS.id, 3, 5, 0});
        FLOOR_3.add(new int[] {Block.BROWN_MUSHROOM.id, 3, 3, 0});
        FLOOR_3.add(new int[] {Item.EGG.id, 1, 1, 0});
        FLOOR_3.add(new int[] {Item.CLAY.id, 3, 6, 0});
        FLOOR_3.add(new int[] {Block.WOOL.id, 5, 3, random.nextInt(16)});
        FLOOR_3.add(new int[] {Item.DYE.id, 6, 1, 8});

        floor_3_size = FLOOR_3.size();
        /// 4st FLOOR
        FLOOR_4.add(new int[] {Item.STRING.id, 3, 2, 0});
        FLOOR_4.add(new int[] {Item.STONE_SWORD.id, 1, 1, 0});
        FLOOR_4.add(new int[] {Block.TORCH.id, 3, 5, 0});
        FLOOR_4.add(new int[] {Block.RED_MUSHROOM.id, 3, 3, 0});
        FLOOR_4.add(new int[] {Item.STONE_AXE.id, 1, 1, 0});
        FLOOR_4.add(new int[] {Item.COOKIE.id, 8, 1, 0});
        FLOOR_4.add(new int[] {Block.WOOL.id, 6, 3, random.nextInt(16)});
        FLOOR_4.add(new int[] {Item.DYE.id, 6, 1, 0});

        floor_4_size = FLOOR_4.size();
        /// 5st FLOOR
        FLOOR_5.add(new int[] {Block.WOODEN_STAIRS.id, 3, 5, 0});
        FLOOR_5.add(new int[] {Item.BOW.id, 1, 1, 0});
        FLOOR_5.add(new int[] {Block.BRICKS.id, 3, 5, 0});
        FLOOR_5.add(new int[] {Item.CHAIN_BOOTS.id, 1, 1, 0});
        FLOOR_5.add(new int[] {Item.GOLDEN_BOOTS.id, 1, 1, 0});
        FLOOR_5.add(new int[] {Item.WOODEN_DOOR.id, 1, 1, 0});
        FLOOR_5.add(new int[] {Item.PAINTING.id, 8, 2, 0});
        FLOOR_5.add(new int[] {Block.CLAY.id, 3, 9, 0});

        floor_5_size = FLOOR_5.size();
        /// 6st FLOOR
        FLOOR_6.add(new int[] {Block.LADDER.id, 3, 9, 0});
        FLOOR_6.add(new int[] {Item.FLINT_AND_STEEL.id, 1, 1, 0});
        FLOOR_6.add(new int[] {Block.GLOWSTONE.id, 3, 5, 0});
        FLOOR_6.add(new int[] {Item.CHAIN_HELMET.id, 1, 1, 0});
        FLOOR_6.add(new int[] {Item.GOLDEN_HELMET.id, 1, 1, 0});
        FLOOR_6.add(new int[] {Item.MILK_BUCKET.id, 1, 1, 0});
        FLOOR_6.add(new int[] {Block.BRICKS.id, 4, 7, 0});
        FLOOR_6.add(new int[] {Block.PUMPKIN.id, 2, 6, 0});
        FLOOR_6.add(new int[] {Item.GOLDEN_SHOVEL.id, 1, 1, 0});

        floor_6_size = FLOOR_6.size();
        /// 7st FLOOR
        FLOOR_7.add(new int[] {Block.JACK_O_LANTERN.id, 3, 9, 0});
        FLOOR_7.add(new int[] {Item.LAVA_BUCKET.id, 1, 1, 0});
        FLOOR_7.add(new int[] {Block.RAIL.id, 5, 9, 0});
        FLOOR_7.add(new int[] {Item.CHAIN_LEGGINGS.id, 1, 1, 0});
        FLOOR_7.add(new int[] {Item.GOLDEN_LEGGINGS.id, 1, 1, 0});
        FLOOR_7.add(new int[] {Block.NOTE_BLOCK.id, 2, 3, 0});
        FLOOR_7.add(new int[] {Block.BOOKSHELF.id, 3, 9, 0});
        FLOOR_7.add(new int[] {Item.APPLE.id, 1, 1, 0});
        FLOOR_7.add(new int[] {Item.GOLDEN_AXE.id, 1, 1, 0});

        floor_7_size = FLOOR_7.size();
        /// 8st FLOOR
        FLOOR_8.add(new int[] {Block.TNT.id, 3, 3, 0});
        FLOOR_8.add(new int[] {Item.DIAMOND_HOE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.OBSIDIAN.id, 3, 6, 0});
        FLOOR_8.add(new int[] {Item.CHAIN_CHESTPLATE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Item.GOLDEN_CHESTPLATE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.POWERED_RAIL.id, 2, 3, 0});
        FLOOR_8.add(new int[] {Item.COMPASS.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.ICE.id, 3, 6, 0});
        FLOOR_8.add(new int[] {Item.GOLDEN_PICKAXE.id, 1, 1, 0});

        floor_8_size = FLOOR_8.size();
        /// 9st FLOOR - VERY RARE FLOOR -
        FLOOR_9.add(new int[] {Item.DIAMOND.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.GOLD_INGOT.id, 2, 1, 0});
        FLOOR_9.add(new int[] {Item.IRON_INGOT.id, 3, 2, 0});
        FLOOR_9.add(new int[] {Item.GOLDEN_APPLE.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.SADDLE.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Block.SPONGE.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.RECORD_CAT.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.RECORD_THIRTEEN.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.DYE.id, 3, 1, 3});
        FLOOR_9.add(new int[] {Item.DIAMOND_CHESTPLATE.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.DIAMOND_LEGGINGS.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.DIAMOND_BOOTS.id, 1, 1, 0});
        FLOOR_9.add(new int[] {Item.DIAMOND_HELMET.id, 1, 1, 0});

        floor_9_size = FLOOR_9.size();
    }
}
