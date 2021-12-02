package net.tigereye.hellishmaterials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.tigereye.hellishmaterials.items.Luckstone;
import net.tigereye.hellishmaterials.registration.HMItems;

public class HellishMaterialsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(HMItems.LUCKSTONE, new Identifier("luck"), (itemStack, clientWorld, livingEntity, seed) -> {
            float luck = 1;
            NbtCompound tag = itemStack.getOrCreateNbt();
            if (tag.contains(Luckstone.DISPLAY_KEY)) {
                luck = tag.getFloat(Luckstone.DISPLAY_KEY);
            }
            if(HellishMaterials.DEBUG) {
                System.out.println("Luckstone Diplay: " + luck/5);
            }
            return luck/5;
        });
    }
}
