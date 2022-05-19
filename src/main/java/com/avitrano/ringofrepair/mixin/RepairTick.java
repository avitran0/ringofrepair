package com.avitrano.ringofrepair.mixin;

import com.avitrano.ringofrepair.InventoryChecker;
import com.avitrano.ringofrepair.RingRepair;
import com.avitrano.ringofrepair.Ringofrepair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class RepairTick extends LivingEntity {
    @Shadow
    public abstract Iterable<ItemStack> getItemsHand();

    protected RepairTick(EntityType<? extends LivingEntity> type, World world)
    {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void repairTick(CallbackInfo ci)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(InventoryChecker.hasItemInInventory(player, Ringofrepair.REPAIR_RING))
        {
            RingRepair.repair(world, player);
        }
    }
}
