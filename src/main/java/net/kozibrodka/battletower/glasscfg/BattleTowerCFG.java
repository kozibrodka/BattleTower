package net.kozibrodka.battletower.glasscfg;


import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;

public class BattleTowerCFG {

    @ConfigName("Tower Rarity")
    @Comment("1 - maximum rarity, 50 - minimum rarity")
    @MaxLength(
            value = 50
    )
    public Integer towerrarity = 12;

}
