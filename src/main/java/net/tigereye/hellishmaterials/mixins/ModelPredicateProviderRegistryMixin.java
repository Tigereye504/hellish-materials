package net.tigereye.hellishmaterials.mixins;


import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.items.Luckstone;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    @Shadow
    private static void register(Item item, Identifier id, ModelPredicateProvider provider){}

    static{
        register(HMItems.LUCKSTONE, new Identifier(HellishMaterials.MODID, "luck"), new ModelPredicateProvider() {
            private float luck = 1;
            private long lastTick = 0;

            public float call(ItemStack itemStack, ClientWorld clientWorld, LivingEntity livingEntity) {
                if (clientWorld != null) {
                    if (clientWorld.getTime() != lastTick) {
                        lastTick = clientWorld.getTime();
                        //luck = assessLuckstoneLuck(itemStack, livingEntity);
                        CompoundTag tag = itemStack.getOrCreateTag();
                        if (tag.contains(Luckstone.DISPLAY_KEY)) {
                            luck = tag.getFloat(Luckstone.DISPLAY_KEY);
                        }
                        //System.out.println("Luckstone Diplay: "+luck);
                    }
                }
                return luck;
            }
        });
    }
}
