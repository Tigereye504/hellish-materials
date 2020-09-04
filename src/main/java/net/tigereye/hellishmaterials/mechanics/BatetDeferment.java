package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.tigereye.hellishmaterials.mob_effect.BloodDebtInstance;
import net.tigereye.hellishmaterials.registration.HM_DamageSource;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.tigereye.hellishmaterials.registration.HM_StatusEffects;

public class BatetDeferment {

    public static final int REPAYMENT_PERIOD = 40;
    public static final float REPAYMENT_RATE = .1f;
    public static final float MINIMUM_REPAYMENT = 4f;

	public static float deferDamage(LivingEntity entity, DamageSource source, float amount) {
        float bloodDebtFactor = 0;
        if(source != HM_DamageSource.HM_BLOOD_DEBT){
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
            addBloodDebt(amount*bloodDebtFactor,entity);
            amount *= (1-bloodDebtFactor);
        }
		return amount;
    }

    private static void addBloodDebt(float amount, LivingEntity entity){
        if(amount > 0){
            if(entity.hasStatusEffect(HM_StatusEffects.HM_BLOODDEBT)){
                ((BloodDebtInstance)(entity.getStatusEffect(HM_StatusEffects.HM_BLOODDEBT))).addDebt(amount);
            }
            else{
                entity.addStatusEffect(newBloodDebtStatusEffectInstance(amount));
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
            ((BloodDebtInstance)entity.getStatusEffect(HM_StatusEffects.HM_BLOODDEBT)).removeDebt(amount);
        }
    }

    public static StatusEffectInstance newBloodDebtStatusEffectInstance(float debt) {
        StatusEffectInstance bloodDebt = new StatusEffectInstance(HM_StatusEffects.HM_BLOODDEBT, REPAYMENT_PERIOD*1000-1, 0);// false, true, true);
        ((BloodDebtInstance)bloodDebt).addDebt(debt);
        return bloodDebt;
    }

    public static StatusEffectInstance bloodDebtFromTag(CompoundTag tag) {
        float newdebt = 0;
        if (tag.contains("HM_BloodDebt")) {
           newdebt = tag.getFloat("HM_BloodDebt");
        }
  
        return BatetDeferment.newBloodDebtStatusEffectInstance(newdebt);
    }
}