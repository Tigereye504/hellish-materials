package net.tigereye.hellishmaterials.mixins;

import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.tigereye.hellishmaterials.registration.HMConfig;

@Mixin(WitherSkeletonEntity.class)
public class WitherSkeletonEntityEquipmentMixin {

    @Inject(
        at = @At("TAIL"),
        method = "initEquipment",
        cancellable = true)
    private void HellishMaterialsPiglinInitializeMixin(LocalDifficulty difficulty, CallbackInfo info){
        if (((WitherSkeletonEntity) (Object) this).world.random.nextFloat() < HMConfig.WITHER_SKELETON_VULD_CHAMPION_CHANCE) {
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.HEAD , new ItemStack(HMItems.VULD_HELM));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.CHEST , new ItemStack(HMItems.VULD_CHESTPLATE));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.LEGS , new ItemStack(HMItems.VULD_LEGGINGS));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.FEET , new ItemStack(HMItems.VULD_BOOTS));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.MAINHAND , new ItemStack(HMItems.VULD_SWORD));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.OFFHAND , new ItemStack(Items.SHIELD));
            ((WitherSkeletonEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,36000,1));
            ((WitherSkeletonEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,36000,1));
        }
        else{
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_HOE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_SHOVEL), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_PICKAXE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_AXE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_SWORD), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.HEAD, new ItemStack(HMItems.VULD_HELM), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.CHEST, new ItemStack(HMItems.VULD_CHESTPLATE), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.LEGS, new ItemStack(HMItems.VULD_LEGGINGS), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.FEET, new ItemStack(HMItems.VULD_BOOTS), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
        }
        
    }

    private void HM_EquipAtChance(EquipmentSlot slot, ItemStack stack, float odds){
        if (((WitherSkeletonEntity) (Object) this).world.random.nextFloat() < odds) {
            ((WitherSkeletonEntity) (Object) this).equipStack(slot, stack);
         }
    }
}
