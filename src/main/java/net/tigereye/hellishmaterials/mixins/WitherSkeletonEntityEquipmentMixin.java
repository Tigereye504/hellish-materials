package net.tigereye.hellishmaterials.mixins;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.random.Random;
import net.tigereye.hellishmaterials.Utils;
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
        method = "initEquipment"
    )
    private void HellishMaterialsPiglinInitializeMixin(Random random, LocalDifficulty difficulty, CallbackInfo info){
        LivingEntity lEntity = (LivingEntity) (Object) this;
        if (random.nextFloat() < HMConfig.WITHER_SKELETON_VULD_CHAMPION_CHANCE) {
            lEntity.equipStack(EquipmentSlot.HEAD , new ItemStack(HMItems.VULD_HELM));
            lEntity.equipStack(EquipmentSlot.CHEST , new ItemStack(HMItems.VULD_CHESTPLATE));
            lEntity.equipStack(EquipmentSlot.LEGS , new ItemStack(HMItems.VULD_LEGGINGS));
            lEntity.equipStack(EquipmentSlot.FEET , new ItemStack(HMItems.VULD_BOOTS));
            lEntity.equipStack(EquipmentSlot.MAINHAND , new ItemStack(HMItems.VULD_SWORD));
            lEntity.equipStack(EquipmentSlot.OFFHAND , new ItemStack(Items.SHIELD));
            lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,36000,1));
            lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,36000,1));
        }
        else{
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_HOE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_SHOVEL), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_PICKAXE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_AXE), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.VULD_SWORD), HMConfig.WITHER_SKELETON_VULD_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.HEAD, new ItemStack(HMItems.VULD_HELM), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.CHEST, new ItemStack(HMItems.VULD_CHESTPLATE), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.LEGS, new ItemStack(HMItems.VULD_LEGGINGS), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.FEET, new ItemStack(HMItems.VULD_BOOTS), HMConfig.WITHER_SKELETON_VULD_ARMOR_CHANCE);
        }
        
    }
}
