package com.avitrano.ringofrepair;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;


public class Ringofrepair implements ModInitializer {
    public static final String MODID = "ringofrepair";

    public static final RingofrepairConfig CONFIG = RingofrepairConfig.init().getConfig();

    public static final RingRepair REPAIR_RING = new RingRepair(new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS));
    public static final RingRepairNetherite REPAIR_RING_NETHERITE = new RingRepairNetherite(new FabricItemSettings().maxCount(1).group(ItemGroup.TOOLS));

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("trinkets")) {
            TrinketsApi.registerTrinket(REPAIR_RING, new Trinket() {
                boolean equippedRingRepair = false;
                @Override
                public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    equippedRingRepair = true;
                }
                @Override
                public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    World world = player.world;
                    if(equippedRingRepair)
                    {
                        RingRepair.repair(world, player);
                    }
                    Trinket.super.tick(stack, slot, entity);
                }
                @Override
                public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    equippedRingRepair = false;
                }
            });
            TrinketsApi.registerTrinket(REPAIR_RING_NETHERITE, new Trinket() {
                boolean equippedRingRepairNetherite = false;
                @Override
                public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    equippedRingRepairNetherite = true;
                }
                @Override
                public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    World world = player.world;
                    if(equippedRingRepairNetherite)
                    {
                        RingRepairNetherite.repair(world, player);
                    }
                    Trinket.super.tick(stack, slot, entity);
                }
                @Override
                public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    equippedRingRepairNetherite = false;
                }
            });
        }
        Registry.register(Registry.ITEM, new Identifier(MODID, "repair_ring"), REPAIR_RING);
        Registry.register(Registry.ITEM, new Identifier(MODID, "repair_ring_netherite"), REPAIR_RING_NETHERITE);
    }
}
