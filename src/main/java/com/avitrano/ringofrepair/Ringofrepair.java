package com.avitrano.ringofrepair;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;


public class Ringofrepair implements ModInitializer {
    public static final RingRepair REPAIR_RING = new RingRepair(new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS));
    public static final GameRules.Key<GameRules.IntRule> REPAIR_DELAY = GameRuleRegistry.register("repairDelay", GameRules.Category.MISC, GameRuleFactory.createIntRule(20));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("ringofrepair", "repair_ring"), REPAIR_RING);
    }
}
