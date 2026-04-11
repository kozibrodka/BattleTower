package net.kozibrodka.battletower.gen;

import net.kozibrodka.battletower.entity.EntityGolem;
import net.kozibrodka.battletower.entity.EntityGolem_old;
import net.kozibrodka.battletower.events.GeneratorStarter;
import net.minecraft.block.Block;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Arrays;
import java.util.Random;

public class WorldGenTower extends Feature {
	private int floor;
	private int floorIterator;
	private boolean topFloor;
	private int[][] candidates = new int[][]{{4, -5}, {4, 0}, {4, 5}, {0, -5}, {0, 0}, {0, 5}, {-4, -5}, {-4, 0}, {-4, 5}};
	private int candidatecount = candidates.length;
	final int maxHoleDepthInBase = 22;
	private towerTypes towerChosen;
	Random random2 = new Random();


	public void chooseClassicTower(){
		int type = random2.nextInt(3);
		if(type == 2){
			towerChosen = towerTypes.CobbleStoneMossy;
		}else if(type == 1){
			towerChosen = towerTypes.SmoothStone;
		}else{
			towerChosen = towerTypes.CobbleStone;
		}
	}


	@Override
    public boolean generate(World world, Random random, int i, int j, int k) {
		int centerblockY = j;
//		int countWater = 0;
		int countSand = 0;
		int countSnow = 0;
		int countElse = 0;

		int towerWallBlockID;
		int towerLightBlockID;
		int towerFloorBlockID;
		for(int nums = 0; nums < candidatecount; ++nums) {
			int[] result = candidates[nums];
			towerWallBlockID = GetSurfaceBlockHeight(world, i + result[0], k + result[1]);
			towerLightBlockID = world.getBlockId(i + result[0], towerWallBlockID, k + result[1]);
			if(world.getBlockId(i + result[0], towerWallBlockID + 1, k + result[1]) != Block.SNOW.id && towerLightBlockID != Block.ICE.id) {
				if(towerLightBlockID != Block.SAND.id && towerLightBlockID != Block.SANDSTONE.id) {
					if(towerLightBlockID == Block.WATER.id) {
//						++countWater;
					} else {
						++countElse;
					}
				} else {
					++countSand;
				}
			} else {
				++countSnow;
			}

			if(Math.abs(towerWallBlockID - centerblockY) > 22) {
				return false;
			}

			for(towerFloorBlockID = 1; towerFloorBlockID <= 3; ++towerFloorBlockID) {
				towerLightBlockID = world.getBlockId(i + result[0], towerWallBlockID + towerFloorBlockID, k + result[1]);
				if(IsBannedBlockID(towerLightBlockID)) {
					return false;
				}
			}

			for(towerFloorBlockID = 1; towerFloorBlockID <= 5; ++towerFloorBlockID) {
				towerLightBlockID = world.getBlockId(i + result[0], towerWallBlockID - towerFloorBlockID, k + result[1]);
				if(towerLightBlockID == 0 || IsBannedBlockID(towerLightBlockID)) {
					return false;
				}
			}
		}

		int[] var24 = new int[]{countSnow, countSand, countElse};
		Arrays.sort(var24);
		int var25 = var24[var24.length - 1];
		int biomRand = random.nextInt(10);
		if(countSand == var25) {
			if(biomRand > 5){
				towerChosen = towerTypes.SandStone;
			}else{
				chooseClassicTower();
			}
		} else if(countSnow == var25) {
			if(biomRand < 3){
				towerChosen = towerTypes.Snow;
			}else if(biomRand < 6){
				towerChosen = towerTypes.Ice;
			}else{
				chooseClassicTower();
			}
		} else {
			chooseClassicTower();
		}

//		towerWallBlockID = towerChosen.GetWallBlockID();
		towerLightBlockID = towerChosen.GetLightBlockID();
		towerFloorBlockID = towerChosen.GetFloorBlockID();
		floor = 1;
		topFloor = false;

		for(int builderHeight = j - 6; builderHeight < 120; builderHeight += 7) {
			if(builderHeight + 7 >= 120) {
				topFloor = true;
			}

			int l3;
			int k4;
			int k5;
			int j6;
			for(floorIterator = 0; floorIterator < 7; ++floorIterator) {
				if(floor == 1 && floorIterator < 4) {
					floorIterator = 4;
				}

				for(l3 = -7; l3 < 7; ++l3) {
					for(k4 = -7; k4 < 7; ++k4) {
						k5 = l3 + i;
						j6 = floorIterator + builderHeight;
						int d2 = k4 + k;
						if(k4 == -7) {
							if(l3 > -5 && l3 < 4) {
								BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
							}
						} else if(k4 != -6 && k4 != -5) {
							if(k4 != -4 && k4 != -3 && k4 != 2 && k4 != 3) {
								if(k4 > -3 && k4 < 2) {
									if(l3 != -7 && l3 != 6) {
										if(l3 > -7 && l3 < 6) {
											if(floorIterator == 5) {
												BuildFloorPiece(world, k5, j6, d2, towerFloorBlockID);
											} else {
												world.setBlock(k5, j6, d2, 0);
											}
										}
									} else if(floorIterator >= 0 && floorIterator <= 3 && (l3 == -7 || l3 == 6) && (k4 == -1 || k4 == 0)) {
										world.setBlock(k5, j6, d2, 0);
									} else {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									}
								} else if(k4 == 4) {
									if(l3 != -5 && l3 != 4) {
										if(l3 > -5 && l3 < 4) {
											if(floorIterator == 5) {
												BuildFloorPiece(world, k5, j6, d2, towerFloorBlockID);
											} else {
												world.setBlock(k5, j6, d2, 0);
											}
										}
									} else {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									}
								} else if(k4 == 5) {
									if(l3 != -4 && l3 != -3 && l3 != 2 && l3 != 3) {
										if(l3 > -3 && l3 < 2) {
											if(floorIterator == 5) {
												BuildFloorPiece(world, k5, j6, d2, towerFloorBlockID);
											} else {
												BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
											}
										}
									} else {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									}
								} else if(k4 == 6 && l3 > -3 && l3 < 2) {
									if(floorIterator >= 0 && floorIterator <= 3 && (l3 == -1 || l3 == 0)) {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									} else {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									}
								}
							} else if(l3 != -6 && l3 != 5) {
								if(l3 > -6 && l3 < 5) {
									if(floorIterator == 5) {
										BuildFloorPiece(world, k5, j6, d2, towerFloorBlockID);
									} else if(world.getBlockId(k5, j6, d2) != Block.CHEST.id) {
										world.setBlock(k5, j6, d2, 0);
									}
								}
							} else {
								BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
							}
						} else if(l3 != -5 && l3 != 4) {
							if(k4 == -6) {
								if(l3 == (floorIterator + 1) % 7 - 3) {
									world.setBlock(k5, j6, d2, towerChosen.GetStairBlockID());
									if(floorIterator == 5) {
										world.setBlock(k5 - 7, j6, d2, towerFloorBlockID);
									}

									if(floorIterator == 6 && topFloor) {
										BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
									}
								} else if(l3 < 4 && l3 > -5) {
									world.setBlock(k5, j6, d2, 0);
								}
							} else if(k4 == -5 && l3 > -5 && l3 < 5) {
								if((floorIterator == 0 || floorIterator == 6) && (l3 == -4 || l3 == 3)) {
									world.setBlock(k5, j6, d2, 0);
								} else if(floorIterator == 5 && (l3 == 3 || l3 == -4)) {
									BuildFloorPiece(world, k5, j6, d2, towerFloorBlockID);
								} else {
									BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
								}
							}
						} else {
							BuildWallPiece(world, k5, j6, d2, towerChosen.GetWallBlockID());
						}
					}
				}
			}

			if(floor == 2) {
				world.setBlock(i + 3, builderHeight, k - 5, towerChosen.GetWallBlockID());
				world.setBlock(i + 3, builderHeight - 1, k - 5, towerChosen.GetWallBlockID());
			}

			if(topFloor) {
				double var26 = (double)i;
				double var30 = (double)(builderHeight + 6);
				double var32 = (double)k + 0.5D;
				EntityGolem entitygolem = new EntityGolem(world, towerChosen.ordinal());
				entitygolem.setPositionAndAngles(var26, var30, var32, world.random.nextFloat() * 360.0F, 0.0F);
				entitygolem.setPosition(var26, var30, var32);
				world.spawnEntity(entitygolem);
				/// OLD vs NEW
//				EntityGolem_old entitygolem = new EntityGolem_old(world, towerChosen.ordinal());
//				entitygolem.setPositionAndAngles(var26, var30, var32, world.random.nextFloat() * 360F, 0.0F);
//				entitygolem.setPosition(var26, var30, var32);
//				world.spawnEntity(entitygolem);
			} else {
				world.setBlock(i + 2, builderHeight + 6, k + 2, Block.SPAWNER.id);
				MobSpawnerBlockEntity var27 = (MobSpawnerBlockEntity)world.getBlockEntity(i + 2, builderHeight + 6, k + 2);
				var27.setSpawnedEntityId(MobTable.getTowerMobType(random));
				world.setBlock(i - 3, builderHeight + 6, k + 2, Block.SPAWNER.id);
				MobSpawnerBlockEntity var28 = (MobSpawnerBlockEntity)world.getBlockEntity(i - 3, builderHeight + 6, k + 2);
				var28.setSpawnedEntityId(MobTable.getTowerMobType(random));
			}

			world.setBlock(i, builderHeight + 6, k - 3, towerFloorBlockID);
			world.setBlock(i - 1, builderHeight + 6, k - 3, towerFloorBlockID);
			if(builderHeight + 56 >= 120 && floor == 1) {
				floor = 2;
			}
			for(l3 = 0; l3 < 2; ++l3) {
				world.setBlock(i - l3, builderHeight + 7, k - 3, Block.CHEST.id);
				ChestBlockEntity var29 = new ChestBlockEntity();
				world.setBlockEntity(i - l3, builderHeight + 7, k - 3, var29);

				for(k5 = 0; k5 < GeneratorStarter.config.loot_rarity; ++k5) {
					ItemStack var31;
					if(GeneratorStarter.config.loot_rework){
						var31 = LootTable.getRandomChestItem_new(floor, random, topFloor?1:0); /// REWORKED LOOT (kozi - 2026)
					}else{
						var31 = LootTable.getRandomChestItem_old(floor, random, topFloor?1:0); /// OLD loot (2010)
					}
					if(var31 != null) {
						var29.setStack(random.nextInt(var29.size()), var31);
					}
				}
			}

			if(towerLightBlockID != 0 && Block.BLOCKS[towerLightBlockID].isOpaque()) {
				builderHeight += 2;
			}

			world.setBlock(i + 3, builderHeight, k - 6, towerLightBlockID);
			world.setBlock(i - 4, builderHeight, k - 6, towerLightBlockID);
			world.setBlock(i + 1, builderHeight, k - 4, towerLightBlockID);
			world.setBlock(i - 2, builderHeight, k - 4, towerLightBlockID);
			if(towerLightBlockID != 0 && Block.BLOCKS[towerLightBlockID].isOpaque()) {
				builderHeight -= 2;
			}

			for(l3 = 0; l3 < floor * 4 + towerChosen.ordinal() - 8 && !topFloor; ++l3) {
				k4 = 5 - random.nextInt(12);
				k5 = builderHeight + 5;
				j6 = 5 - random.nextInt(10);
				if(j6 >= -2 || k4 >= 4 || k4 <= -5 || k4 == 1 || k4 == -2) {
					k4 += i;
					j6 += k;
					if(world.getBlockId(k4, k5, j6) == towerFloorBlockID && world.getBlockId(k4, k5 + 1, j6) != Block.SPAWNER.id) {
						world.setBlock(k4, k5, j6, 0);
					}
				}
			}

			++floor;
		}

		return true;
	}

