package net.tigereye.hellishmaterials.mixins;


import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.tigereye.hellishmaterials.CustomModelPredicateProvider;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    @Shadow
    private static void register(Item item, Identifier id, UnclampedModelPredicateProvider provider){}

    static{
        register(HMItems.LUCKSTONE, new Identifier(HellishMaterials.MODID, "luck"), new CustomModelPredicateProvider());
    }

}
