package com.avitrano.ringofrepair;

import dev.emi.trinkets.api.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.world.World;

import static com.avitrano.ringofrepair.Ringofrepair.CONFIG;

public class RingRepair extends Item {
    public RingRepair(Settings settings) {
        super(settings);
    }

    public static void repair(World world, PlayerEntity player) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (serverPlayer.age % CONFIG.repairDelay == 0) {
                for (int i = 0; i < serverPlayer.getInventory().size(); i++) {
                    ItemStack stack = serverPlayer.getInventory().getStack(i);
                    if (!stack.isEmpty()) {
                        if (stack.isDamaged() && !(stack == serverPlayer.getMainHandStack())) {
                            stack.setDamage(stack.getDamage() - 1);
                            return;
                        }
                    }
                }
                TrinketComponent trinketInventory = TrinketsApi.getTrinketComponent(player).get();

                for (int i = 0; i < trinketInventory.getAllEquipped().size(); i++) {
                    Pair<SlotReference, ItemStack> slotstackPair = trinketInventory.getAllEquipped().get(i);
                    ItemStack stack2 = slotstackPair.getRight();
                    if (stack2.isDamaged()) {
                        stack2.setDamage(stack2.getDamage() - 1);
                        return;
                    }
                }
            }
        }
    }
}
