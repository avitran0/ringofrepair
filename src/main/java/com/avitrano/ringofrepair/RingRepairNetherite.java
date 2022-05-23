package com.avitrano.ringofrepair;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.world.World;

import static com.avitrano.ringofrepair.Ringofrepair.CONFIG;

public class RingRepairNetherite extends Item {
    public RingRepairNetherite(Settings settings) {
        super(settings);
    }

    public static void repair(World world, PlayerEntity player) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (serverPlayer.age % CONFIG.repairDelayNetherite == 0) {
                for (int i = 0; i < serverPlayer.getInventory().size(); i++) {
                    ItemStack stack = serverPlayer.getInventory().getStack(i);
                    if (!stack.isEmpty()) {
                        if (stack.isDamaged() && !(stack == serverPlayer.getMainHandStack())) {
                            stack.setDamage(stack.getDamage()-1);
                            break;
                        }
                    }
                }
                TrinketComponent trinketInventory = TrinketsApi.getTrinketComponent(player).get();

                for (int i = 0; i < trinketInventory.getAllEquipped().size(); i++) {
                    Pair<SlotReference, ItemStack> slotstackPair = trinketInventory.getAllEquipped().get(i);
                    if (slotstackPair.getRight().isDamaged()) {
                        slotstackPair.getRight().setDamage(slotstackPair.getRight().getDamage() - 1);
                        return;
                    }
                }
            }
        }
    }
}
