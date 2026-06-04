package net.kozibrodka.battletower.glasscfg;


import net.glasslauncher.mods.gcapi3.api.ConfigCategory;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class BattleTowerCFG {

    @ConfigEntry(name="Tower Rarity", minValue = 1, maxValue = 50, multiplayerSynced = true)
    public Integer tower_rarity = 12;

    @ConfigEntry(name="Min Tower Distance", minValue = 16, maxValue = 1000000, description = "?", multiplayerSynced = true)
    public Double min_tower_distance = 64.0;

    @ConfigEntry(name="Enable Tower Collapse", multiplayerSynced = true)
    public Boolean tower_destroyer = true;

    @ConfigEntry(name="50% chance of each Diamond Drop from Golem", multiplayerSynced = true)
    public Boolean less_diamonds = false;

    @ConfigEntry(name="Chest tower Loot Overhaul", description = "Disable to get old loot tables", multiplayerSynced = true)
    public Boolean loot_rework = true;

    @ConfigEntry(name="Number of ItemStacks per chest", minValue = 1, maxValue = 54, multiplayerSynced = true)
    public Integer loot_rarity = 3;

    @ConfigEntry(name="Enable Mo' Creatures Spawners integration", multiplayerSynced = true)
    public Boolean mocr_monsters = true;

    @ConfigCategory(name="§6Chest Loot Mods integration" , multiplayerSynced = true)
    public ModsLootCFG mod_loot = new ModsLootCFG();



}
