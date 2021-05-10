package net.tigereye.hellishmaterials;

import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.tigereye.hellishmaterials.items.Luckstone;

public class CustomModelPredicateProvider implements ModelPredicateProvider {

    private float luck = 1;
    private long lastTick = 0;

    @Override
    public float call(ItemStack itemStack, ClientWorld clientWorld, LivingEntity entity) {
        if (clientWorld != null) {
            if (clientWorld.getTime() != lastTick) {
                lastTick = clientWorld.getTime();
                //luck = assessLuckstoneLuck(itemStack, livingEntity);
                CompoundTag tag = itemStack.getOrCreateTag();
                if (tag.contains(Luckstone.DISPLAY_KEY)) {
                    luck = tag.getFloat(Luckstone.DISPLAY_KEY);
                }
                //System.out.println("Luckstone Diplay: "+luck);
            }
        }
        return luck;
    }

}
