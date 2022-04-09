package net.tigereye.hellishmaterials.registration;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.interfaces.BloodDebtTracker;

public class HMGUI {
    public static final Identifier ENDEBTED_HEARTS = new Identifier(HellishMaterials.MODID,"textures/gui/endebted_hearts.png");
    public static final Identifier ENDEBTED_FULL_HEART = new Identifier(HellishMaterials.MODID,"textures/gui/endebted_full_heart.png");
    public static final Identifier ENDEBTED_HALF_HEART = new Identifier(HellishMaterials.MODID,"textures/gui/endebted_half_heart.png");

    public static void register(){
        HudRenderCallback.EVENT.register((matrixStack,delta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = client.player;
            if(!(player.isCreative() || player.isSpectator())) {
                matrixStack.push();
                RenderSystem._setShaderTexture(0, ENDEBTED_HEARTS);
                int scaledWidth = client.getWindow().getScaledWidth();
                int scaledHeight = client.getWindow().getScaledHeight();


                float maxHealth = Math.max((float) player.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH), 2);
                int absorption = MathHelper.ceil(player.getAbsorptionAmount());
                int lineMidValue = MathHelper.ceil((maxHealth + (float) absorption) / 2.0F / 10.0F);

                int x = scaledWidth / 2 - 91;
                int y = scaledHeight - 39;
                int lines = Math.max(10 - (lineMidValue - 2), 3);
                int displayedHealth = MathHelper.ceil(player.getHealth());
                int displayedBloodDebt = Math.min(displayedHealth, MathHelper.ceil(((BloodDebtTracker) player).getBloodDebt()));

                int i = 9 * (player.world.getLevelProperties().isHardcore() ? 5 : 0);
                int j = MathHelper.ceil((double) maxHealth / 2.0D);
                int k = MathHelper.ceil((double) absorption / 2.0D);
                int l = j * 2;


                for (int m = j + k - 1; m >= 0; --m) {
                    int n = m / 10;
                    int o = m % 10;
                    int posX = x + o * 8;
                    int posY = y - n * lines;

                    int r = m * 2;

                    if (r < displayedBloodDebt) {
                        if (r + 1 == displayedBloodDebt) {
                            DrawableHelper.drawTexture(matrixStack, posX, posY, 9, 0, 9, 9, 18, 9);
                        } else {
                            DrawableHelper.drawTexture(matrixStack, posX, posY, 0, 0, 9, 9, 18, 9);
                        }
                        //this.drawHeart(matrixStack, heartType, posX, posY, i, false, isHalfHeart);
                    }
                }
                matrixStack.pop();
            }
        });
    }
}
