package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.mob_effect.BloodDebtInstance;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import net.tigereye.hellishmaterials.registration.HMStatusEffects;

public class BatetDeferment {

    public static final int REPAYMENT_PERIOD = 40;
    public static final float REPAYMENT_RATE = .1f;
    public static final float MINIMUM_REPAYMENT = 4f;
    public static final float BLOOD_THEFT_FACTOR = .2f;

    public static float deferDamage(LivingEntity entity, float amount) {
        return deferDamage(entity, amount, findBloodDebtFactor(entity));
    }

    public static float deferDamage(LivingEntity entity, float amount, float bloodDebtFactor) {
        addBloodDebt(entity,amount*bloodDebtFactor);
        amount *= (1-bloodDebtFactor);
		return amount;
    }

    public static float findBloodDebtFactor(LivingEntity entity){
        float bloodDebtFactor = 0;
        ItemStack armor = entity.getEquippedStack(EquipmentSlot.HEAD);
        if(Utils.isBatet(armor)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.CHEST);
        if(Utils.isBatet(armor)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.LEGS);
        if(Utils.isBatet(armor)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.FEET);
        if(Utils.isBatet(armor)){
            bloodDebtFactor += .25;
        }
        return bloodDebtFactor;
    }

    public static void addBloodDebt(LivingEntity entity, float amount){
        if(amount > 0){
            if(entity.hasStatusEffect(HMStatusEffects.HM_BLOODDEBT)){
                ((BloodDebtInstance)(entity.getStatusEffect(HMStatusEffects.HM_BLOODDEBT))).addDebt(amount);
            }
            else{
                entity.addStatusEffect(HMStatusEffects.newBloodDebtStatusEffectInstance(amount));
            }
            if(!entity.hasStatusEffect(HMStatusEffects.HM_BLOODDEBT)){
                if(entity instanceof PlayerEntity){
                    ((PlayerEntity)entity).sendMessage(new LiteralText("You cannot cheat Death so easily!"),true);
                }
                entity.damage(HMDamageSource.HM_BLOOD_DEBT,amount);
            }
        }
    }

	public static void forgiveDebts(LivingEntity entity) {
        if(entity.hasStatusEffect(HMStatusEffects.HM_BLOODDEBT)){
            entity.removeStatusEffect(HMStatusEffects.HM_BLOODDEBT);
        }
    }

    public static void forgiveDebts(LivingEntity entity, float amount) {
        if(entity.hasStatusEffect(HMStatusEffects.HM_BLOODDEBT)){
            if(((BloodDebtInstance)entity.getStatusEffect(HMStatusEffects.HM_BLOODDEBT)).removeDebt(amount) == 0){
                entity.removeStatusEffect(HMStatusEffects.HM_BLOODDEBT);
            }
        }
    }

}