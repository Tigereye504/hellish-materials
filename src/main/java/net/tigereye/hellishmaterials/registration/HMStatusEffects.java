package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.mob_effect.BloodDebtInstance;
import net.tigereye.hellishmaterials.mob_effect.HM_StatusEffect;

public class HMStatusEffects {
    
    public static StatusEffect HM_BLOODDEBT = new HM_StatusEffect(StatusEffectType.HARMFUL, 104562){
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return duration % BatetDeferment.REPAYMENT_PERIOD == 0;
        }

        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if(!(entity.world.isClient)){
                BloodDebtInstance instance = (BloodDebtInstance)entity.getStatusEffect(HM_BLOODDEBT);
                float dmg = instance.drawRepayment();
                System.out.println("Repaying "+dmg+" Blood Debt\n");
                entity.damage(HMDamageSource.HM_BLOOD_DEBT, dmg);
                if(instance.getDebt() == 0){
                    System.out.println("Blood Debt Resolved\n");
                    entity.removeStatusEffect(HM_BLOODDEBT);
                }
            }
        }
    };

    public static void register(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "blood_debt"), HM_BLOODDEBT);
    }

    public static StatusEffectInstance newBloodDebtStatusEffectInstance(float debt) {
        StatusEffectInstance bloodDebt = new StatusEffectInstance(HMStatusEffects.HM_BLOODDEBT,
                BatetDeferment.REPAYMENT_PERIOD * 1000 - 1, 0, false, true, true);
        ((BloodDebtInstance)bloodDebt).addDebt(debt);
        return bloodDebt;
    }
}
