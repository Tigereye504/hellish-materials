package net.tigereye.hellishmaterials.mixins;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {

    @Shadow
    private static void registerPotionRecipe(Potion input, Item item, Potion output){}

    @Inject(
            at = @At("HEAD"),
            method = "registerDefaults")
    private static void HMRegisterDefaultsMixin(CallbackInfo ci) {
        registerPotionRecipe(Potions.AWKWARD, HMItems.LUSS_DUST, Potions.LUCK);
        registerPotionRecipe(Potions.LUCK, Items.REDSTONE, HMItems.LONG_LUCK);
        registerPotionRecipe(Potions.LUCK, Items.GLOWSTONE_DUST, HMItems.STRONG_LUCK);
    }
}
