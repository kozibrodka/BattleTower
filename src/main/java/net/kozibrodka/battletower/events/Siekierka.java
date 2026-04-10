package net.kozibrodka.battletower.events;

import net.kozibrodka.battletower.entity.EntityGolem_old;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;


public class Siekierka extends TemplateSwordItem {
    public Siekierka(Identifier identifier) {
        super(identifier, ToolMaterial.DIAMOND);
        this.maxCount = 16;
        this.damage = 500;

    }

    @Override
    public boolean useOnBlock(ItemStack arg, PlayerEntity arg2, World arg3, int i, int j, int k, int l) {

        if(arg3.isRemote){
            return true;
        }
        EntityGolem_old entitygolem = new EntityGolem_old(arg3, 1);
        entitygolem.setPositionAndAngles(i, j, k, arg3.random.nextFloat() * 360F, 0.0F);
        entitygolem.setPosition(i, j + 2, k);
        arg3.spawnEntity(entitygolem);
        --arg.count;

//        arg3.playSound(i, j, k, "ambient.cave.cave", 0.7F, 1.0F);
//        arg3.playSound(i, j, k, "kozibrodka.golemhurt", 0.7F, 1.0F);
//        arg3.playSound(i, j, k, "kozibrodka:golemhurt", 0.7F, 1.0F);
//        arg3.playSound(i, j, k, "kozibrodka:golem.golemhurt", 0.7F, 1.0F);

        return false;
    }


}
