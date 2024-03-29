package net.tigereye.hellishmaterials;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.tigereye.hellishmaterials.entity.FlaskOfVaporousVuldEntity;
import net.tigereye.hellishmaterials.entity.FlaskOfVaporousVuldEntityRenderer;
import net.tigereye.hellishmaterials.items.luss.Luckstone;
import net.tigereye.hellishmaterials.registration.HMEntities;
import net.tigereye.hellishmaterials.registration.HMGUI;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.UUID;

public class HellishMaterialsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HMGUI.register();
        BlockRenderLayerMap.INSTANCE.putBlock(HMItems.VAPOROUS_VULD, RenderLayer.getCutout());
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

        EntityRendererRegistry.register(HMEntities.FLASK_OF_VAPOROUS_VULD_ENTITY, FlaskOfVaporousVuldEntityRenderer::new);
        ClientSidePacketRegistryImpl.INSTANCE.register(FlaskOfVaporousVuldEntity.SPAWN_PACKET,((context, packet) -> {
            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int id = packet.readInt();
            UUID uuid = packet.readUuid();

            context.getTaskQueue().execute(() -> {
                FlaskOfVaporousVuldEntity proj = new FlaskOfVaporousVuldEntity(MinecraftClient.getInstance().world, x,y,z,id,uuid);
                MinecraftClient.getInstance().world.addEntity(id,proj);
            });
        }));
    }
}
