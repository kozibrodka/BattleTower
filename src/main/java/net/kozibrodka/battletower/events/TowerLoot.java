package net.kozibrodka.battletower.events;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;

import java.util.ArrayList;
import java.util.List;

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

    public static int floor_top_size;
    public static int floor_1_size;
    public static int floor_2_size;
    public static int floor_3_size;
    public static int floor_4_size;
    public static int floor_5_size;
    public static int floor_6_size;
    public static int floor_7_size;
    public static int floor_8_size;

    @EventListener(priority = ListenerPriority.LOWEST)
    public void registerAchievements(AchievementRegisterEvent event) {

        /// MODS



        /// { Item.ID -> Random() -> +ILE -> META }
//        FLOOR_TOP.add(new int[] {Item.XXX.id, 1, 1, 0});
//        FLOOR_TOP.add(new int[] {Block.XXX.id, 1, 1, 0});
        /// TOP FLOOR
        FLOOR_TOP.add(new int[] {Item.CAKE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_PICKAXE.id, 1, 1, 0});
        FLOOR_TOP.add(new int[] {Item.GOLDEN_AXE.id, 1, 1, 0});
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
        FLOOR_2.add(new int[] {Block.WOOL.id, 5, 4, 4});
        FLOOR_2.add(new int[] {Block.WOOL.id, 5, 4, 14});

        floor_2_size = FLOOR_2.size();
        /// 3st FLOOR
        FLOOR_3.add(new int[] {Item.FEATHER.id, 3, 6, 0});
        FLOOR_3.add(new int[] {Item.BREAD.id, 1, 1, 0});
        FLOOR_3.add(new int[] {Block.GLASS.id, 3, 5, 0});
        FLOOR_3.add(new int[] {Block.BROWN_MUSHROOM.id, 3, 3, 0});
        FLOOR_3.add(new int[] {Item.EGG.id, 1, 1, 0});
        FLOOR_3.add(new int[] {Item.CLAY.id, 3, 6, 0});
        FLOOR_3.add(new int[] {Block.WOOL.id, 5, 4, 7});
        FLOOR_3.add(new int[] {Block.WOOL.id, 5, 4, 5});

        floor_3_size = FLOOR_3.size();
        /// 4st FLOOR
        FLOOR_4.add(new int[] {Item.STRING.id, 3, 2, 0});
        FLOOR_4.add(new int[] {Item.STONE_SWORD.id, 1, 1, 0});
        FLOOR_4.add(new int[] {Block.TORCH.id, 3, 5, 0});
        FLOOR_4.add(new int[] {Block.RED_MUSHROOM.id, 3, 3, 0});
        FLOOR_4.add(new int[] {Item.STONE_AXE.id, 1, 1, 0});
        FLOOR_4.add(new int[] {Item.COOKIE.id, 8, 1, 0});
        FLOOR_4.add(new int[] {Block.WOOL.id, 5, 4, 11});
        FLOOR_4.add(new int[] {Block.WOOL.id, 5, 4, 15});

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

        floor_7_size = FLOOR_7.size();
        /// 8st FLOOR
        FLOOR_8.add(new int[] {Block.TNT.id, 3, 3, 0});
        FLOOR_8.add(new int[] {Item.DIAMOND_HOE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.OBSIDIAN.id, 3, 6, 0});
        FLOOR_8.add(new int[] {Item.CHAIN_CHESTPLATE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Item.GOLDEN_CHESTPLATE.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.POWERED_RAIL.id, 2, 4, 0});
        FLOOR_8.add(new int[] {Item.COMPASS.id, 1, 1, 0});
        FLOOR_8.add(new int[] {Block.ICE.id, 3, 6, 0});

        floor_8_size = FLOOR_8.size();
    }
}