	private void BuildFloorPiece(World world, int i, int j, int k, int towerFloorBlockID) {
		world.setBlock(i, j, k, towerFloorBlockID);
		if(towerChosen.GetFloorBlockMetaData() != 0) {
			world.setBlockMeta(i, j, k, towerChosen.GetFloorBlockMetaData());
		}

	}

	private void BuildWallPiece(World world, int i, int j, int k, int towerWallBlockID) {
		world.setBlock(i, j, k, towerWallBlockID);
		if(floor == 1 && floorIterator == 4) {
			FillTowerBaseToGround(world, i, j, k, towerWallBlockID);
		}

	}

	private void FillTowerBaseToGround(World world, int i, int j, int k, int blocktype) {
		int x = j - 1;

		do {
			world.setBlock(i, x, k, blocktype);
			--x;
		} while(!IsBuildableBlockID(world.getBlockId(i, x, k)));

	}

	private int GetSurfaceBlockHeight(World world, int x, int z) {
		int h = 50;

		do {
			++h;
		} while(world.getBlockId(x, h, z) != 0 && !IsFoliageBlockID(world.getBlockId(x, h, z)));

		return h - 1;
	}

	private boolean IsFoliageBlockID(int ID) {
		return ID == Block.SNOW.id || ID == Block.GRASS.id || ID == Block.DEAD_BUSH.id || ID == Block.LOG.id || ID == Block.LEAVES.id;
	}

