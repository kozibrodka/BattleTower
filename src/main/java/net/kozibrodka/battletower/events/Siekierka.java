package net.kozibrodka.battletower.events;

import net.kozibrodka.battletower.entity.EntityGolem;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class Siekierka extends TemplateItemBase {
    public Siekierka(Identifier identifier) {
        super(identifier);
        this.maxStackSize = 16;

    }

    public boolean useOnTile(ItemInstance arg, PlayerBase arg2, Level arg3, int i, int j, int k, int l) {

        EntityGolem entitygolem = new EntityGolem(arg3, 1);
        entitygolem.setPositionAndAngles(i, j, k, arg3.rand.nextFloat() * 360F, 0.0F);
        entitygolem.setPosition(i, j + 2, k);

        arg3.playSound(i, j, k, "ambient.cave.cave", 0.7F, 1.0F);
        arg3.playSound(i, j, k, "kozibrodka.golemhurt", 0.7F, 1.0F);
        arg3.playSound(i, j, k, "kozibrodka:golemhurt", 0.7F, 1.0F);
        arg3.playSound(i, j, k, "kozibrodka:golem.golemhurt", 0.7F, 1.0F);

        return false;
    }


}
