package net.kozibrodka.battletower.gen;

import net.kozibrodka.battletower.events.DestroyerSystem;
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
		this.world = worldObj;
		this.player = golemkiller;
		this.x = coords.x;
		this.y = coords.y;
		this.z = coords.z;
		this.triggerTime = time;
		this.lastExplosionSoundTime = time;
		this.world.playSound((double)this.x, (double)this.y, (double)this.z, "battletower:towerbreakstart", 4.0F, 1.0F);
	}

	public void Update() {
		if(this.floor == 6 && System.currentTimeMillis() > this.triggerTime + 30000L) {
			this.triggerTime = System.currentTimeMillis();
			if(!this.world.isRemote) {
				this.world.createExplosion(this.player, (double)this.x, this.yCoord(), (double)this.z, 10.0F);
				this.cleanUpStragglerBlocks();
			}

			--this.floor;
		} else if(this.floor < 6 && System.currentTimeMillis() > this.triggerTime + 10000L) {
			if(this.floor < -1) { /// ZMIANA < 1 oryg, czyli dodałem 2 piętra, rczej zostaw 0 //TODO kondycja : CHECK FOR GROUND!!!!
				DestroyerSystem.unRegisterTowerDestroyer(this);
				return;
			}

			this.triggerTime = System.currentTimeMillis();
			if(!this.world.isRemote) {
				this.world.createExplosion(this.player, (double)this.x, this.yCoord(), (double)this.z, 10.0F);
				this.cleanUpStragglerBlocks();
			}

			--this.floor;
		} else {
			this.createSFX(this.randomTowerCoord(this.x), (int)this.yCoord(), this.randomTowerCoord(this.z));
		}

	}

	private double yCoord() {
		return (double)(this.y - 7 * Math.abs(6 - this.floor));
	}

	private int randomTowerCoord(int i) {
		return i - 7 + this.world.random.nextInt(15);
	}

	private void cleanUpStragglerBlocks() {
		int ytemp = (int)this.yCoord();

		for(int xIterator = -8; xIterator < 8; ++xIterator) {
			for(int zIterator = -8; zIterator < 8; ++zIterator) {
				for(int yIterator = 1; yIterator < 9; ++yIterator) {
					if(this.world.getBlockId(this.x + xIterator, ytemp + yIterator, this.z + zIterator) != 0) {
						this.world.setBlock(this.x + xIterator, ytemp + yIterator, this.z + zIterator, 0);
					}
				}
			}
		}

	}

	private void createSFX(int i, int j, int k) {
		if(System.currentTimeMillis() > this.lastExplosionSoundTime + 4000L) {
			switch(this.world.random.nextInt(4)) {
			case 0:
				this.world.playSound((double)i, (double)j, (double)k, "random.fizz", 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
			case 1:
				this.world.playSound((double)i, (double)j, (double)k, "battletower:towercrumble", 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
			default:
				this.lastExplosionSoundTime = System.currentTimeMillis();
			}
		}

		double d = (double)((float)i + this.world.random.nextFloat());
		double d1 = (double)((float)j + this.world.random.nextFloat());
		double d2 = (double)((float)k + this.world.random.nextFloat());
		double d3 = d - (double)i;
		double d4 = d1 - (double)j;
		double d5 = d2 - (double)k;
		double d6 = (double) MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
		d3 /= d6;
		d4 /= d6;
		d5 /= d6;
		double d7 = 0.5D / (d6 / 1.0D + 0.1D);
		d7 *= (double)(this.world.random.nextFloat() * this.world.random.nextFloat() + 0.3F);
		d3 *= d7;
		d4 *= d7;
		d5 *= d7;
		switch(this.world.random.nextInt(4)) {
		case 0:
			this.world.addParticle("explode", (d + (double)i * 1.0D) / 2.0D, (d1 + (double)j * 1.0D) / 2.0D, (d2 + (double)k * 1.0D) / 2.0D, d3, d4, d5);
		case 1:
			this.world.addParticle("smoke", d, d1, d2, d3, d4, d5);
		case 2:
			this.world.addParticle("lava", d, d1, d2, 0.0D, 0.0D, 0.0D);
		case 4:
			this.world.addParticle("largesmoke", (double)i + Math.random(), (double)j + 1.2D, (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
		case 3:
		default:
		}
	}
}
