package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

public class LussAttackEffectCloud implements LussAttackEffect{


    Quality quality;
    StatusEffect effect;
    int weight;
    int duration;
    int amplification;
    int cloudDuration;
    int cloudDurationPerUse;
    float cloudRadius;

    public LussAttackEffectCloud(Quality quality, int weight, StatusEffect effect, int duration, int amplification, int cloudDuration, int cloudDurationPerUse, float cloudRadius){
        this.quality = quality;
        this.effect = effect;
        this.duration = duration;
        this.weight = weight;
        this.amplification = amplification;
        this.cloudDuration = cloudDuration;
        this.cloudDurationPerUse = cloudDurationPerUse;
        this.cloudRadius = cloudRadius;
    }

    @Override
    public Quality getQuality() {
        return quality;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(defender.world, defender.getX(), defender.getBodyY(0.5), defender.getZ());
        areaEffectCloudEntity.setRadius(cloudRadius);
        areaEffectCloudEntity.setRadiusOnUse(0);
        areaEffectCloudEntity.setDurationOnUse(-cloudDurationPerUse);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setDuration(cloudDuration);
        areaEffectCloudEntity.setRadiusGrowth(0);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(effect,duration,amplification));
        defender.world.spawnEntity(areaEffectCloudEntity);
    }
}
