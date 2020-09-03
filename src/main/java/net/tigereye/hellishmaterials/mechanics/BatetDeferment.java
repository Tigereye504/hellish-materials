package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.tigereye.hellishmaterials.registration.HM_DamageSource;
import net.tigereye.hellishmaterials.registration.HM_Items;

public class BatetDeferment {

    public static final int REPAYMENT_PERIOD = 40;
    private static final float REPAYMENT_RATE = .1f;
    private static final float MINIMUM_REPAYMENT = 1f;

    
    

	public static float deferDamage(LivingEntity entity, DamageSource source, float amount) {
        float deferPerItem = amount/4;
        if(source != HM_DamageSource.HM_BLOOD_DEBT){
            ItemStack armor = entity.getEquippedStack(EquipmentSlot.HEAD);
            if(armor.getItem().isIn(HM_Items.TAG_BATET)){
                addBloodDebt(deferPerItem,armor);
                amount -= deferPerItem;
            }
            armor = entity.getEquippedStack(EquipmentSlot.CHEST);
            if(armor.getItem().isIn(HM_Items.TAG_BATET)){
                addBloodDebt(deferPerItem,armor);
                amount -= deferPerItem;
            }
            armor = entity.getEquippedStack(EquipmentSlot.LEGS);
            if(armor.getItem().isIn(HM_Items.TAG_BATET)){
                addBloodDebt(deferPerItem,armor);
                amount -= deferPerItem;
            }
            armor = entity.getEquippedStack(EquipmentSlot.FEET);
            if(armor.getItem().isIn(HM_Items.TAG_BATET)){
                addBloodDebt(deferPerItem,armor);
                amount -= deferPerItem;
            }
        }
		return amount;
	}
    
    private static void addBloodDebt(float amount, ItemStack armor){
        if(armor.getTag().contains("HMBloodDebt")){
            armor.getTag().putFloat("HMBloodDebt", armor.getTag().getFloat("HMBloodDebt")+amount);
        }
        else{
            armor.getTag().putFloat("HMBloodDebt", amount);
        }
    }

    private static float takeBloodDebt(ItemStack armor){
        if(armor.getTag() != null){
            if(armor.getTag().contains("HMBloodDebt")){
                float debt = armor.getTag().getFloat("HMBloodDebt");
                System.out.println("Armor Piece "+armor.getName().getString()+" Contains "+debt+" Blood Debt.\n");
                if(debt < .25f){
                    armor.getTag().remove("HMBloodDebt");
                    return debt;
                }
                float repayment = Math.max(MINIMUM_REPAYMENT,debt*REPAYMENT_RATE);
                armor.getTag().putFloat("HMBloodDebt", debt-repayment);
                return repayment;
            }
        }
        return 0;
    }

    public static void payBloodDebt(LivingEntity entity){
        float debtDamage = 0;
        debtDamage += takeBloodDebt(entity.getEquippedStack(EquipmentSlot.HEAD));
        debtDamage += takeBloodDebt(entity.getEquippedStack(EquipmentSlot.CHEST));
        debtDamage += takeBloodDebt(entity.getEquippedStack(EquipmentSlot.LEGS));
        debtDamage += takeBloodDebt(entity.getEquippedStack(EquipmentSlot.FEET));
        if(debtDamage > 0){
            System.out.println("Repaying "+debtDamage+" Blood Debt from\n");
            entity.damage(HM_DamageSource.HM_BLOOD_DEBT, debtDamage);
        }
    }

	public static void ForgiveDebts(LivingEntity entity) {
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.HEAD),0);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.CHEST),0);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.LEGS),0);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.FEET),0);
    }

    public static void ForgiveDebts(LivingEntity entity, float amount) {
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.HEAD),GetBloodDebt(entity.getEquippedStack(EquipmentSlot.HEAD))-amount/4);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.CHEST),GetBloodDebt(entity.getEquippedStack(EquipmentSlot.CHEST))-amount/4);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.LEGS),GetBloodDebt(entity.getEquippedStack(EquipmentSlot.LEGS))-amount/4);
        SetBloodDebt(entity.getEquippedStack(EquipmentSlot.FEET),GetBloodDebt(entity.getEquippedStack(EquipmentSlot.FEET))-amount/4);
    }
    
    public static float GetBloodDebt(ItemStack item){
        if(item.getTag() != null){
            if(item.getTag().contains("HMBloodDebt")){
                return item.getTag().getFloat("HMBloodDebt");
            }
        }
        return 0;
    }

    public static boolean SetBloodDebt(ItemStack item, float debt){
        if(item.getTag() != null){
            if(debt <= 0){
                if(item.getTag().contains("HMBloodDebt")){
                    item.getTag().remove("HMBloodDebt");
                }
            }
            else{
                item.getTag().putFloat("HMBloodDebt", debt);
            }
            return true;
        }
        else{
            return false;
        }
    }
}