package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;

@Mixin(LivingEntity.class)
public class LivingEntityApplyDamageMixin {

    @ModifyVariable(at = @At("HEAD"), method = "applyDamage")
    public float HellishMaterialsApplyDamageMixin(float amount, DamageSource source) {
		    return BatetDeferment.deferDamage(((LivingEntity)(Object)this), source, amount);
    }
    
}