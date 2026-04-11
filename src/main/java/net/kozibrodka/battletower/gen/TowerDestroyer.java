package net.kozibrodka.battletower.gen;

import net.kozibrodka.battletower.events.DestroyerSystem;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class TowerDestroyer {
	private int x;
	private int y;
	private int z;
	private World world;
	private Entity player;
	private long triggerTime;
	private long lastExplosionSoundTime;
	private final int maxfloor = 6;
	private int floor = 6;
	private final int floorDistance = 7;
	private final float explosionPower = 10.0F;

	public TowerDestroyer(World worldObj, Vec3i coords, long time, Entity golemkiller) {
		world = worldObj;
		player = golemkiller;
		x = coords.x;
		y = coords.y;
		z = coords.z;
		triggerTime = time;
		lastExplosionSoundTime = time;
		world.playSound((double)x, (double)y, (double)z, "battletower:towerbreakstart", 4.0F, 1.0F);
	}
	
	public boolean shoudStopCollapsing(){
		int offY = 5; /// Do samego dołu, Dla CBBL trochę za dużo zostaje, dla SAND trochę za mało...
//		int offY = 13; /// Zostawia ruiny - bardziej "eco"
		int block1 = world.getBlockId(x, (int)yCoord() - offY, z);
		int block2 = world.getBlockId(x + 5, (int)yCoord() - offY, z);
		int block3 = world.getBlockId(x - 5, (int)yCoord() - offY, z);
		int block4 = world.getBlockId(x, (int)yCoord() - offY, z + 6);
		int block5 = world.getBlockId(x, (int)yCoord() - offY, z - 5);

//		System.out.println(block1 + " " + block2 + " " + block3 + " " + block4 + " " + block5 + " " );

		int count = 0;
		if(isGroundBlock(block1)){
			count++;
		}
		if(isGroundBlock(block2)){
			count++;
		}
		if(isGroundBlock(block3)){
			count++;
		}
		if(isGroundBlock(block4)){
			count++;
		}
		if(isGroundBlock(block5)){
			count++;
		}

		System.out.println(count);
		if(count > 2){
			System.out.println("STOP");
			return true;
		}else{
			System.out.println("CONTINUE");
			return false;
		}


//		System.out.println("ID: " + block1);
//		System.out.println(x + " " + (int)yCoord() + " " + z);
	}

	public boolean isGroundBlock(int blockID){
		return blockID == 1 || blockID == 3 || blockID == 12 || blockID == 13 || blockID == 24;
		/// STONE, DIRT, SAND, GRAVEL, SANDSTONE
	}

	public void Update() {
		if(floor == 6 && System.currentTimeMillis() > triggerTime + 30000L) {
			triggerTime = System.currentTimeMillis();
			if(!world.isRemote) {
				world.createExplosion(player, (double)x, yCoord(), (double)z, 10.0F);
				cleanUpStragglerBlocks();
			}

			--floor;
		} else if(floor < 6 && System.currentTimeMillis() > triggerTime + 10000L) {
			if(floor < -2 || shoudStopCollapsing()) { /// ZMIANA < 1 oryg
				DestroyerSystem.unRegisterTowerDestroyer(this);
				return;
			}

			triggerTime = System.currentTimeMillis();
			if(!world.isRemote) {
				world.createExplosion(player, (double)x, yCoord(), (double)z, 10.0F);
				cleanUpStragglerBlocks();
			}

			--floor;
		} else {
			createSFX(randomTowerCoord(x), (int)yCoord(), randomTowerCoord(z));
		}

	}

	private double yCoord() {
		return (double)(y - 7 * Math.abs(6 - floor));
	}

	private int randomTowerCoord(int i) {
		return i - 7 + world.random.nextInt(15);
	}

	private void cleanUpStragglerBlocks() {
		int ytemp = (int)yCoord();

		for(int xIterator = -8; xIterator < 8; ++xIterator) {
			for(int zIterator = -8; zIterator < 8; ++zIterator) {
				for(int yIterator = 1; yIterator < 9; ++yIterator) {
					if(world.getBlockId(x + xIterator, ytemp + yIterator, z + zIterator) != 0) {
						world.setBlock(x + xIterator, ytemp + yIterator, z + zIterator, 0);
					}
				}
			}
		}

	}

	private void createSFX(int i, int j, int k) {
		if(System.currentTimeMillis() > lastExplosionSoundTime + 4000L) {
			switch(world.random.nextInt(4)) {
			case 0:
				world.playSound((double)i, (double)j, (double)k, "random.fizz", 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
			case 1:
				world.playSound((double)i, (double)j, (double)k, "battletower:towercrumble", 4.0F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
			default:
				lastExplosionSoundTime = System.currentTimeMillis();
			}
		}

		double d = (double)((float)i + world.random.nextFloat());
		double d1 = (double)((float)j + world.random.nextFloat());
		double d2 = (double)((float)k + world.random.nextFloat());
		double d3 = d - (double)i;
		double d4 = d1 - (double)j;
		double d5 = d2 - (double)k;
		double d6 = (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
		d3 /= d6;
		d4 /= d6;
		d5 /= d6;
		double d7 = 0.5D / (d6 / 1.0D + 0.1D);
		d7 *= (double)(world.random.nextFloat() * world.random.nextFloat() + 0.3F);
		d3 *= d7;
		d4 *= d7;
		d5 *= d7;
		switch(world.random.nextInt(4)) {
		case 0:
			world.addParticle("explode", (d + (double)i * 1.0D) / 2.0D, (d1 + (double)j * 1.0D) / 2.0D, (d2 + (double)k * 1.0D) / 2.0D, d3, d4, d5);
		case 1:
			world.addParticle("smoke", d, d1, d2, d3, d4, d5);
		case 2:
			world.addParticle("lava", d, d1, d2, 0.0D, 0.0D, 0.0D);
		case 4:
			world.addParticle("largesmoke", (double)i + Math.random(), (double)j + 1.2D, (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
		case 3:
		default:
		}
	}
}
