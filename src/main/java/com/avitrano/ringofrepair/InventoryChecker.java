package com.avitrano.ringofrepair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryChecker {
    public static int hasItemInInventory(PlayerEntity player, Item item)
    {
        PlayerInventory inv = player.getInventory();
        int size = inv.size();
        int numItems = 0;

        //Is the item in the player inventory?
        for (int slot = 0; slot < size; slot++)
        {
            ItemStack stack = inv.getStack(slot);
            if (stack.getItem() == item)
            {
                numItems++;
            }
        }

        return numItems;
    }
}
