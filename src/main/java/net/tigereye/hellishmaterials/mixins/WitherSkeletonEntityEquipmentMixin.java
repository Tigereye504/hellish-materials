package net.tigereye.hellishmaterials.mixins;

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
import net.tigereye.hellishmaterials.registration.HM_Config;
import net.tigereye.hellishmaterials.registration.HM_Items;

@Mixin(WitherSkeletonEntity.class)
public class WitherSkeletonEntityEquipmentMixin {

    @Inject(
        at = @At("TAIL"),
        method = "initEquipment",
        cancellable = true)
    private void HellishMaterialsPiglinInitializeMixin(LocalDifficulty difficulty, CallbackInfo info){
        if (((WitherSkeletonEntity) (Object) this).world.random.nextFloat() < HM_Config.WITHER_SKELETON_VULD_CHAMPION_CHANCE) {
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.HEAD , new ItemStack(HM_Items.VULD_HELM));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.CHEST , new ItemStack(HM_Items.VULD_CHESTPLATE));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.LEGS , new ItemStack(HM_Items.VULD_LEGGINGS));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.FEET , new ItemStack(HM_Items.VULD_BOOTS));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.MAINHAND , new ItemStack(HM_Items.VULD_SWORD));
            ((WitherSkeletonEntity) (Object) this).equipStack(EquipmentSlot.OFFHAND , new ItemStack(Items.SHIELD));
            ((WitherSkeletonEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,36000,1));
            ((WitherSkeletonEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,36000,1));
        }
        else{
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.VULD_HOE), HM_Config.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.VULD_SHOVEL), HM_Config.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.VULD_PICKAXE), HM_Config.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.VULD_AXE), HM_Config.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.VULD_SWORD), HM_Config.WITHER_SKELETON_VULD_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.HEAD, new ItemStack(HM_Items.VULD_HELM), HM_Config.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.CHEST, new ItemStack(HM_Items.VULD_CHESTPLATE), HM_Config.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.LEGS, new ItemStack(HM_Items.VULD_LEGGINGS), HM_Config.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.FEET, new ItemStack(HM_Items.VULD_BOOTS), HM_Config.WITHER_SKELETON_VULD_ARMOR_CHANCE);
        }
        
    }

    private void HM_EquipAtChance(EquipmentSlot slot, ItemStack stack, float odds){
        if (((WitherSkeletonEntity) (Object) this).world.random.nextFloat() < odds) {
            ((WitherSkeletonEntity) (Object) this).equipStack(slot, stack);
         }
    }
}
