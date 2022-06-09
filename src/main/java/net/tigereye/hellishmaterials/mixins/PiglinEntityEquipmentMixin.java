package net.tigereye.hellishmaterials.mixins;

import net.minecraft.util.math.random.Random;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.tigereye.hellishmaterials.registration.HMConfig;

@Mixin(PiglinEntity.class)
public class PiglinEntityEquipmentMixin {

    @Inject(
        at = @At("HEAD"),
        method = "initEquipment"
    )
    private void HellishMaterialsPiglinInitializeMixin(Random random, LocalDifficulty localDifficulty, CallbackInfo ci){
        
        if (((PiglinEntity) (Object) this).isAdult()) {
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.LUSS_HOE), HMConfig.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.LUSS_SHOVEL), HMConfig.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.LUSS_PICKAXE), HMConfig.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.LUSS_AXE), HMConfig.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HMItems.LUSS_SWORD), HMConfig.PIGLIN_LUSS_TOOL_CHANCE);
            }
    }

    private void HM_EquipAtChance(EquipmentSlot slot, ItemStack stack, float odds){
        if (((PiglinEntity) (Object) this).world.random.nextFloat() < odds) {
            ((PiglinEntity) (Object) this).equipStack(slot, stack);
         }
    }
}
