package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import net.tigereye.hellishmaterials.registration.HMItems;

@Mixin(PlayerEntity.class)
public class PlayerEntityApplyDamageMixin {
    /*
    @ModifyVariable(at = @At("HEAD"), method = "applyDamage")
    public float HellishMaterialsPlayerApplyDamageMixin(float amount, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity){
            if(((LivingEntity)(source.getAttacker())).getStackInHand(((LivingEntity)(source.getAttacker())).getActiveHand()).getItem().isIn(HM_Items.TAG_BATET)){
                BatetDeferment.ForgiveDebts((LivingEntity)source.getAttacker(),amount*BatetDeferment.BLOOD_THEFT_FACTOR);
            }
        }
        if(source != HM_DamageSource.HM_BLOOD_DEBT && source != HM_DamageSource.HM_MAGIC_BLOOD_DEBT){
            amount = BatetDeferment.deferDamage(((LivingEntity)(Object)this), amount,source.bypassesArmor());
        }
        return amount;
    }
    */

    //protected void applyDamage(DamageSource source, float amount) {
    //    if (!this.isInvulnerableTo(source)) {
    //          amount = this.applyArmorToDamage(source, amount);
    //          amount = this.applyEnchantmentsToDamage(source, amount);
    //          float f = amount;
    //          amount = Math.max(amount - this.getAbsorptionAmount(), 0.0F);
    //          this.setAbsorptionAmount(this.getAbsorptionAmount() - (f - amount));
    //          float g = f - amount;
    //          if (g > 0.0F && g < 3.4028235E37F) {
    //              this.increaseStat(Stats.DAMAGE_ABSORBED, Math.round(g * 10.0F));
    //          }
    @ModifyVariable(at = @At(value = "CONSTANT",ordinal = 2,args = "floatValue=0.0F"), method = "applyDamage")
    public float HellishMaterialsPlayerApplyDamageMixin(float amount, DamageSource source) {
        if(source.getAttacker() instanceof LivingEntity){
            if(((LivingEntity)(source.getAttacker())).getStackInHand(((LivingEntity)(source.getAttacker())).getActiveHand()).getItem().isIn(HMItems.TAG_BATET)){
                BatetDeferment.forgiveDebts((LivingEntity)source.getAttacker(),amount*BatetDeferment.BLOOD_THEFT_FACTOR);
            }
        }
        if(source != HMDamageSource.HM_BLOOD_DEBT){
            amount = BatetDeferment.deferDamage(((LivingEntity)(Object)this), amount);
        }
        return amount;
    }
    //          if (amount != 0.0F) {
    //              this.addExhaustion(source.getExhaustion());
    //              float h = this.getHealth();
    //              this.setHealth(this.getHealth() - amount);
    //              this.getDamageTracker().onDamage(source, h, amount);
    //              if (amount < 3.4028235E37F) {
    //                  this.increaseStat(Stats.DAMAGE_TAKEN, Math.round(amount * 10.0F));
    //              }
    //          }
    //      }
    //  }
}