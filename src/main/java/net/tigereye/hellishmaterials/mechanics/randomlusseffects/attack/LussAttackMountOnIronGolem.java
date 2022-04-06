package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.registration.HMStatusEffects;

public class LussAttackMountOnIronGolem implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackMountOnIronGolem(Quality quality, int weight){
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
        IronGolemEntity golem = new IronGolemEntity(EntityType.IRON_GOLEM, defender.world);
        golem.setPos(defender.getX(),defender.getY(),defender.getZ());
        if(attacker != null){
            golem.setAttacker(attacker);
            golem.setAngryAt(attacker.getUuid());
            golem.setAngerTime(48000);
            golem.setTarget(attacker);
        }
        defender.world.spawnEntity(golem);
        defender.startRiding(golem,true);
    }
}
