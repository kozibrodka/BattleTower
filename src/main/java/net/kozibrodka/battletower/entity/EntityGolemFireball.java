package net.kozibrodka.battletower.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.battletower.events.GolemListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.EntitySpawnDataProvider;
import net.modificationstation.stationapi.api.server.entity.HasTrackingParameters;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.TriState;

import java.util.List;

@HasTrackingParameters(trackingDistance = 60, updatePeriod = 1, sendVelocity = TriState.TRUE)
public class EntityGolemFireball extends Entity implements EntitySpawnDataProvider {
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile = 0;
	private boolean wasDeflected;
	private boolean inGround = false;
	public int shake = 0;
	public LivingEntity shooterEntity;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	public boolean receivedP;

	public EntityGolemFireball(World world) {
		super(world);
		this.setBoundingBoxSpacing(0.3F, 0.3F);
		this.wasDeflected = false;
	}

	@Override
    protected void initDataTracker() {
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void setPositionAndAnglesAvoidEntities(double x, double y, double z, float pitch, float yaw, int interpolationSteps) {
		if(!receivedP) {
			this.setPosition(x, y, z);
			this.setRotation(pitch, yaw);
			receivedP = true;
		}
	}

	@Override
    public boolean shouldRender(double d) {
		double d1 = this.boundingBox.getAverageSideLength() * 4.0D;
		d1 *= 64.0D;
		return d < d1 * d1;
	}

	public EntityGolemFireball(World world, double diffX, double diffY, double diffZ) {
		super(world);
		this.setBoundingBoxSpacing(0.3F, 0.3F);
		this.standingEyeHeight = 0.0F;
		this.velocityX = this.velocityY = this.velocityZ = 0.0D;
		diffX += this.random.nextGaussian() * 0.4D;
		diffY += this.random.nextGaussian() * 0.4D;
		diffZ += this.random.nextGaussian() * 0.4D;
		double targetDistance = (double) MathHelper.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
		this.accelerationX = diffX / targetDistance * 0.1D;
		this.accelerationY = diffY / targetDistance * 0.1D;
		this.accelerationZ = diffZ / targetDistance * 0.1D;
		this.wasDeflected = false;
	}

	public EntityGolemFireball(World world, LivingEntity entityliving, double diffX, double diffY, double diffZ) {
		super(world);
		this.shooterEntity = entityliving;
		this.setBoundingBoxSpacing(0.3F, 0.3F);
		this.setPosition(this.x, this.y, this.z);
		this.standingEyeHeight = 0.0F;
		this.velocityX = this.velocityY = this.velocityZ = 0.0D;
		diffX += this.random.nextGaussian() * 0.4D;
		diffY += this.random.nextGaussian() * 0.4D;
		diffZ += this.random.nextGaussian() * 0.4D;
		double targetDistance = (double)MathHelper.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
		this.accelerationX = diffX / targetDistance * 0.1D;
		this.accelerationY = diffY / targetDistance * 0.1D;
		this.accelerationZ = diffZ / targetDistance * 0.1D;
		this.wasDeflected = false;
	}

	@Override
    public void tick() {
		super.tick();
		this.fireTicks = 10;
		if(this.shake > 0) {
			--this.shake;
		}

		if(this.inGround) {
			int vec3d = this.world.getBlockId(this.xTile, this.yTile, this.zTile);
			if(vec3d == this.inTile) {
				if(this.age >= 1200) {
					this.markDead();
				}
				return;
			}

			this.inGround = false;
			this.velocityX *= (double)(this.random.nextFloat() * 0.2F);
			this.velocityY *= (double)(this.random.nextFloat() * 0.2F);
			this.velocityZ *= (double)(this.random.nextFloat() * 0.2F);
		}

		Vec3d var15 = Vec3d.createCached(this.x, this.y, this.z);
		Vec3d vec3d1 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
		HitResult movingobjectposition = this.world.raycast(var15, vec3d1);
		var15 = Vec3d.createCached(this.x, this.y, this.z);
		vec3d1 = Vec3d.createCached(this.x + this.velocityX, this.y + this.velocityY, this.z + this.velocityZ);
		if(movingobjectposition != null) {
			vec3d1 = Vec3d.createCached(movingobjectposition.pos.x, movingobjectposition.pos.y, movingobjectposition.pos.z);
		}

		Entity entity = null;
		List list = this.world.getEntities(this, this.boundingBox.stretch(this.velocityX, this.velocityY, this.velocityZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;

		for(int f = 0; f < list.size(); ++f) {
			Entity f1 = (Entity)list.get(f);
			if(f1.isCollidable() && (f1 != this.shooterEntity || this.age >= 25 || this.wasDeflected)) {
				float k = 0.3F;
				Box f3 = f1.boundingBox.expand(k, k, k);
				HitResult movingobjectposition1 = f3.raycast(var15, vec3d1);
				if(movingobjectposition1 != null) {
					double d1 = var15.distanceTo(movingobjectposition1.pos);
					if(d1 < d || d == 0.0D) {
						entity = f1;
						d = d1;
					}
				}
			}
		}

		if(entity != null) {
			movingobjectposition = new HitResult(entity);
		}

		if(movingobjectposition != null) {
			if(!this.world.isRemote) {
				if(movingobjectposition.entity != null && !movingobjectposition.entity.damage(this.shooterEntity, 0)) {
				}

				this.world.createExplosion((Entity)null, this.x, this.y, this.z, 1.0F, true);
			}

			this.markDead();
		}

		this.x += this.velocityX;
		this.y += this.velocityY;
		this.z += this.velocityZ;
		float var16 = MathHelper.sqrt(this.velocityX * this.velocityX + this.velocityZ * this.velocityZ);
		this.yaw = (float)(Math.atan2(this.velocityX, this.velocityZ) * 180.0D / (double)(float)Math.PI);

		for(this.pitch = (float)(Math.atan2(this.velocityY, (double)var16) * 180.0D / (double)(float)Math.PI); this.pitch - this.prevPitch < -180.0F; this.prevPitch -= 360.0F) {
		}

		while(this.pitch - this.prevPitch >= 180.0F) {
			this.prevPitch += 360.0F;
		}

		while(this.yaw - this.prevYaw < -180.0F) {
			this.prevYaw -= 360.0F;
		}

		while(this.yaw - this.prevYaw >= 180.0F) {
			this.prevYaw += 360.0F;
		}

		this.pitch = this.prevPitch + (this.pitch - this.prevPitch) * 0.2F;
		this.yaw = this.prevYaw + (this.yaw - this.prevYaw) * 0.2F;
		float var17 = 0.95F;
		if(this.isSubmergedInWater()) {
			for(int var18 = 0; var18 < 4; ++var18) {
				float var19 = 0.25F;
				this.world.addParticle("bubble", this.x - this.velocityX * (double)var19, this.y - this.velocityY * (double)var19, this.z - this.velocityZ * (double)var19, this.velocityX, this.velocityY, this.velocityZ);
			}

			var17 = 0.8F;
		}

		this.velocityX += this.accelerationX;
		this.velocityY += this.accelerationY;
		this.velocityZ += this.accelerationZ;
		this.velocityX *= (double)var17;
		this.velocityY *= (double)var17;
		this.velocityZ *= (double)var17;
		this.world.addParticle("smoke", this.x, this.y + 0.5D, this.z, 0.0D, 0.0D, 0.0D);
		this.setPosition(this.x, this.y, this.z);
	}

	@Override
    public void writeNbt(NbtCompound nbttagcompound) {
		nbttagcompound.putShort("xTile", (short)this.xTile);
		nbttagcompound.putShort("yTile", (short)this.yTile);
		nbttagcompound.putShort("zTile", (short)this.zTile);
		nbttagcompound.putByte("inTile", (byte)this.inTile);
		nbttagcompound.putByte("shake", (byte)this.shake);
		nbttagcompound.putByte("inGround", (byte)(this.inGround ? 1 : 0));
	}

	@Override
    public void readNbt(NbtCompound nbttagcompound) {
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = nbttagcompound.getByte("inTile") & 255;
		this.shake = nbttagcompound.getByte("shake") & 255;
		this.inGround = nbttagcompound.getByte("inGround") == 1;
	}

	@Override
    public boolean isCollidable() {
		return true;
	}

	@Override
    public float getTargetingMargin() {
		return 1.0F;
	}

	@Override
    public boolean damage(Entity entity, int i) { /// Odbijanie kuli zostawiam, bo może byc za ciężko.
		this.scheduleVelocityUpdate();
		if(entity != null) {
			Vec3d vec3d = entity.getLookVector();
			if(vec3d != null) {
				this.velocityX = vec3d.x;
				this.velocityY = vec3d.y;
				this.velocityZ = vec3d.z;
				this.accelerationX = this.velocityX * 0.1D;
				this.accelerationY = this.velocityY * 0.1D;
				this.accelerationZ = this.velocityZ * 0.1D;
				this.wasDeflected = true;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
    public float getShadowRadius() {
		return 0.0F;
	}

	@Override
	public Identifier getHandlerIdentifier() {
		return Identifier.of(GolemListener.MOD_ID, "GolemFireball");
	}
}
