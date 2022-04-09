package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;

public class LussAttackChickenStackOnHead implements LussAttackEffect{


    Quality quality;
    int weight;
    public LussAttackChickenStackOnHead(Quality quality, int weight){
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
        Entity topOfStack = defender;
        while(topOfStack.hasPassengers()){
            topOfStack = topOfStack.getFirstPassenger();
        }
        for (int i = 0; i < damage; i++) {
            ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, defender.world);
            defender.world.spawnEntity(chicken);
            chicken.startRiding(topOfStack, true);
            topOfStack = chicken;
        }
    }
}
