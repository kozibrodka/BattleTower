package net.kozibrodka.battletower.entity;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.monster.MonsterBase;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;


public class EntityGolem extends MonsterBase
{

    public EntityGolem(Level world, int i)
    {
        super(world);
        texture = "/assets/battletower/stationapi/textures/mob/golemdormant.png";
        movementSpeed = 0.35F;
        attackDamage = 8;
        health = 150 + 150 * i;
        setSize(1.6F, 3.4F);
        yaw = 0.0F;
        field_21481_field_20158_dormant = 1;
        pathToEntity = 0;
        field_21479_field_20159_growl = 0;
        immuneToFire = true;
        field_21480_field_20161_drops = 2 + 2 * i;
        setPositionAndAngles(x, y, z, 0.0F, 0.0F);
    }

    public EntityGolem(Level world)
    {
        super(world);
        texture = "/assets/battletower/stationapi/textures/mob/golem.png";
        movementSpeed = 0.35F;
        attackDamage = 8;
        health = 300;
        setSize(1.6F, 3.4F);
        yaw = 0.0F;
        field_21481_field_20158_dormant = 0;
        pathToEntity = 0;
        field_21479_field_20159_growl = 0;
        immuneToFire = true;
        field_21480_field_20161_drops = 1;
        setPositionAndAngles(x, y, z, 0.0F, 0.0F);
    }

    public void remove()
    {
        if(health <= 0 || level.difficulty == 0)
        {
            super.remove();
        }
    }

    public void onKilledBy(EntityBase entitybase)
    {
        if(field_1024 > 0 && entitybase != null)
        {
            entitybase.onKilledOther(this, field_1024);
        }
        if(!level.isServerSide)
        {
            int i = field_21480_field_20161_drops - rand.nextInt(2);
            for(int j = 0; j < i; j++)
            {
                dropItem(ItemBase.diamond.id, 1);
            }

            i = rand.nextInt(4) + 9;
            for(int k = 0; k < i; k++)
            {
                dropItem(BlockBase.STONE_SLAB.id, 1);
            }

        }
        level.method_185(this, (byte)3);
    }

    public void method_925(EntityBase entity, int i, double d, double d1)
    {
        movementSpeed = 0.35F + (float)((double)(450 - health) / 1750D);
        if(rand.nextInt(5) == 0)
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
            PlayerBase entityplayer = level.getClosestPlayerTo(this, 6D);
            if(entityplayer != null && method_928(entityplayer))
            {
                field_21481_field_20158_dormant = 0;
                level.playSound(x, y, z, "ambient.cave.cave", 0.7F, 1.0F);
                level.playSound(this, "battletower:golem.golemawaken", getSoundVolume() * 2.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                texture = "/assets/battletower/stationapi/textures/mob/golem.png";
                pathToEntity = 175;
            }
        } else
        {
            List list = level.getEntities(this, boundingBox.expand(6D, 6D, 6D));
            boolean flag = false;
            int i = 0;
            do
            {
                if(i >= list.size())
                {
                    break;
                }
                EntityBase entitybase = (EntityBase)list.get(i);
                if(entitybase == entity)
                {
                    flag = true;
                    break;
                }
                i++;
            } while(true);
            if(!flag && entity != null || field_21479_field_20159_growl == 1)
            {
                pathToEntity--;
            } else
            {
                pathToEntity = 175;
            }
        }
    }

    public void tick()
    {
        if(field_21481_field_20158_dormant == 0)
        {
            if(pathToEntity <= 0 && field_21479_field_20159_growl == 0)
            {
                if(field_21479_field_20159_growl == 0 && (entity instanceof PlayerBase) && level.getClosestPlayerTo(this, 24D) == null)
                {
                    entity = null;
                } else
                if(!func_21476_func_20154_happyMan())
                {
                    level.playSound(this, "battletower:golem.golemspecial", getSoundVolume() * 2.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
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
                if(!level.isServerSide) {
                    level.createExplosion(this, x, y, z, 4.5F + (float)(field_21480_field_20161_drops - 2) / 4F);
                }
                pathToEntity = 125;
                field_21479_field_20159_growl = 0;
            }
            super.tick();
        }
        func_21475_func_20155_look_();
    }

    public void writeCustomDataToTag(CompoundTag arg)
    {
        super.writeCustomDataToTag(arg);
        arg.put("isDormant", (byte)field_21481_field_20158_dormant);
        arg.put("hasGrowled", (byte)field_21479_field_20159_growl);
        arg.put("rageCounter", (byte)pathToEntity);
        arg.put("Drops", (byte)field_21480_field_20161_drops);
    }

    public void readCustomDataFromTag(CompoundTag arg)
    {
        super.readCustomDataFromTag(arg);
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
            Field field = (net.minecraft.entity.Living.class).getDeclaredField("team");
            i = field.getInt(this);
            Field field1 = (net.minecraft.entity.Living.class).getDeclaredField("team");
            j = field1.getInt(super.entity);
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

    protected void tryAttack(EntityBase entitybase, float f)
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
            super.tryAttack(entitybase, f);
        }
    }

    protected String getAmbientSound()
    {
        if(field_21481_field_20158_dormant == 0)
        {
            return "battletower:golem.golem";
        } else
        {
            return null;
        }
    }

    protected String getHurtSound()
    {
        return "battletower:golemhurt";
    }

    protected String getDeathSound()
    {
        return "battletower:golem.golemdeath";
    }


    protected int getMobDrops()
    {
        return ItemBase.brick.id;
    }


    public boolean constructed = false;
    public int ticks = 0;

    public int field_21481_field_20158_dormant = 1;
    private int pathToEntity;
    private int field_21479_field_20159_growl;
    private int field_21480_field_20161_drops;
    public EntityBase field_21478_field_20156_b;
    public int field_21477_field_20160_c;
}
