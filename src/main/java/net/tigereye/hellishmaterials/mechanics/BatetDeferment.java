package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.hellishmaterials.mob_effect.BloodDebtInstance;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.tigereye.hellishmaterials.registration.HM_StatusEffects;

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
        if(armor.getItem().isIn(HM_Items.TAG_BATET)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.CHEST);
        if(armor.getItem().isIn(HM_Items.TAG_BATET)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.LEGS);
        if(armor.getItem().isIn(HM_Items.TAG_BATET)){
            bloodDebtFactor += .25;
        }
        armor = entity.getEquippedStack(EquipmentSlot.FEET);
        if(armor.getItem().isIn(HM_Items.TAG_BATET)){
            bloodDebtFactor += .25;
        }
        return bloodDebtFactor;
    }

    private static void addBloodDebt(LivingEntity entity, float amount){
        if(amount > 0){
            if(entity.hasStatusEffect(HM_StatusEffects.HM_BLOODDEBT)){
                ((BloodDebtInstance)(entity.getStatusEffect(HM_StatusEffects.HM_BLOODDEBT))).addDebt(amount);
            }
            else{
                    entity.addStatusEffect(HM_StatusEffects.newBloodDebtStatusEffectInstance(amount));
            }
        }
    }

	public static void ForgiveDebts(LivingEntity entity) {
        if(entity.hasStatusEffect(HM_StatusEffects.HM_BLOODDEBT)){
            entity.removeStatusEffect(HM_StatusEffects.HM_BLOODDEBT);
        }
    }

    public static void ForgiveDebts(LivingEntity entity, float amount) {
        if(entity.hasStatusEffect(HM_StatusEffects.HM_BLOODDEBT)){
            if(((BloodDebtInstance)entity.getStatusEffect(HM_StatusEffects.HM_BLOODDEBT)).removeDebt(amount) == 0){
                entity.removeStatusEffect(HM_StatusEffects.HM_BLOODDEBT);
            }
        }
    }

}