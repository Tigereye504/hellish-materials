package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;

@Mixin(PlayerEntity.class)
public class PlayerEntityApplyDamageMixin {

    @ModifyVariable(at = @At("HEAD"), method = "applyDamage")
    public float HellishMaterialsPlayerApplyDamageMixin(float amount, DamageSource source) {
		    return BatetDeferment.deferDamage(((LivingEntity)(Object)this), source, amount);
    }
    
}