package net.tigereye.luss.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.tigereye.luss.LussGamblersOre;
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

    public static void SpawnVuldInBiome(Biome biome) {
        if(biome.getCategory() == Biome.Category.NETHER) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHER_ORE_REPLACEABLES,
                    LussGamblersOre.VULD_ORE.getDefaultState(),
                    1 //Ore vein size
               )).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                    10, //Number of veins per chunk
                    0, //Bottom Offset
                    64, //Min y level
                    128 //Max y level
            ))));
        }
    }
}