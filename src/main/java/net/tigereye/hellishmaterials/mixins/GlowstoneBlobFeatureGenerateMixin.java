package net.tigereye.hellishmaterials.mixins;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.GlowstoneBlobFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.tigereye.hellishmaterials.registration.HMItems;
import net.tigereye.hellishmaterials.worldgen.WorldGenUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowstoneBlobFeature.class)
public class GlowstoneBlobFeatureGenerateMixin {
    @Inject(
        at = @At("TAIL"),
        method = "generate")
    public void HellishMaterialsGenerateLussAndBatet(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> info) {
        WorldGenUtil.GenerateLuss(context);
        WorldGenUtil.GenerateBatetCrystalGrowth(context);
    }
}
