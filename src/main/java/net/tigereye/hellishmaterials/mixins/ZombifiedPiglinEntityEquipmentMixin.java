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
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.tigereye.hellishmaterials.registration.HMConfig;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityEquipmentMixin {

    @Inject(
        at = @At("TAIL"),
        method = "initEquipment"
    )
    private void HellishMaterialsPiglinInitializeMixin(Random random, LocalDifficulty localDifficulty, CallbackInfo ci){
        LivingEntity lEntity = (LivingEntity) (Object) this;
        if (random.nextFloat() < HMConfig.ZOMBIE_PIGLIN_BATET_CHAMPION_CHANCE) {
            lEntity.equipStack(EquipmentSlot.HEAD , new ItemStack(HMItems.BATET_HELM));
            lEntity.equipStack(EquipmentSlot.CHEST , new ItemStack(HMItems.BATET_CHESTPLATE));
            lEntity.equipStack(EquipmentSlot.LEGS , new ItemStack(HMItems.BATET_LEGGINGS));
            lEntity.equipStack(EquipmentSlot.FEET , new ItemStack(HMItems.BATET_BOOTS));
            lEntity.equipStack(EquipmentSlot.MAINHAND , new ItemStack(HMItems.BATET_SWORD));
            lEntity.equipStack(EquipmentSlot.OFFHAND , new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
            lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,36000,2));
            lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,36000,1));
            lEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,36000,1));
        }
        else{
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.BATET_HOE), HMConfig.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.BATET_SHOVEL), HMConfig.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.BATET_PICKAXE), HMConfig.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.BATET_AXE), HMConfig.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.MAINHAND, new ItemStack(HMItems.BATET_SWORD), HMConfig.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.HEAD, new ItemStack(HMItems.BATET_HELM), HMConfig.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.CHEST, new ItemStack(HMItems.BATET_CHESTPLATE), HMConfig.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.LEGS, new ItemStack(HMItems.BATET_LEGGINGS), HMConfig.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            Utils.GenerateEquipmentAtChance(random, lEntity, EquipmentSlot.FEET, new ItemStack(HMItems.BATET_BOOTS), HMConfig.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
        }
        
    }
}
