package net.kozibrodka.battletower.gen;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode



import net.kozibrodka.battletower.entity.EntityGolem;
import net.minecraft.block.BlockBase;
import net.minecraft.block.Plant;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockGrass,
//            EntityGolem, TileEntityMobSpawner, TileEntityChest, ItemStack,
//            Item, EntityList, ModLoader

public class GenerateTower extends Structure
{

    public GenerateTower()
    {

    }

    public boolean generate(Level world, Random random, int i, int j, int k)
    {
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        boolean flag1 = false;
        for(int k1 = 0; k1 < 32; k1++)
        {
            int l1 = (i + random.nextInt(8)) - random.nextInt(8);
            int i2 = (j + random.nextInt(4)) - random.nextInt(4);
            int j2 = (k + random.nextInt(8)) - random.nextInt(8);
            l = l1;
            i1 = i2;
            j1 = j2;
            boolean flag5 = false;
            if(world.getTileId(l1, i2, j2) != 0 && world.getTileId(l1, i2, j2) != BlockBase.SNOW.id || world.getTileId(l1, i2 - 1, j2) == 0)
            {
                continue;
            }
            int i3 = -4;
            do
            {
                if(i3 >= 5)
                {
                    break;
                }
                int i4 = -4;
                do
                {
                    if(i4 >= 5)
                    {
                        break;
                    }
                    int l4 = world.getTileId(l1 + i3, i2, j2 + i4);
                    if(l4 != 0 && l4 != BlockBase.SNOW.id && l4 != Plant.ROSE.id && l4 != Plant.DANDELION.id && l4 != BlockBase.LOG.id)
                    {
                        flag5 = true;
                    }
                    int l5 = world.getTileId(l1 + i3, i2 - 1, j2 + i4);
                    if(l5 != BlockBase.GRASS.id && l5 != BlockBase.SAND.id && l5 != BlockBase.STONE.id)
                    {
                        flag5 = true;
                    }
                    if(flag5)
                    {
                        break;
                    }
                    i4++;
                } while(true);
                if(flag5)
                {
                    break;
                }
                i3++;
            } while(true);
            if(flag5)
            {
                continue;
            }
            flag1 = true;
            break;
        }

        if(!flag1)
        {
            return false;
        }
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        int k2 = i1 - 6;
        int l2 = random.nextInt(3);
        field_22238_field_20340_floor = 1;
        field_22237_field_20341_topFloor = 0;
        for(; k2 < 120; k2 += 7)
        {
            if(k2 + 7 >= 120)
            {
                field_22237_field_20341_topFloor = 1;
            }
            for(int j3 = 0; j3 < 7; j3++)
            {
                if(k2 == i1 - 6 && j3 < 4)
                {
                    j3 = 4;
                }
                for(int j4 = -7; j4 < 7; j4++)
                {
                    for(int i5 = -7; i5 < 7; i5++)
                    {
                        int i6 = j4 + l;
                        int k6 = j3 + k2;
                        int l6 = i5 + j1;
                        if(i5 == -7)
                        {
                            if(j4 > -5 && j4 < 4)
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                            }
                            continue;
                        }
                        if(i5 == -6 || i5 == -5)
                        {
                            if(j4 == -5 || j4 == 4)
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                continue;
                            }
                            if(i5 == -6)
                            {
                                if(j4 == (j3 + 1) % 7 - 3)
                                {
                                    world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                                    if(j3 == 5)
                                    {
                                        world.setTile(i6 - 7, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                                    }
                                    if(j3 == 6 && field_22237_field_20341_topFloor == 1)
                                    {
                                        world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                    }
                                    continue;
                                }
                                if(j4 < 4 && j4 > -5)
                                {
                                    world.setTile(i6, k6, l6, 0);
                                }
                                continue;
                            }
                            if(i5 != -5 || j4 <= -5 || j4 >= 5)
                            {
                                continue;
                            }
                            if(j3 != 0 && j3 != 6 || j4 != -4 && j4 != 3)
                            {
                                if(j3 == 5 && (j4 == 3 || j4 == -4))
                                {
                                    world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                                } else
                                {
                                    world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                }
                            } else
                            {
                                world.setTile(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == -4 || i5 == -3 || i5 == 2 || i5 == 3)
                        {
                            if(j4 == -6 || j4 == 5)
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -6 || j4 >= 5)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                                continue;
                            }
                            if(world.getTileId(i6, k6, l6) != 54)
                            {
                                world.setTile(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 > -3 && i5 < 2)
                        {
                            if(j4 == -7 || j4 == 6)
                            {
                                if(j3 < 0 || j3 > 3 || j4 != -7 && j4 != 6 || i5 != -1 && i5 != 0)
                                {
                                    world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                } else
                                {
                                    world.setTile(i6, k6, l6, 0);
                                }
                                continue;
                            }
                            if(j4 <= -7 || j4 >= 6)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                            } else
                            {
                                world.setTile(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == 4)
                        {
                            if(j4 == -5 || j4 == 4)
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -5 || j4 >= 4)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                            } else
                            {
                                world.setTile(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == 5)
                        {
                            if(j4 == -4 || j4 == -3 || j4 == 2 || j4 == 3)
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -3 || j4 >= 2)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setTile(i6, k6, l6, BlockBase.DOUBLE_STONE_SLAB.id);
                            } else
                            {
                                world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                            }
                            continue;
                        }
                        if(i5 != 6 || j4 <= -3 || j4 >= 2)
                        {
                            continue;
                        }
                        if(j3 < 0 || j3 > 3 || j4 != -1 && j4 != 0)
                        {
                            world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                        } else
                        {
                            world.setTile(i6, k6, l6, func_22235_func_20337_cobbler(l2, random));
                        }
                    }

                }

            }

            if(field_22238_field_20340_floor == 2)
            {
                world.setTile(l + 3, k2, j1 - 5, func_22235_func_20337_cobbler(l2, random));
                world.setTile(l + 3, k2 - 1, j1 - 5, func_22235_func_20337_cobbler(l2, random));
            }
            if(field_22237_field_20341_topFloor == 1)
            {
                double d = l;
                double d1 = k2 + 6;
                double d2 = (double)j1 + 0.5D;
                EntityGolem entitygolem = new EntityGolem(world, l2);
                entitygolem.setPositionAndAngles(d, d1, d2, world.rand.nextFloat() * 360F, 0.0F);
                entitygolem.setPosition(d, d1, d2);
                world.spawnEntity(entitygolem);
            } else
            {
                world.setTile(l + 2, k2 + 6, j1 + 2, BlockBase.MOB_SPAWNER.id);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(l + 2, k2 + 6, j1 + 2);
                tileentitymobspawner.setEntityId(func_22234_b(random));
                world.setTile(l - 3, k2 + 6, j1 + 2, BlockBase.MOB_SPAWNER.id);
                TileEntityMobSpawner tileentitymobspawner1 = (TileEntityMobSpawner)world.getTileEntity(l - 3, k2 + 6, j1 + 2);
                tileentitymobspawner1.setEntityId(func_22234_b(random));
            }
            world.setTile(l, k2 + 6, j1 - 3, BlockBase.DOUBLE_STONE_SLAB.id);
            world.setTile(l - 1, k2 + 6, j1 - 3, BlockBase.DOUBLE_STONE_SLAB.id);
            if(k2 + 56 >= 120 && field_22238_field_20340_floor == 1)
            {
                field_22238_field_20340_floor = 2;
            }
            for(int k3 = 0; k3 < 2; k3++)
            {
                world.setTile(l - k3, k2 + 7, j1 - 3, 54);
                TileEntityChest tileentitychest = new TileEntityChest();
                world.setTileEntity(l - k3, k2 + 7, j1 - 3, tileentitychest);
                for(int j5 = 0; j5 < 1 + k3 + l2; j5++)
                {
                    ItemInstance itemstack = func_22233_func_20339_a(field_22238_field_20340_floor, random);
                    if(itemstack != null)
                    {
                        tileentitychest.setInventoryItem(random.nextInt(tileentitychest.getInventorySize()), itemstack);
                    }
                }

            }

            world.setTile(l + 3, k2, j1 - 6, BlockBase.TORCH.id);
            world.setTile(l - 4, k2, j1 - 6, BlockBase.TORCH.id);
            world.setTile(l + 1, k2, j1 - 4, BlockBase.TORCH.id);
            world.setTile(l - 2, k2, j1 - 4, BlockBase.TORCH.id);
            for(int l3 = 0; l3 < (field_22238_field_20340_floor * 4 + l2) - 8 && field_22237_field_20341_topFloor != 1; l3++)
            {
                int k4 = 5 - random.nextInt(12);
                int k5 = k2 + 5;
                int j6 = 5 - random.nextInt(10);
                if(j6 < -2 && k4 < 4 && k4 > -5 && k4 != 1 && k4 != -2)
                {
                    continue;
                }
                k4 += l;
                j6 += j1;
                if(world.getTileId(k4, k5, j6) == BlockBase.DOUBLE_STONE_SLAB.id && world.getTileId(k4, k5 + 1, j6) != BlockBase.MOB_SPAWNER.id)
                {
                    world.setTile(k4, k5, j6, 0);
                }
            }

            field_22238_field_20340_floor++;
        }

        return true;
    }

