package net.kozibrodka.battletower.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.battletower.events.DestroyerSystem;
import net.kozibrodka.battletower.events.GeneratorStarter;
import net.kozibrodka.battletower.events.GolemListener;
import net.kozibrodka.battletower.gen.TowerDestroyer;
import net.kozibrodka.battletower.network.CoordsPacket;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;


public class EntityGolem extends MonsterEntity implements MobSpawnDataProvider {
//	private int dormant;
	private int rageCounter;
	private int explosionAttack;
	private int drops;
	private final float golemMoveSpeed = 0.05F;
	private int attackCounter;
	public Vec3i towerTopCoord = null;
	private int maxHealth;
	private boolean constructed;

	public EntityGolem(World world, int diff) {
		super(world);
		int i;
		if(diff<3){
			i = diff;
		}else{
			i = 0;
		}
		texture = "/assets/battletower/stationapi/textures/mob/golemdormant.png";
		movementSpeed = golemMoveSpeed;
		attackDamage = 8;
		health = 150 + 150 * i; //150 old
		maxHealth = health;
		setBoundingBoxSpacing(1.6F, 3.4F);
		yaw = 0.0F;
		setDormant(1);
		rageCounter = 0;
		explosionAttack = 0;
		fireImmune = true;
//		drops = 5 + i; // old
		drops = 2 + 2 * i;
		setPositionAndAngles(x, y, z, 0.0F, 0.0F);
		attackCounter = 0;
	}

