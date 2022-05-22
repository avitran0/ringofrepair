package com.avitrano.ringofrepair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class RingRepair extends Item {
    public RingRepair(Settings settings) {
        super(settings);
    }

    public static void repair(World world, PlayerEntity player, int numRings) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            if (serverPlayer.age % (40 / numRings) == 0) {
                for (int i = 0; i < serverPlayer.getInventory().size(); i++) {
                    ItemStack stack = serverPlayer.getInventory().getStack(i);
                    if (!stack.isEmpty()) {
                        if (stack.isDamaged() && !(stack == serverPlayer.getMainHandStack())) {
                            stack.setDamage(stack.getDamage()-1);
                            break;
                        }
                    }
                }
            }
        }
    }
}
