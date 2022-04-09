package net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack;

import net.minecraft.entity.LivingEntity;

public class LussAttackNoEffect implements LussAttackEffect{

    Quality quality;
    int weight;

    public LussAttackNoEffect(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
    }

    @Override
    public Quality getQuality() {
        return null;
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
    }

}
