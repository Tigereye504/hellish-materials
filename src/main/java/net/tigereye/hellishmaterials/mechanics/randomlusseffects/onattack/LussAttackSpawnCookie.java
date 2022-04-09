package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class LussAttackSpawnCookie implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackSpawnCookie(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
    }
    @Override
    public Quality getQuality() {
        return this.quality;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        World w = defender.getWorld();
        ItemEntity entity = new ItemEntity(w,
                defender.getX(), defender.getBodyY(.5), defender.getZ(),
                new ItemStack(Items.COOKIE, 1));
        w.spawnEntity(entity);

    }
}