	public EntityGolem(World world) {
		super(world);
		texture = "/assets/battletower/stationapi/textures/mob/golem.png";
		movementSpeed = golemMoveSpeed;
		attackDamage = 8;
		health = 300;
		setBoundingBoxSpacing(1.6F, 3.4F);
		yaw = 0.0F;
		setDormant(1);
		rageCounter = 0;
		explosionAttack = 0;
		fireImmune = true;
		drops = 1;
		setPositionAndAngles(x, y, z, 0.0F, 0.0F);
		attackCounter = 0;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public String getTexture() {
		if (getDormant() == 1) {
			return "/assets/battletower/stationapi/textures/mob/golemdormant.png";
		} else {
			return "/assets/battletower/stationapi/textures/mob/golem.png";
		}
	}

	@Override
    public void markDead() {
		if(health <= 0) { //TODO its actually logic for CLIENT REGISTER LOL - make for ALL players... chyba juz?? test
			if(world.isRemote) {
				towerTopCoord = new Vec3i(dataTracker.getInt(17), dataTracker.getInt(18), dataTracker.getInt(19));;
				if (GeneratorStarter.config.tower_destroyer && towerTopCoord.y != -1 && world.getClosestPlayer(this, 24.0D) != null && !constructed) {
					((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.addChatMessage("The Tower Guardian has fallen! Without it's energy, the tower will collapse...");
					DestroyerSystem.registerTowerDestroyer(new TowerDestroyer(world, towerTopCoord, System.currentTimeMillis(), world.getClosestPlayer(this, 24.0D)));
					constructed = true;
				}
			}
			super.markDead();
		}
	}

	@Override
    public void onKilledBy(Entity entity) {
		super.onKilledBy(entity);
		if(scoreAmount > 0 && entity != null) {
			entity.updateKilledAchievement(this, scoreAmount);
		}

		if(!world.isRemote) { /// Diaxy są OP, ale chyba zostawiamy...
			int i = drops - random.nextInt(2);
			for(int j = 0; j < i; j++)
			{
				if(GeneratorStarter.config.less_diamonds){
					if(random.nextInt(2) == 0){
						dropItem(Item.DIAMOND.id, 1);
					}
				}else{
					dropItem(Item.DIAMOND.id, 1);
				}
			}
			i = random.nextInt(4) + 9;
			for(int k = 0; k < i; k++)
			{
				dropItem(Block.SLAB.id, 1);
			}
			/// NEW - unbalanced imo
//			int i = drops;
//			int k;
//			for(k = 0; k < i; ++k) {
//				dropItem(Item.DIAMOND.id, 1);
//				dropItem(Item.REDSTONE.id, 1);
//			}
//			i = random.nextInt(4) + 8;
//
//			for(k = 0; k < i; ++k) {
//				dropItem(Block.CLAY.id, 1);
//			}
			///
			towerTopCoord = new Vec3i(dataTracker.getInt(17), dataTracker.getInt(18), dataTracker.getInt(19));;
			if(towerTopCoord.y != -1 && target != null && GeneratorStarter.config.tower_destroyer) {
				if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
					GolemListener.mc.inGameHud.addChatMessage("The Tower Guardian has fallen! Without it's energy, the tower will collapse...");
				}else{
					GolemListener.mcServ.sendMessage("The Tower Guardian has fallen! Without it's energy, the tower will collapse...");
				}
				DestroyerSystem.registerTowerDestroyer(new TowerDestroyer(world, towerTopCoord, System.currentTimeMillis(), target));
			}
		}

		world.broadcastEntityEvent(this, (byte)3);
	}



	@Override
    public void applyKnockback(Entity entity, int i, double d, double d1) {
		if(random.nextInt(5) == 0) {
			velocityX *= 1.5D;
			velocityZ *= 1.5D;
			velocityY += (double)0.6F;
		}

	}

	protected void look() {
		if(getDormant() == 1) {
			PlayerEntity targetNearby = world.getClosestPlayer(this, 6.0D);
			if(targetNearby != null && canSee(targetNearby)) {
				setDormant(0);
				if((int)y > 90) {
//					towerTopCoord = new Vec3i((int)x, (int)y, (int)z);
					dataTracker.set(17, (int)x);
					dataTracker.set(18, (int)y);
					dataTracker.set(19, (int)z);
				}
				world.broadcastEntityEvent(this, (byte)6);
				world.playSound(x, y, z, "ambient.cave.cave", 0.7F, 1.0F);
				world.playSound(this, "battletower:golemawaken", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
				rageCounter = 175;
			}
		} else if(target != null) {
			if(dataTracker.getInt(18) == -1) {
//				towerTopCoord = new Vec3i((int)x, (int)y, (int)z);
				dataTracker.set(17, (int)x);
				dataTracker.set(18, (int)y);
				dataTracker.set(19, (int)z);
			}

			boolean var10 = target.getSquaredDistance(this) < 36.0D;
			if(var10 && explosionAttack != 1 && (!var10 || y - target.y <= 0.3D)) {
				rageCounter = 175;
			} else {
				rageCounter -= 2;
			}

			double diffX = target.x - x;
			double diffY = target.boundingBox.minY + (double)(target.height / 2.0F) - (y + (double)height * 0.8D);
			double diffZ = target.z - z;
			bodyYaw = yaw = -((float)Math.atan2(diffX, diffZ)) * 180.0F / 3.141593F;
			if(canSee(target)) {
				if(attackCounter == 10) {
					world.broadcastEntityEvent(this, (byte)7);
					world.playSound(this, "battletower:golemcharge", getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
				}

				++attackCounter;
				if(attackCounter == 20) {
					world.broadcastEntityEvent(this, (byte)8);
					world.playSound(this, "mob.ghast.fireball", getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
					if(!world.isRemote) {
						EntityGolemFireball entityfireball = new EntityGolemFireball(world, this, diffX, diffY, diffZ);
						Vec3d vec3d = getLookVector(1.0F);
						entityfireball.x = x + vec3d.x * 2.0D;
						entityfireball.y = y + (double)height * 0.8D;
						entityfireball.z = z + vec3d.z * 2.0D;
						world.spawnEntity(entityfireball);
					}

					attackCounter = -40;
				}
			} else if(attackCounter > 0) {
				--attackCounter;
			}
		} else {
			bodyYaw = yaw = -((float)Math.atan2(velocityX, velocityZ)) * 180.0F / 3.141593F;
			if(attackCounter > 0) {
				--attackCounter;
			}
		}

	}

//	@Environment(EnvType.SERVER)
//	public void coordsPacket(World world, int x, int y, int z) {
//		List list2 = world.players;
//		if (list2.size() != 0) {
//			for (int k = 0; k < list2.size(); k++) {
//				ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
//				PacketHelper.sendTo(player1, new CoordsPacket(x, y, z, this.id));
//			}
//		}
//	}

//	public void clientLook(){
//		if(getDormant() == 1) {
//			PlayerEntity targetNearby = world.getClosestPlayer(this, 6.0D);
//			if(targetNearby != null && canSee(targetNearby)) {
//				setDormant(0);
//				if((int)y > 90) {
//					towerTopCoord = new Vec3i((int)x, (int)y, (int)z);
//				}
//			}
//		} else if(target != null) {
//			if (towerTopCoord == null) {
//				towerTopCoord = new Vec3i((int) x, (int) y, (int) z);
//			}
//		}
//	}

	@Override
    public void tick() {
		if(world.isRemote){
			super.tick();
			return;
		}

		if(getDormant() == 0) {
			velocityX *= 0.7D;
			velocityZ *= 0.7D;
			if(rageCounter <= 0 && explosionAttack == 0) {
				if(explosionAttack == 0 && target instanceof PlayerEntity && world.getClosestPlayer(this, 24.0D) == null) {
					target = null;
				} else {
					world.broadcastEntityEvent(this, (byte)9);
					world.playSound(this, "battletower:golemspecial", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
					velocityY += 0.9D;
					explosionAttack = 1;
					movementSpeed = 1.0F;
				}
			} else if(target == null) {
				health = maxHealth;
				rageCounter = 125;
				explosionAttack = 0;
			} else if((rageCounter <= -30 || onGround) && explosionAttack == 1) {
				if(health <= 425) {
					health += 25;
				}

				if(!world.isRemote && y - target.y > 0.3D) {
					world.createExplosion(this, x, y - 0.3D, z, 4.0F);
				}

				rageCounter = 125;
				explosionAttack = 0;
				movementSpeed = golemMoveSpeed;
			}

			super.tick();
		}

		look();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void processServerEntityStatus(byte status) {
		if (status == 6) {
			world.playSound(this, "ambient.cave.cave", 0.7F, 1.0F); /// test
			world.playSound(this, "battletower:golemawaken", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
		} else if (status == 7) {
			world.playSound(this, "battletower:golemcharge", getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
		} else if (status == 8) {
			world.playSound(this, "mob.ghast.fireball", getSoundVolume(), (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
		} else if (status == 9) {
			world.playSound(this, "battletower:golemspecial", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
		} else {
			super.processServerEntityStatus(status);
		}

	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		dataTracker.startTracking(16, (byte)0); //DORMANT
		dataTracker.startTracking(17, -1); //Found X
		dataTracker.startTracking(18, -1); //Found Y
		dataTracker.startTracking(19, -1); //Found Z
	}

	public void setDormant(int type)
	{
		if(!world.isRemote) {
			dataTracker.set(16, (byte) type);
		}

	}

	public int getDormant()
	{
		return dataTracker.getByte(16);
	}

	@Override
    public void writeNbt(NbtCompound nbttagcompound) {
		super.writeNbt(nbttagcompound);
		nbttagcompound.putByte("isDormant", (byte) getDormant());
		nbttagcompound.putByte("hasexplosionAttacked", (byte)explosionAttack);
		nbttagcompound.putByte("rageCounter", (byte)rageCounter);
		nbttagcompound.putByte("Drops", (byte)drops);
		nbttagcompound.putInt("MaxHealth", maxHealth);

		nbttagcompound.putInt("CoordX", dataTracker.getInt(17));
		nbttagcompound.putInt("CoordY", dataTracker.getInt(18));
		nbttagcompound.putInt("CoordZ", dataTracker.getInt(19));
	}

	@Override
    public void readNbt(NbtCompound nbttagcompound) {
		super.readNbt(nbttagcompound);
		setDormant(nbttagcompound.getByte("isDormant") & 255); ///TEST BYTES
		explosionAttack = nbttagcompound.getByte("hasexplosionAttacked") & 255;
		rageCounter = nbttagcompound.getByte("rageCounter") & 255;
		drops = nbttagcompound.getByte("Drops") & 255;
		movementSpeed = golemMoveSpeed;
		maxHealth = nbttagcompound.getInt("MaxHealth");

		dataTracker.set(17, nbttagcompound.getInt("CoordX"));
		dataTracker.set(18, nbttagcompound.getInt("CoordY"));
		dataTracker.set(19, nbttagcompound.getInt("CoordZ"));

		attackDamage = 8;
	}

	@Override
    protected void attack(Entity entity, float f) {
		if((double)f < 3.0D && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
			entity.damage(this, attackDamage);
		}

		if(onGround) {
			double d = entity.x - x;
			double d1 = entity.z - z;
			float f1 = MathHelper.sqrt(d * d + d1 * d1);
			velocityX = (d / (double)f1) * 0.5D * 0.20000000192092895D + velocityX * 0.20000000098023224D;
			velocityZ = (d1 / (double)f1) * 0.5D * 0.10000000192092896D + velocityZ * 0.20000000098023224D;
		} else {
			super.attack(entity, f);
		}

	}

	@Override
    protected String getRandomSound() {
		return getDormant() == 0 ? "battletower:golem" : null;
	}

	@Override
    protected String getHurtSound() {
		return "battletower:golemhurt";
	}

	@Override
    protected String getDeathSound() {
		return "battletower:golemdeath"; //todo??
	}

	@Override
    protected int getDroppedItemId() {
		return Item.BRICK.id;
	}

	@Override
	protected void dropItems()
	{
	}

	@Override
	public Identifier getHandlerIdentifier() {
		return Identifier.of(GolemListener.MOD_ID, "TowerGolem");
	}
}
