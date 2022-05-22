package com.avitrano.ringofrepair;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Ringofrepair implements ModInitializer {
    public static final String MODID = "ringofrepair";

    public static final RingRepair REPAIR_RING = new RingRepair(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));
    public static final RingRepairNetherite REPAIR_RING_NETHERITE = new RingRepairNetherite(new FabricItemSettings().maxCount(1).group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "repair_ring"), REPAIR_RING);
        Registry.register(Registry.ITEM, new Identifier(MODID, "repair_ring_netherite"), REPAIR_RING_NETHERITE);
    }
}
