package net.tigereye.hellishmaterials;

import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.tigereye.hellishmaterials.items.Luckstone;

public class CustomModelPredicateProvider implements UnclampedModelPredicateProvider {

    private float luck = 1;
    private long lastTick = 0;

    @Override
    public float unclampedCall(ItemStack itemStack, ClientWorld clientWorld, LivingEntity entity, int seed) {
        if (clientWorld != null) {
            if (clientWorld.getTime() != lastTick) {
                lastTick = clientWorld.getTime();
                //luck = assessLuckstoneLuck(itemStack, livingEntity);
                NbtCompound tag = itemStack.getOrCreateNbt();
                if (tag.contains(Luckstone.DISPLAY_KEY)) {
                    luck = tag.getFloat(Luckstone.DISPLAY_KEY);
                }
                //System.out.println("Luckstone Diplay: "+luck);
            }
        }
        return luck;
    }

}
