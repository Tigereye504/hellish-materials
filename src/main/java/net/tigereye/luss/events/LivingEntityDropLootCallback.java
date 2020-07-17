package net.tigereye.luss.events;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

/**
 * Callback for killing a dood 
 * Called before item drops are dropped
/**/

public interface LivingEntityDropLootCallback {
    Event<LivingEntityDropLootCallback> EVENT = EventFactory.createArrayBacked(LivingEntityDropLootCallback.class,
            (listeners) -> (source, causedByPlayer, loot) -> {
            for (LivingEntityDropLootCallback listener : listeners) {
                TypedActionResult<List<ItemStack>> result = listener.editDroppedLoot(source, causedByPlayer, loot);
                if(result.getResult() != ActionResult.PASS) {
                    return result;
                }
                loot = result.getValue();
            }
            return TypedActionResult.pass(loot);
    });

    TypedActionResult<List<ItemStack>> editDroppedLoot(DamageSource source, boolean causedByPlayer, List<ItemStack> loot);
}