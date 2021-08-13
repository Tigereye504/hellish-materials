package net.tigereye.hellishmaterials.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityApplyDamageMixin {

    //@ModifyVariable(at = @At("HEAD"), method = "applyDamage")
    @ModifyVariable(at = @At(value = "CONSTANT",ordinal = 2,args = "floatValue=0.0F"), method = "applyDamage")
    public float HellishMaterialsApplyDamageMixin(float amount, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity){
            if(Utils.isBatet(((LivingEntity) (source.getAttacker())).getStackInHand(((LivingEntity)(source.getAttacker())).getActiveHand()))) {
                BatetDeferment.forgiveDebts((LivingEntity)source.getAttacker(),amount*BatetDeferment.BLOOD_THEFT_FACTOR);
            }
        }
		if(source != HMDamageSource.HM_BLOOD_DEBT && source != DamageSource.OUT_OF_WORLD){
            amount = BatetDeferment.deferDamage(((LivingEntity)(Object)this), amount);
        }
        return amount;
    }
    
    @Inject(at = @At("HEAD"), method = "applyArmorToDamage", cancellable = true)
    public void HellishMaterialsApplyArmorToDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info)
    {
        if(source == HMDamageSource.HM_BLOOD_DEBT){
            info.setReturnValue(amount);
        }
    }

    @Inject(at = @At("HEAD"), method = "applyEnchantmentsToDamage", cancellable = true)
    public void HellishMaterialsApplyEnchantmentsToDamageMixin(DamageSource source, float amount, CallbackInfoReturnable<Float> info)
    {
        if(source == HMDamageSource.HM_BLOOD_DEBT){
            info.setReturnValue(amount);
        }
    }
}