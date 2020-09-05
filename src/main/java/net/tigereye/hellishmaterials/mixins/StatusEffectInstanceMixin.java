package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.CompoundTag;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.mob_effect.BloodDebtInstance;
import net.tigereye.hellishmaterials.registration.HM_StatusEffects;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin implements BloodDebtInstance{
    private float debt = 0;

    @Inject(
        at = @At("HEAD"),
        method = "fromTag",
        cancellable = true)
    private static void RedirectToBloodDebtFromTagMixin(CompoundTag tag, CallbackInfoReturnable<StatusEffectInstance> info){
        if(StatusEffect.byRawId(tag.getByte("Id")) == HM_StatusEffects.HM_BLOODDEBT){
            info.setReturnValue(BatetDeferment.bloodDebtFromTag(tag));
        }
    }

    @ModifyVariable(
        at = @At("HEAD"),
        method = "toTag")
    public CompoundTag toTag(CompoundTag tag) {
        tag.putFloat("HM_BloodDebt", debt);
        return tag;
    }
     

    public float addDebt(float amount){
        debt += amount;
        return debt;
    }

    public float removeDebt(float amount){
        debt -= amount;
        if(debt < 0){debt = 0;}
        return debt;
    }

    public float drawRepayment(){
        float payment = 0;
        if(debt <= BatetDeferment.MINIMUM_REPAYMENT){
            payment = debt;
            debt = 0;
        }
        else{
            payment = debt * BatetDeferment.REPAYMENT_RATE;
            if(payment < BatetDeferment.MINIMUM_REPAYMENT){
                payment = BatetDeferment.MINIMUM_REPAYMENT;
            }
            debt -= payment;
        }
        return payment;
    }

    public float getDebt(){
        return debt;
    }

    @Override
    public void setDebt(float debt) {
        this.debt = debt;

    }
}
