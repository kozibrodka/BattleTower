package net.kozibrodka.battletower.gen;

import net.kozibrodka.battletower.events.TowerLoot;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class LootTable {


    public static ItemStack getRandomChestItem_new(int floorNR, Random random, int isTowerTop) {
        if (isTowerTop == 1) {
            int a = random.nextInt(TowerLoot.floor_top_size);
            return new ItemStack(TowerLoot.FLOOR_TOP.get(a)[0], random.nextInt(TowerLoot.FLOOR_TOP.get(a)[1]) + TowerLoot.FLOOR_TOP.get(a)[2], TowerLoot.FLOOR_TOP.get(a)[3]);
        }
        switch (floorNR) {
            case 1:
                int a = random.nextInt(TowerLoot.floor_1_size);
                return new ItemStack(TowerLoot.FLOOR_1.get(a)[0], random.nextInt(TowerLoot.FLOOR_1.get(a)[1]) + TowerLoot.FLOOR_1.get(a)[2], TowerLoot.FLOOR_1.get(a)[3]);
            case 2:
                int b = random.nextInt(TowerLoot.floor_2_size);
                return new ItemStack(TowerLoot.FLOOR_2.get(b)[0], random.nextInt(TowerLoot.FLOOR_2.get(b)[1]) + TowerLoot.FLOOR_2.get(b)[2], TowerLoot.FLOOR_2.get(b)[3]);
            case 3:
                int c = random.nextInt(TowerLoot.floor_3_size);
                return new ItemStack(TowerLoot.FLOOR_3.get(c)[0], random.nextInt(TowerLoot.FLOOR_3.get(c)[1]) + TowerLoot.FLOOR_3.get(c)[2], TowerLoot.FLOOR_3.get(c)[3]);
            case 4:
                int d = random.nextInt(TowerLoot.floor_4_size);
                return new ItemStack(TowerLoot.FLOOR_4.get(d)[0], random.nextInt(TowerLoot.FLOOR_4.get(d)[1]) + TowerLoot.FLOOR_4.get(d)[2], TowerLoot.FLOOR_4.get(d)[3]);
            case 5:
                int e = random.nextInt(TowerLoot.floor_5_size);
                return new ItemStack(TowerLoot.FLOOR_5.get(e)[0], random.nextInt(TowerLoot.FLOOR_5.get(e)[1]) + TowerLoot.FLOOR_5.get(e)[2], TowerLoot.FLOOR_5.get(e)[3]);
            case 6:
                int f = random.nextInt(TowerLoot.floor_6_size);
                return new ItemStack(TowerLoot.FLOOR_6.get(f)[0], random.nextInt(TowerLoot.FLOOR_6.get(f)[1]) + TowerLoot.FLOOR_6.get(f)[2], TowerLoot.FLOOR_6.get(f)[3]);
            case 7:
                int g = random.nextInt(TowerLoot.floor_7_size);
                return new ItemStack(TowerLoot.FLOOR_7.get(g)[0], random.nextInt(TowerLoot.FLOOR_7.get(g)[1]) + TowerLoot.FLOOR_7.get(g)[2], TowerLoot.FLOOR_7.get(g)[3]);
            case 8:
                int h = random.nextInt(TowerLoot.floor_8_size);
                return new ItemStack(TowerLoot.FLOOR_8.get(h)[0], random.nextInt(TowerLoot.FLOOR_8.get(h)[1]) + TowerLoot.FLOOR_8.get(h)[2], TowerLoot.FLOOR_8.get(h)[3]);
            case 9,10:
                int i = random.nextInt(TowerLoot.floor_9_size);
                return new ItemStack(TowerLoot.FLOOR_9.get(i)[0], random.nextInt(TowerLoot.FLOOR_9.get(i)[1]) + TowerLoot.FLOOR_9.get(i)[2], TowerLoot.FLOOR_9.get(i)[3]);
            default:
                return null;
        }
    }


    public static ItemStack getRandomChestItem_old(int i, Random random, int isTower_topFloor)
    {
        int j = random.nextInt(4);
        if(isTower_topFloor == 1)
        {
            if(j == 0)
            {
                return new ItemStack(Item.IRON_SWORD, 1);
            }
            if(j == 1)
            {
                return new ItemStack(Item.IRON_PICKAXE, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Item.IRON_AXE, 1);
            }
            if(j == 3)
            {
                return new ItemStack(Item.IRON_SHOVEL, 1);
            } else
            {
                return null;
            }
        }
        if(i == 1)
        {
            if(j == 0)
            {
                return new ItemStack(Item.STICK, random.nextInt(5) + 2);
            }
            if(j == 1)
            {
                return new ItemStack(Item.SEEDS, random.nextInt(5) + 2);
            }
            if(j == 2)
            {
                return new ItemStack(Block.COBBLESTONE, random.nextInt(5) + 4);
            }
            if(j == 3)
            {
                return new ItemStack(Block.SAND, random.nextInt(5) + 4);
            } else
            {
                return null;
            }
        }
        if(i == 2)
        {
            if(j == 0)
            {
                return new ItemStack(Item.COAL, random.nextInt(3) + 6);
            }
            if(j == 1)
            {
                return new ItemStack(Item.WOODEN_PICKAXE, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.LOG, random.nextInt(3) + 4);
            }
            if(j == 3)
            {
                return new ItemStack(Block.WOOL, random.nextInt(3) + 6);
            } else
            {
                return null;
            }
        }
        if(i == 3)
        {
            if(j == 0)
            {
                return new ItemStack(Item.FEATHER, random.nextInt(3) + 6);
            }
            if(j == 1)
            {
                return new ItemStack(Item.BREAD, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.GLASS, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemStack(Block.BROWN_MUSHROOM, random.nextInt(3) + 3);
            } else
            {
                return null;
            }
        }
        if(i == 4)
        {
            if(j == 0)
            {
                return new ItemStack(Item.STRING, random.nextInt(3) + 2);
            }
            if(j == 1)
            {
                return new ItemStack(Item.STONE_SWORD, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.TORCH, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemStack(Block.RED_MUSHROOM, random.nextInt(3) + 3);
            } else
            {
                return null;
            }
        }
        if(i == 5)
        {
            if(j == 0)
            {
                return new ItemStack(Block.WOODEN_STAIRS, random.nextInt(3) + 5);
            }
            if(j == 1)
            {
                return new ItemStack(Item.BOW, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.BRICKS, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemStack(Item.CHAIN_BOOTS, 1);
            } else
            {
                return null;
            }
        }
        if(i == 6)
        {
            if(j == 0)
            {
                return new ItemStack(Block.LADDER, random.nextInt(3) + 9);
            }
            if(j == 1)
            {
                return new ItemStack(Item.FLINT_AND_STEEL, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.GLOWSTONE, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemStack(Item.CHAIN_HELMET, 1);
            } else
            {
                return null;
            }
        }
        if(i == 7)
        {
            if(j == 0)
            {
                return new ItemStack(Block.JACK_O_LANTERN, random.nextInt(3) + 9);
            }
            if(j == 1)
            {
                return new ItemStack(Item.LAVA_BUCKET, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.RAIL, random.nextInt(5) + 9);
            }
            if(j == 3)
            {
                return new ItemStack(Item.CHAIN_LEGGINGS, 1);
            } else
            {
                return null;
            }
        }
        if(i == 8)
        {
            if(j == 0)
            {
                return new ItemStack(Block.TNT, random.nextInt(3) + 3);
            }
            if(j == 1)
            {
                return new ItemStack(Item.DIAMOND_HOE, 1);
            }
            if(j == 2)
            {
                return new ItemStack(Block.OBSIDIAN, random.nextInt(3) + 6);
            }
            if(j == 3)
            {
                return new ItemStack(Item.CHAIN_CHESTPLATE, 1);
            } else
            {
                return null;
            }
        } /// Co tutaj sie dzieje?
        if(random.nextInt(4) == 0)
        {
            return new ItemStack(Item.DIAMOND, random.nextInt(3) + 3);
        }
        if(random.nextInt(4) == 1)
        {
            return new ItemStack(Item.IRON_INGOT, random.nextInt(3) + 3);
        }
        if(random.nextInt(4) == 2)
        {
            return new ItemStack(Item.DIAMOND, random.nextInt(4) + 6);
        }
        if(random.nextInt(4) == 3)
        {
            return new ItemStack(Item.IRON_INGOT, random.nextInt(4) + 6);
        } else
        {
            return null;
        }
    }

    public static ItemStack getRandomChestItem_AS(int i, Random random, boolean topFloor) {
        int j = random.nextInt(4);
        if(topFloor) {
            switch(j) {
                case 0:
                    return new ItemStack(Item.REDSTONE, random.nextInt(8));
                case 1:
                    return new ItemStack(Item.IRON_INGOT, random.nextInt(8));
                case 2:
                    return new ItemStack(Item.DIAMOND, random.nextInt(6));
                case 3:
                    return new ItemStack(Item.GOLD_INGOT, random.nextInt(8));
            }
        }

        switch(i) {
            case 1:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.STICK, random.nextInt(5) + 6);
                    case 1:
                        return new ItemStack(Item.SEEDS, random.nextInt(5) + 6);
                    case 2:
                        return new ItemStack(Block.PLANKS, random.nextInt(5) + 7);
                    case 3:
                        return new ItemStack(Item.COAL, random.nextInt(5) + 6);
                }
            case 2:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.COAL, random.nextInt(3) + 6);
                    case 1:
                        return new ItemStack(Item.WOODEN_PICKAXE, 1);
                    case 2:
                        return new ItemStack(Block.PLANKS, random.nextInt(3) + 6);
                    case 3:
                        return new ItemStack(Block.WOOL, random.nextInt(3) + 6);
                }
            case 3:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.FEATHER, random.nextInt(3) + 6);
                    case 1:
                        return new ItemStack(Item.BREAD, 1);
                    case 2:
                        return new ItemStack(Block.GLASS, random.nextInt(3) + 6);
                    case 3:
                        return new ItemStack(Block.RED_MUSHROOM, 1);
                }
            case 4:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.STRING, random.nextInt(3) + 4);
                    case 1:
                        return new ItemStack(Item.IRON_SWORD, 1);
                    case 2:
                        return new ItemStack(Block.TORCH, random.nextInt(3) + 8);
                    case 3:
                        return new ItemStack(Block.ROSE, random.nextInt(3) + 3);
                }
            case 5:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.SIGN, 1);
                    case 1:
                        return new ItemStack(Item.BOW, 1);
                    case 2:
                        return new ItemStack(Block.BRICKS, random.nextInt(3) + 5);
                    case 3:
                        return new ItemStack(Item.CHAIN_BOOTS, 1);
                }
            case 6:
                switch(j) {
                    case 0:
                        return new ItemStack(Block.LADDER, random.nextInt(3) + 9);
                    case 1:
                        return new ItemStack(Item.FLINT_AND_STEEL, 1);
                    case 2:
                        return new ItemStack(Block.GLOWSTONE, random.nextInt(3) + 5);
                    case 3:
                        return new ItemStack(Item.CHAIN_HELMET, 1);
                }
            case 7:
                switch(j) {
                    case 0:
                        return new ItemStack(Block.TNT, random.nextInt(3) + 6);
                    case 1:
                        return new ItemStack(Item.DIAMOND_HOE, 1);
                    case 2:
                        return new ItemStack(Block.OBSIDIAN, random.nextInt(3) + 2);
                    case 3:
                        return new ItemStack(Item.CHAIN_CHESTPLATE, 1);
                }
            case 8:
                switch(j) {
                    case 0:
                        return new ItemStack(Item.DIAMOND_SWORD, 1);
                    case 1:
                        return new ItemStack(Item.DIAMOND_PICKAXE, 1);
                    case 2:
                        return new ItemStack(Item.DIAMOND_AXE, 1);
                    case 3:
                        return new ItemStack(Item.DIAMOND_SHOVEL, 1);
                }
            default:
                return null;
        }
    }
}
