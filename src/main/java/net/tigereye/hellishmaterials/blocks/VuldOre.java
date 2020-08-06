package net.tigereye.hellishmaterials.blocks;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;

public class VuldOre extends Block {
    
    public VuldOre(){
        super(FabricBlockSettings.of(Material.STONE)
            .strength(20f,800f)
            .breakByHand(false)
            .breakByTool(FabricToolTags.PICKAXES, 2)
            .sounds(BlockSoundGroup.SLIME));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        dropStacks(state, world, pos, blockEntity, player, stack);
        player.getStackInHand(player.getActiveHand()).damage(250, (LivingEntity)player, (Consumer<LivingEntity>)((p) -> {
           ((LivingEntity) p).sendToolBreakStatus(player.getActiveHand());
        }));
    }

    public static void SpawnVuldInBiome(Biome biome) {
        if(biome.getCategory() == Biome.Category.NETHER) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHER_ORE_REPLACEABLES,
                    HM_Items.VULD_ORE.getDefaultState(),
                    3 //Ore vein size
               )).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                    10, //Number of veins per chunk
                    0, //Bottom Offset
                    0, //Min y level
                    128 //Max y level
            ))));
        }
    }

     
}