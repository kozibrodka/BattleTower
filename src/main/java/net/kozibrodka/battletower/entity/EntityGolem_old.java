package net.kozibrodka.battletower.entity;

import net.kozibrodka.battletower.events.GolemListener;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MonsterEntity;

import java.lang.reflect.Field;
import java.util.List;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;


public class EntityGolem_old extends MonsterEntity implements MobSpawnDataProvider
{

    public EntityGolem_old(World world, int i)
    {
        super(world);
        texture = "/assets/battletower/stationapi/textures/mob/golemdormant.png";
        movementSpeed = 0.35F;
        attackDamage = 8;
        health = 150 + 150 * i;
        setBoundingBoxSpacing(1.6F, 3.4F);
        yaw = 0.0F;
        field_21481_field_20158_dormant = 1;
        pathToEntity = 0;
        field_21479_field_20159_growl = 0;
        fireImmune = true;
        field_21480_field_20161_drops = 2 + 2 * i;
        setPositionAndAngles(x, y, z, 0.0F, 0.0F);
    }

    public EntityGolem_old(World world)
    {
        super(world);
        texture = "/assets/battletower/stationapi/textures/mob/golem.png";
        movementSpeed = 0.35F;
        attackDamage = 8;
        health = 300;
        setBoundingBoxSpacing(1.6F, 3.4F);
        yaw = 0.0F;
        field_21481_field_20158_dormant = 0;
        pathToEntity = 0;
        field_21479_field_20159_growl = 0;
        fireImmune = true;
        field_21480_field_20161_drops = 1;
        setPositionAndAngles(x, y, z, 0.0F, 0.0F);
    }

    @Override
    public void markDead()
    {
        if(health <= 0 || world.difficulty == 0)
        {
            super.markDead();
        }
    }

    @Override
    public void onKilledBy(Entity entitybase)
    {
        if(scoreAmount > 0 && entitybase != null)
        {
            entitybase.updateKilledAchievement(this, scoreAmount);
        }
        if(!world.isRemote)
        {
            int i = field_21480_field_20161_drops - random.nextInt(2);
            for(int j = 0; j < i; j++)
            {
                dropItem(Item.DIAMOND.id, 1);
            }

            i = random.nextInt(4) + 9;
            for(int k = 0; k < i; k++)
            {
                dropItem(Block.SLAB.id, 1);
            }

        }
        world.broadcastEntityEvent(this, (byte)3);
    }

    @Override
    public void applyKnockback(Entity entity, int i, double d, double d1)
    {
        movementSpeed = 0.35F + (float)((double)(450 - health) / 1750D);
        if(random.nextInt(5) == 0)
        {
            velocityX *= 1.5D;
            velocityZ *= 1.5D;
            velocityY += 0.60000002384185791D;
        }
        pathToEntity = 150;
    }