    private ItemInstance func_22233_func_20339_a(int i, Random random)
    {
        int j = random.nextInt(4);
        if(field_22237_field_20341_topFloor == 1)
        {
            if(j == 0)
            {
                return new ItemInstance(ItemBase.ironSword, 1);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.ironPickaxe, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(ItemBase.ironAxe, 1);
            }
            if(j == 3)
            {
                return new ItemInstance(ItemBase.ironShovel, 1);
            } else
            {
                return null;
            }
        }
        if(i == 1)
        {
            if(j == 0)
            {
                return new ItemInstance(ItemBase.stick, random.nextInt(5) + 2);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.seeds, random.nextInt(5) + 2);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.COBBLESTONE, random.nextInt(5) + 4);
            }
            if(j == 3)
            {
                return new ItemInstance(BlockBase.SAND, random.nextInt(5) + 4);
            } else
            {
                return null;
            }
        }
        if(i == 2)
        {
            if(j == 0)
            {
                return new ItemInstance(ItemBase.coal, random.nextInt(3) + 6);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.woodPickaxe, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.WOOD, random.nextInt(3) + 4);
            }
            if(j == 3)
            {
                return new ItemInstance(BlockBase.WOOL, random.nextInt(3) + 6);
            } else
            {
                return null;
            }
        }
        if(i == 3)
        {
            if(j == 0)
            {
                return new ItemInstance(ItemBase.feather, random.nextInt(3) + 6);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.bread, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.GLASS, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemInstance(BlockBase.BROWN_MUSHROOM, random.nextInt(3) + 3);
            } else
            {
                return null;
            }
        }
        if(i == 4)
        {
            if(j == 0)
            {
                return new ItemInstance(ItemBase.string, random.nextInt(3) + 2);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.stoneSword, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.TORCH, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemInstance(BlockBase.RED_MUSHROOM, random.nextInt(3) + 3);
            } else
            {
                return null;
            }
        }
        if(i == 5)
        {
            if(j == 0)
            {
                return new ItemInstance(BlockBase.WOOD_STAIRS, random.nextInt(3) + 5);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.bow, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.BRICKS, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemInstance(ItemBase.chainBoots, 1);
            } else
            {
                return null;
            }
        }
        if(i == 6)
        {
            if(j == 0)
            {
                return new ItemInstance(BlockBase.LADDER, random.nextInt(3) + 9);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.flintAndSteel, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.GLOWSTONE, random.nextInt(3) + 5);
            }
            if(j == 3)
            {
                return new ItemInstance(ItemBase.chainHelmet, 1);
            } else
            {
                return null;
            }
        }
        if(i == 7)
        {
            if(j == 0)
            {
                return new ItemInstance(BlockBase.JACK_O_LANTERN, random.nextInt(3) + 9);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.lavaBucket, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.RAIL, random.nextInt(5) + 9);
            }
            if(j == 3)
            {
                return new ItemInstance(ItemBase.chainLeggings, 1);
            } else
            {
                return null;
            }
        }
        if(i == 8)
        {
            if(j == 0)
            {
                return new ItemInstance(BlockBase.TNT, random.nextInt(3) + 3);
            }
            if(j == 1)
            {
                return new ItemInstance(ItemBase.diamondHoe, 1);
            }
            if(j == 2)
            {
                return new ItemInstance(BlockBase.OBSIDIAN, random.nextInt(3) + 6);
            }
            if(j == 3)
            {
                return new ItemInstance(ItemBase.chainChestplate, 1);
            } else
            {
                return null;
            }
        }
        if(random.nextInt(4) == 0)
        {
            return new ItemInstance(ItemBase.diamond, random.nextInt(3) + 3);
        }
        if(random.nextInt(4) == 1)
        {
            return new ItemInstance(ItemBase.ironIngot, random.nextInt(3) + 3);
        }
        if(random.nextInt(4) == 2)
        {
            return new ItemInstance(ItemBase.diamond, random.nextInt(4) + 6);
        }
        if(random.nextInt(4) == 3)
        {
            return new ItemInstance(ItemBase.ironIngot, random.nextInt(4) + 6);
        } else
        {
            return null;
        }
    }


    private String func_22234_b(Random random)
    {
        int i = random.nextInt(5);
        if(i == 0)
        {
            return "Skeleton";
        }
        if(i == 1)
        {
            return "Zombie";
        }
        if(i == 2)
        {
            return "Zombie";
        }
        if(i == 3)
        {
            return "Spider";
        }
        if(i == 4)
        {
            return "Zombie";
        } else
        {
            return "Spider";
        }
    }

    private int func_22235_func_20337_cobbler(int i, Random random)
    {
        if(i == 0)
        {
            return BlockBase.COBBLESTONE.id;
        }
        if(i == 1)
        {
            if(random.nextInt(3) == 0)
            {
                return BlockBase.COBBLESTONE.id;
            } else
            {
                return BlockBase.MOSSY_COBBLESTONE.id;
            }
        } else
        {
            return BlockBase.MOSSY_COBBLESTONE.id;
        }
    }

    private int field_22238_field_20340_floor;
    private int field_22237_field_20341_topFloor;

}

