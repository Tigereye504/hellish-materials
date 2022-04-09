package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ShulkerEntity;

public class LussAttackShulkerOnHead implements LussAttackEffect{


    Quality quality;
    int weight;
    public LussAttackShulkerOnHead(Quality quality, int weight){
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
        ShulkerEntity shulker = new ShulkerEntity(EntityType.SHULKER, defender.world);
        Entity topOfStack = defender;
        while(topOfStack.hasPassengers()){
            topOfStack = topOfStack.getFirstPassenger();
        }
        shulker.setTarget(defender);
        defender.world.spawnEntity(shulker);
        shulker.startRiding(topOfStack,true);
    }
}