    protected void func_21475_func_20155_look_()
    {
        if(field_21481_field_20158_dormant == 1)
        {
            PlayerEntity entityplayer = world.getClosestPlayer(this, 6D);
            if(entityplayer != null && canSee(entityplayer))
            {
                field_21481_field_20158_dormant = 0;
                world.playSound(x, y, z, "ambient.cave.cave", 0.7F, 1.0F);
                world.playSound(this, "battletower:golemawaken", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                texture = "/assets/battletower/stationapi/textures/mob/golem.png";
                pathToEntity = 175;
            }
        } else
        {
            List list = world.getEntities(this, boundingBox.expand(6D, 6D, 6D));
            boolean flag = false;
            int i = 0;
            do
            {
                if(i >= list.size())
                {
                    break;
                }
                Entity entitybase = (Entity)list.get(i);
                if(entitybase == target)
                {
                    flag = true;
                    break;
                }
                i++;
            } while(true);
            if(!flag && target != null || field_21479_field_20159_growl == 1)
            {
                pathToEntity--;
            } else
            {
                pathToEntity = 175;
            }
        }
    }

    @Override
    public void tick()
    {
        if(field_21481_field_20158_dormant == 0)
        {
            if(pathToEntity <= 0 && field_21479_field_20159_growl == 0)
            {
                if(field_21479_field_20159_growl == 0 && (target instanceof PlayerEntity) && world.getClosestPlayer(this, 24D) == null)
                {
                    target = null;
                } else
                if(!func_21476_func_20154_happyMan())
                {
                    world.playSound(this, "battletower:golemspecial", getSoundVolume() * 2.0F, ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                    velocityY += 0.90000000000000002D;
                    field_21479_field_20159_growl = 1;
                } else
                {
                    pathToEntity = 150;
                }
            } else
            if((pathToEntity <= -30 || onGround) && field_21479_field_20159_growl == 1)
            {
                if(health <= 425)
                {
                    health += 25;
                }
                if(!world.isRemote) {
                    world.createExplosion(this, x, y, z, 4.5F + (float)(field_21480_field_20161_drops - 2) / 4F);
                }
                pathToEntity = 125;
                field_21479_field_20159_growl = 0;
            }
            super.tick();
        }
        func_21475_func_20155_look_();
    }

    @Override
    public void writeNbt(NbtCompound arg)
    {
        super.writeNbt(arg);
        arg.putByte("isDormant", (byte)field_21481_field_20158_dormant);
        arg.putByte("hasGrowled", (byte)field_21479_field_20159_growl);
        arg.putByte("rageCounter", (byte)pathToEntity);
        arg.putByte("Drops", (byte)field_21480_field_20161_drops);
    }

    @Override
    public void readNbt(NbtCompound arg)
    {
        super.readNbt(arg);
        field_21481_field_20158_dormant = arg.getByte("isDormant") & 0xff;
        field_21479_field_20159_growl = arg.getByte("hasGrowled") & 0xff;
        pathToEntity = arg.getByte("rageCounter") & 0xff;
        field_21480_field_20161_drops = arg.getByte("Drops") & 0xff;
        movementSpeed = 0.35F + (float)((double)(450 - health) / 1750D);
        if(field_21481_field_20158_dormant == 1)
        {
            texture = "/assets/battletower/stationapi/textures/mob/golemdormant.png";
        } else
        {
            texture = "/assets/battletower/stationapi/textures/mob/golem.png";
        }
        attackDamage = 8;
    }

    protected boolean func_21476_func_20154_happyMan()
    {
        int i = -1;
        int j = 0;
        try
        {
            Field field = (net.minecraft.entity.LivingEntity.class).getDeclaredField("team");
            i = field.getInt(this);
            Field field1 = (net.minecraft.entity.LivingEntity.class).getDeclaredField("team");
            j = field1.getInt(super.target);
        }
        catch(Exception exception)
        {
            if(!(exception instanceof SecurityException) && !(exception instanceof NoSuchFieldException))
            {
                if(!(exception instanceof IllegalAccessException));
            }
        }
        return i == j;
    }

    @Override
    protected void attack(Entity entitybase, float f)
    {
        if((double)f < 3D && entitybase.boundingBox.maxY > boundingBox.minY && entitybase.boundingBox.minY < boundingBox.maxY)
        {
            entitybase.damage(this, attackDamage);
        }
        if(onGround)
        {
            double d = entitybase.x - x;
            double d1 = entitybase.z - z;
            float f1 = MathHelper.sqrt(d * d + d1 * d1);
            velocityX = (d / (double)f1) * 0.5D * 0.20000000192092895D + velocityX * 0.20000000098023224D;
            velocityZ = (d1 / (double)f1) * 0.5D * 0.10000000192092896D + velocityZ * 0.20000000098023224D;
        } else
        {
            super.attack(entitybase, f);
        }
    }

    @Override
    protected String getRandomSound()
    {
        if(field_21481_field_20158_dormant == 0)
        {
            return "battletower:golem";
        } else
        {
            return null;
        }
    }

    @Override
    protected String getHurtSound()
    {
        return "battletower:golemhurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "battletower:golemdeath";
    }


    @Override
    protected int getDroppedItemId()
    {
        return Item.BRICK.id;
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return Identifier.of(GolemListener.MOD_ID, "TowerGolem_old");
    }


    public boolean constructed = false;
    public int ticks = 0;

    public int field_21481_field_20158_dormant = 1;
    private int pathToEntity;
    private int field_21479_field_20159_growl;
    private int field_21480_field_20161_drops;
    public Entity field_21478_field_20156_b;
    public int field_21477_field_20160_c;
}
