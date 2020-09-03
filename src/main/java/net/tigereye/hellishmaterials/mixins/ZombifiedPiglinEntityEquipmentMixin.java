package net.tigereye.hellishmaterials.mixins;

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
import net.tigereye.hellishmaterials.registration.HM_Config;
import net.tigereye.hellishmaterials.registration.HM_Items;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityEquipmentMixin {

    @Inject(
        at = @At("TAIL"),
        method = "initEquipment",
        cancellable = true)
    private void HellishMaterialsPiglinInitializeMixin(LocalDifficulty difficulty, CallbackInfo info){
        if (((ZombifiedPiglinEntity) (Object) this).world.random.nextFloat() < HM_Config.ZOMBIE_PIGLIN_BATET_CHAMPION_CHANCE) {
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.HEAD , new ItemStack(HM_Items.BATET_HELM));
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.CHEST , new ItemStack(HM_Items.BATET_CHESTPLATE));
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.LEGS , new ItemStack(HM_Items.BATET_LEGGINGS));
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.FEET , new ItemStack(HM_Items.BATET_BOOTS));
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.MAINHAND , new ItemStack(HM_Items.BATET_SWORD));
            ((ZombifiedPiglinEntity) (Object) this).equipStack(EquipmentSlot.OFFHAND , new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
            ((ZombifiedPiglinEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,36000,2));
            ((ZombifiedPiglinEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,36000,1));
            ((ZombifiedPiglinEntity) (Object) this).applyStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,36000,1));
        }
        else{
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.BATET_HOE), HM_Config.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.BATET_SHOVEL), HM_Config.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.BATET_PICKAXE), HM_Config.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.BATET_AXE), HM_Config.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.BATET_SWORD), HM_Config.ZOMBIE_PIGLIN_BATET_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.HEAD, new ItemStack(HM_Items.BATET_HELM), HM_Config.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.CHEST, new ItemStack(HM_Items.BATET_CHESTPLATE), HM_Config.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.LEGS, new ItemStack(HM_Items.BATET_LEGGINGS), HM_Config.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
            HM_EquipAtChance(EquipmentSlot.FEET, new ItemStack(HM_Items.BATET_BOOTS), HM_Config.ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE);
        }
        
    }

    private void HM_EquipAtChance(EquipmentSlot slot, ItemStack stack, float odds){
        if (((ZombifiedPiglinEntity) (Object) this).world.random.nextFloat() < odds) {
            ((ZombifiedPiglinEntity) (Object) this).equipStack(slot, stack);
         }
    }
}
