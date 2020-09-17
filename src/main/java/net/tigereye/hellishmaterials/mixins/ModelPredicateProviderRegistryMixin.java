package net.tigereye.hellishmaterials.mixins;


import net.minecraft.client.item.ModelPredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import net.tigereye.hellishmaterials.registration.HM_Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ModelPredicateProviderRegistry.class)
public class ModelPredicateProviderRegistryMixin {

    @Shadow
    private static void register(Item item, Identifier id, ModelPredicateProvider provider){}

    static{
        register(HM_Items.LUCKSTONE, new Identifier("luck"), new ModelPredicateProvider() {
            private float luck = 0;
            private float luckroll = 0.5f;
            private long lastTickRolled = 0;

            public float call(ItemStack itemStack, ClientWorld clientWorld, LivingEntity livingEntity) {
                if(clientWorld != null){
                    if(clientWorld.getTime() - lastTickRolled >= 20){
                        lastTickRolled = clientWorld.getTime();
                        rollLuckstoneLuck(livingEntity);
                    }
                }
                return luck;
            }

            private void rollLuckstoneLuck(LivingEntity livingEntity){
                if (livingEntity != null) {
                    if (livingEntity instanceof PlayerEntity) {
                        luckroll = LussLuck.RandomFloatWithLuck(((PlayerEntity) livingEntity).getLuck());
                        System.out.println("Luckstone Rolled "+luckroll);
                    }
                }
                if(luckroll > .99f){
                    luck = 2;
                }
                else if(luckroll > .80f){
                    luck = 1;
                }
                else if(luckroll > .20f){
                    luck = 0;
                }
                else if(luckroll > .01f){
                    luck = -1;
                }
                else{
                    luck = -2;
                }
                System.out.println("Luckstone Diplay: "+luck);
            }
        });
    }
}