	private boolean IsBuildableBlockID(int ID) {
		return ID == Block.STONE.id || ID == Block.GRASS_BLOCK.id || ID == Block.SAND.id || ID == Block.SANDSTONE.id || ID == Block.GRAVEL.id || ID == Block.DIRT.id;
		/// RARE issue - Wieża będzie mocno wkopana w ziemie. Musiało by być bardzo specyficzne miejsce i brak żadnej rudy węgla itd. chyba
	}

	private boolean IsBannedBlockID(int ID) {
		return ID == Block.DANDELION.id || ID == Block.ROSE.id || ID == Block.BROWN_MUSHROOM.id || ID == Block.RED_MUSHROOM.id || ID == Block.PUMPKIN.id || ID == Block.FLOWING_LAVA.id || ID == Block.LAVA.id || ID == Block.FLOWING_WATER.id || ID == Block.WATER.id;
	/// minus kaktus || ID == Block.CACTUS.id
	}

	public static enum towerTypes {
		CobbleStone(Block.COBBLESTONE.id, Block.TORCH.id, Block.DOUBLE_SLAB.id, 0, Block.COBBLESTONE_STAIRS.id),
		SmoothStone(Block.STONE.id, Block.TORCH.id, Block.DOUBLE_SLAB.id, 0, Block.COBBLESTONE_STAIRS.id),
		CobbleStoneMossy(Block.MOSSY_COBBLESTONE.id, Block.TORCH.id, Block.DOUBLE_SLAB.id, 0, Block.COBBLESTONE_STAIRS.id),
		SandStone(Block.SANDSTONE.id, Block.TORCH.id, Block.DOUBLE_SLAB.id, 1, Block.WOODEN_STAIRS.id),
		Ice(Block.ICE.id, 0, Block.SNOW_BLOCK.id, 0, Block.COBBLESTONE_STAIRS.id),
		Snow(Block.SNOW_BLOCK.id, 0, Block.ICE.id, 0, Block.COBBLESTONE_STAIRS.id),
		Netherrack(Block.NETHERRACK.id, Block.GLOWSTONE.id, Block.SOUL_SAND.id, 0, Block.COBBLESTONE_STAIRS.id);


		private int wallBlockID;
		private int lightBlockID;
		private int floorBlockID;
		private int floorBlockMetaData;
		private int stairBlockID;

		private towerTypes(int a, int b, int c, int d, int e) {
			wallBlockID = a;
			lightBlockID = b;
			floorBlockID = c;
			floorBlockMetaData = d;
			stairBlockID = e;
		}

		Random random1 = new Random();

		int GetWallBlockID() {
			if(wallBlockID == Block.STONE.id){

				if(random1.nextInt(3) == 0)
				{
					return Block.COBBLESTONE.id;
				} else
				{
					return Block.MOSSY_COBBLESTONE.id;
				}

			}
			return wallBlockID;
		}

		int GetLightBlockID() {
			return lightBlockID;
		}

		int GetFloorBlockID() {
			return floorBlockID;
		}

		int GetFloorBlockMetaData() {
			return floorBlockMetaData;
		}

		int GetStairBlockID() {
			return stairBlockID;
		}
	}
}
