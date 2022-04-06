package net.tigereye.hellishmaterials.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.interfaces.HM_PlayerEntity;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityApplyDamageMixin implements HM_PlayerEntity {
    private float HM_lastAttackCooldownProgressResult;

    @ModifyVariable(at = @At(value = "CONSTANT",ordinal = 2,args = "floatValue=0.0F"), method = "applyDamage")
    public float HellishMaterialsPlayerApplyDamageMixin(float amount, DamageSource source) {
        return Utils.applyDamageEffects((PlayerEntity)(Object)this,amount,source);
    }

    @Inject(at = @At(value = "RETURN"), method = "getAttackCooldownProgress")
    public void HellishMaterialsPlayerEntityGetAttackCooldownProgressMixin(float baseTime, CallbackInfoReturnable<Float> cir){
        HM_lastAttackCooldownProgressResult = cir.getReturnValue();
    }
    @Override
    public float getLastAttackCooldownProgressResult() {
        return HM_lastAttackCooldownProgressResult;
    }
}