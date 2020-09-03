package net.tigereye.hellishmaterials.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.tigereye.hellishmaterials.registration.HM_Config;
import net.tigereye.hellishmaterials.registration.HM_Items;

@Mixin(PiglinEntity.class)
public class PiglinEntityEquipmentMixin {

    @Inject(
        at = @At("HEAD"),
        method = "initEquipment",
        cancellable = true)
    private void HellishMaterialsPiglinInitializeMixin(LocalDifficulty difficulty, CallbackInfo info){
        
        if (((PiglinEntity) (Object) this).isAdult()) {
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.LUSS_HOE), HM_Config.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.LUSS_SHOVEL), HM_Config.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.LUSS_PICKAXE), HM_Config.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.LUSS_AXE), HM_Config.PIGLIN_LUSS_TOOL_CHANCE);
            HM_EquipAtChance(EquipmentSlot.MAINHAND, new ItemStack(HM_Items.LUSS_SWORD), HM_Config.PIGLIN_LUSS_TOOL_CHANCE);
            }
    }

    private void HM_EquipAtChance(EquipmentSlot slot, ItemStack stack, float odds){
        if (((PiglinEntity) (Object) this).world.random.nextFloat() < odds) {
            ((PiglinEntity) (Object) this).equipStack(slot, stack);
         }
    }
}
