package net.tigereye.hellishmaterials.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;
import net.tigereye.hellishmaterials.mechanics.BatetDeferment;
import net.tigereye.hellishmaterials.registration.HMDamageSource;
import net.tigereye.hellishmaterials.registration.HMStatusEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements BloodDebtTracker {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean isDead();

    @Shadow public abstract boolean isAlive();

    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    private static final TrackedData<Float> HM_BLOODDEBT = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);
    int HM_BloodDebtTimer = 0;
    boolean HM_BloodDebtActiveTick = false;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("TAIL"), method = "initDataTracker")
    public void HellishMaterialsInitDataTrackerMixin(CallbackInfo info){
        this.dataTracker.startTracking(HM_BLOODDEBT,0f);
    }

    @ModifyVariable(at = @At(value = "CONSTANT",ordinal = 2,args = "floatValue=0.0F"), method = "applyDamage")
    public float HellishMaterialsApplyDamageMixin(float amount, DamageSource source) {
        float bleedFactor = 0;
        if(this.hasStatusEffect(HMStatusEffects.BLEEDING)){
            bleedFactor += (this.getStatusEffect(HMStatusEffects.BLEEDING).getAmplifier()+1)*amount/4;
        }
        if(source.getAttacker() instanceof LivingEntity){
            if(Utils.isBatet(((LivingEntity) (source.getAttacker())).getStackInHand(((LivingEntity)(source.getAttacker())).getActiveHand()))) {
                bleedFactor += .25f;
            }
        }
        BatetDeferment.addBloodDebt(this,amount * bleedFactor);

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

    @Inject(
            at = @At("HEAD"),
            method = "baseTick")
    private void HellishMaterialsBaseTickMixin(CallbackInfo info){
        float blooddebt = getBloodDebt();
        if(blooddebt > 0) {
            if(isDead()){
                setBloodDebt(0);
                HM_BloodDebtActiveTick = false;
            }
            else if (!this.world.isClient()){
                HM_BloodDebtTimer++;
                float repaymentPeriodMultiplier = 1;
                if (this.hasStatusEffect(HMStatusEffects.GUTS))
                    repaymentPeriodMultiplier += (this.getStatusEffect(HMStatusEffects.GUTS).getAmplifier() + 1) * .5;
                if (this.hasStatusEffect(HMStatusEffects.BLEEDING))
                    repaymentPeriodMultiplier *= 2f / (this.getStatusEffect(HMStatusEffects.BLEEDING).getAmplifier() + 3);
                if (HM_BloodDebtTimer >= BatetDeferment.REPAYMENT_PERIOD * repaymentPeriodMultiplier) {
                    HM_BloodDebtTimer = 0;
                    HM_BloodDebtActiveTick = true;
                    float bloodToll = BatetDeferment.calculateRepayment(blooddebt);
                    if(HellishMaterials.DEBUG)
                        System.out.println("Repaying " + bloodToll + " Blood Debt\n");
                    BatetDeferment.takeLife(((LivingEntity) (Object) this), bloodToll);
                    blooddebt -= bloodToll;
                    if (blooddebt <= 0 && HellishMaterials.DEBUG) {
                        System.out.println("Blood Debt Resolved\n");
                    }
                    setBloodDebt(blooddebt);
                } else {
                    HM_BloodDebtActiveTick = false;
                }
            }
        }
        else{
            HM_BloodDebtTimer = 0;
            HM_BloodDebtActiveTick = false;
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "dropEquipment")
    private void HellishMaterialsDropEquipmentMixin(CallbackInfo info){
        setBloodDebt(0);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound tag, CallbackInfo callbackInfo) {
        setBloodDebt(tag.getFloat("HM_BloodDebt"));
        HM_BloodDebtTimer = tag.getInt("HM_BloodDebtTimer");
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound tag, CallbackInfo callbackInfo) {
        tag.putFloat("HM_BloodDebt", getBloodDebt());
        tag.putInt("HM_BloodDebtTimer", HM_BloodDebtTimer);
    }

    @Override
    public boolean isBloodDebtTick(){
        return HM_BloodDebtActiveTick;
    }

    @Override
    public float getBloodDebt() {
        return this.dataTracker.get(HM_BLOODDEBT);
    }

    @Override
    public void setBloodDebt(float debt) {
        this.dataTracker.set(HM_BLOODDEBT,debt);
    }

    @Shadow
    protected void initDataTracker() {

    }

    @Shadow
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Shadow
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Shadow
    public Packet<?> createSpawnPacket() {
        return null;
    }
}