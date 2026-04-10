package net.kozibrodka.battletower.gen;


import net.kozibrodka.battletower.entity.EntityGolem_old;
import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class GenerateTower_old extends Feature
{

    public GenerateTower_old()
    {

    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
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
            if(world.getBlockId(l1, i2, j2) != 0 && world.getBlockId(l1, i2, j2) != Block.SNOW.id || world.getBlockId(l1, i2 - 1, j2) == 0)
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
                    int l4 = world.getBlockId(l1 + i3, i2, j2 + i4);
                    if(l4 != 0 && l4 != Block.SNOW.id && l4 != PlantBlock.ROSE.id && l4 != PlantBlock.DANDELION.id && l4 != Block.LOG.id)
                    {
                        flag5 = true;
                    }
                    int l5 = world.getBlockId(l1 + i3, i2 - 1, j2 + i4);
                    if(l5 != Block.GRASS.id && l5 != Block.SAND.id && l5 != Block.STONE.id)
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
        tower_floor = 1;
        isTower_topFloor = 0;
        for(; k2 < 120; k2 += 7)
        {
            if(k2 + 7 >= 120)
            {
                isTower_topFloor = 1;
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
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                            }
                            continue;
                        }
                        if(i5 == -6 || i5 == -5)
                        {
                            if(j4 == -5 || j4 == 4)
                            {
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                continue;
                            }
                            if(i5 == -6)
                            {
                                if(j4 == (j3 + 1) % 7 - 3)
                                {
                                    world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                                    if(j3 == 5)
                                    {
                                        world.setBlock(i6 - 7, k6, l6, Block.DOUBLE_SLAB.id);
                                    }
                                    if(j3 == 6 && isTower_topFloor == 1)
                                    {
                                        world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                    }
                                    continue;
                                }
                                if(j4 < 4 && j4 > -5)
                                {
                                    world.setBlock(i6, k6, l6, 0);
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
                                    world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                                } else
                                {
                                    world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                }
                            } else
                            {
                                world.setBlock(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == -4 || i5 == -3 || i5 == 2 || i5 == 3)
                        {
                            if(j4 == -6 || j4 == 5)
                            {
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -6 || j4 >= 5)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                                continue;
                            }
                            if(world.getBlockId(i6, k6, l6) != 54)
                            {
                                world.setBlock(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 > -3 && i5 < 2)
                        {
                            if(j4 == -7 || j4 == 6)
                            {
                                if(j3 < 0 || j3 > 3 || j4 != -7 && j4 != 6 || i5 != -1 && i5 != 0)
                                {
                                    world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                } else
                                {
                                    world.setBlock(i6, k6, l6, 0);
                                }
                                continue;
                            }
                            if(j4 <= -7 || j4 >= 6)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                            } else
                            {
                                world.setBlock(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == 4)
                        {
                            if(j4 == -5 || j4 == 4)
                            {
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -5 || j4 >= 4)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                            } else
                            {
                                world.setBlock(i6, k6, l6, 0);
                            }
                            continue;
                        }
                        if(i5 == 5)
                        {
                            if(j4 == -4 || j4 == -3 || j4 == 2 || j4 == 3)
                            {
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                                continue;
                            }
                            if(j4 <= -3 || j4 >= 2)
                            {
                                continue;
                            }
                            if(j3 == 5)
                            {
                                world.setBlock(i6, k6, l6, Block.DOUBLE_SLAB.id);
                            } else
                            {
                                world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                            }
                            continue;
                        }
                        if(i5 != 6 || j4 <= -3 || j4 >= 2)
                        {
                            continue;
                        }
                        if(j3 < 0 || j3 > 3 || j4 != -1 && j4 != 0)
                        {
                            world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                        } else
                        {
                            world.setBlock(i6, k6, l6, towerBuilder_cobbler(l2, random));
                        }
                    }

                }

            }

            if(tower_floor == 2)
            {
                world.setBlock(l + 3, k2, j1 - 5, towerBuilder_cobbler(l2, random));
                world.setBlock(l + 3, k2 - 1, j1 - 5, towerBuilder_cobbler(l2, random));
            }
            if(isTower_topFloor == 1)
            {
                double d = l;
                double d1 = k2 + 6;
                double d2 = (double)j1 + 0.5D;
                EntityGolem_old entitygolem = new EntityGolem_old(world, l2);
                entitygolem.setPositionAndAngles(d, d1, d2, world.random.nextFloat() * 360F, 0.0F);
                entitygolem.setPosition(d, d1, d2);
                world.spawnEntity(entitygolem);
            } else
            {
                world.setBlock(l + 2, k2 + 6, j1 + 2, Block.SPAWNER.id);
                MobSpawnerBlockEntity tileentitymobspawner = (MobSpawnerBlockEntity)world.getBlockEntity(l + 2, k2 + 6, j1 + 2);
                tileentitymobspawner.setSpawnedEntityId(MobTable.getTowerMobType(random));
                world.setBlock(l - 3, k2 + 6, j1 + 2, Block.SPAWNER.id);
                MobSpawnerBlockEntity tileentitymobspawner1 = (MobSpawnerBlockEntity)world.getBlockEntity(l - 3, k2 + 6, j1 + 2);
                tileentitymobspawner1.setSpawnedEntityId(MobTable.getTowerMobType(random));
            }
            world.setBlock(l, k2 + 6, j1 - 3, Block.DOUBLE_SLAB.id);
            world.setBlock(l - 1, k2 + 6, j1 - 3, Block.DOUBLE_SLAB.id);
            if(k2 + 56 >= 120 && tower_floor == 1)
            {
                tower_floor = 2;
            }
            for(int k3 = 0; k3 < 2; k3++)
            {
                world.setBlock(l - k3, k2 + 7, j1 - 3, 54);
                ChestBlockEntity tileentitychest = new ChestBlockEntity();
                world.setBlockEntity(l - k3, k2 + 7, j1 - 3, tileentitychest);
                for(int j5 = 0; j5 < 1 + k3 + l2; j5++)
                {
//                    ItemStack itemstack = getRandomTowerItem(tower_floor, random);
                    ItemStack itemstack = LootTable.getRandomChestItem_old(tower_floor, random, isTower_topFloor);
                    if(itemstack != null)
                    {
                        tileentitychest.setStack(random.nextInt(tileentitychest.size()), itemstack);
                    }
                }

            }

            world.setBlock(l + 3, k2, j1 - 6, Block.TORCH.id);
            world.setBlock(l - 4, k2, j1 - 6, Block.TORCH.id);
            world.setBlock(l + 1, k2, j1 - 4, Block.TORCH.id);
            world.setBlock(l - 2, k2, j1 - 4, Block.TORCH.id);
            for(int l3 = 0; l3 < (tower_floor * 4 + l2) - 8 && isTower_topFloor != 1; l3++)
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
                if(world.getBlockId(k4, k5, j6) == Block.DOUBLE_SLAB.id && world.getBlockId(k4, k5 + 1, j6) != Block.SPAWNER.id)
                {
                    world.setBlock(k4, k5, j6, 0);
                }
            }

            tower_floor++;
        }

        return true;
    }

    private int towerBuilder_cobbler(int i, Random random)
    {
        if(i == 0)
        {
            return Block.COBBLESTONE.id;
        }
        if(i == 1)
        {
            if(random.nextInt(3) == 0)
            {
                return Block.COBBLESTONE.id;
            } else
            {
                return Block.MOSSY_COBBLESTONE.id;
            }
        } else
        {
            return Block.MOSSY_COBBLESTONE.id;
        }
    }

    private int tower_floor;
    private int isTower_topFloor;

}

