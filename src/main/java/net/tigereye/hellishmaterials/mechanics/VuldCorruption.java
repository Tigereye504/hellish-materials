package net.tigereye.hellishmaterials.mechanics;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.tigereye.hellishmaterials.registration.HMItems;

public class VuldCorruption {
    public static int countVuldArmor(LivingEntity entity){
        int count = 0;
        if(isItemVuld(entity.getEquippedStack(EquipmentSlot.HEAD).getItem())){count++;}
        if(isItemVuld(entity.getEquippedStack(EquipmentSlot.CHEST).getItem())){count++;}
        if(isItemVuld(entity.getEquippedStack(EquipmentSlot.LEGS).getItem())){count++;}
        if(isItemVuld(entity.getEquippedStack(EquipmentSlot.FEET).getItem())){count++;}
        return count;
    }

    public static void inflictCumulativeWither(LivingEntity entity, int ticksToAdd, int severity, int minimumTicks){
        if(entity.hasStatusEffect(StatusEffects.WITHER)){
            ticksToAdd += entity.getStatusEffect(StatusEffects.WITHER).getDuration();
            severity = Math.max(severity, entity.getStatusEffect(StatusEffects.WITHER).getAmplifier());
        }
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,Math.max(ticksToAdd,minimumTicks),severity));
    }
    
    private static boolean isItemVuld(Item item){
        if(item == null){
            return false;
        }
        return item.isIn(HMItems.TAG_VULD);
    }

}