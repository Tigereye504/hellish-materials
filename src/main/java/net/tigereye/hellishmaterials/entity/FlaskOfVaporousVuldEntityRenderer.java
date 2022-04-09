package net.tigereye.hellishmaterials.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.registration.HMItems;

public class FlaskOfVaporousVuldEntityRenderer extends EntityRenderer<FlaskOfVaporousVuldEntity> {

    public static final ItemStack STACK = new ItemStack(HMItems.FLASK_OF_VAPOROUS_VULD);

    public FlaskOfVaporousVuldEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public void render(FlaskOfVaporousVuldEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient.getInstance().getItemRenderer().renderItem(STACK, ModelTransformation.Mode.FIXED,light, OverlayTexture.DEFAULT_UV, matrices,vertexConsumers,283245);
    }

    @Override
    public Identifier getTexture(FlaskOfVaporousVuldEntity entity) {
        return new Identifier(HellishMaterials.MODID,"flask_of_vaporous_vuld");
    }
}
