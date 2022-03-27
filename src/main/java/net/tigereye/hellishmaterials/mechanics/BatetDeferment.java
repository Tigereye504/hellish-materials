package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.registration.HMDamageSource;

public class BatetDeferment {

    public static final int REPAYMENT_PERIOD = 10;
    public static final float REPAYMENT_RATE = .05f;
    public static final float MINIMUM_REPAYMENT = 1f;
    public static final float BLOOD_THEFT_FACTOR = .2f;

    public static float calculateRepayment(float bloodDebt){
        float payment;
        if(bloodDebt <= BatetDeferment.MINIMUM_REPAYMENT){
            payment = bloodDebt;
        }
        else{
            payment = bloodDebt * BatetDeferment.REPAYMENT_RATE;
            if(payment < BatetDeferment.MINIMUM_REPAYMENT){
                payment = BatetDeferment.MINIMUM_REPAYMENT;
            }
        }
        return payment;
    }

    public static float deferDamage(LivingEntity entity, float amount) {
        return deferDamage(entity, amount, findBloodDebtFactor(entity));
    }

    public static float deferDamage(LivingEntity entity, float amount, float bloodDebtFactor) {
        addBloodDebt((BloodDebtTracker)entity,amount*bloodDebtFactor);
        amount *= (1-bloodDebtFactor);
		return amount;
    }

    public static float findBloodDebtFactor(LivingEntity entity){
        float bloodDebtFactor = 0;
        //TODO: check for 'guts' status effect
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

    public static void addBloodDebt(BloodDebtTracker entity, float amount){
        entity.setBloodDebt(entity.getBloodDebt()+amount);
    }

	public static void forgiveDebts(BloodDebtTracker entity) {
        entity.setBloodDebt(0);
    }

    public static void forgiveDebts(BloodDebtTracker entity, float amount) {
        entity.setBloodDebt(Math.max(0,entity.getBloodDebt() - amount));
    }

    public static void takeLife(LivingEntity entity, float dmg){
        if(entity.getHealth() > dmg) {
            entity.setHealth(entity.getHealth() - dmg);
        }
        else{
            entity.setHealth(Float.MIN_VALUE);
            entity.setAbsorptionAmount(0);
            entity.damage(HMDamageSource.HM_BLOOD_DEBT, dmg);
        }
    }
}