package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;

public class LussOre extends Block {
    
    public LussOre(){
        super(FabricBlockSettings.of(Material.GLASS)
            .strength(0.5f,0.3f)
            .breakByHand(true)
            .lightLevel(14)
            .sounds(BlockSoundGroup.GLASS));
    }

    public static void SpawnLussInBiome(Biome biome) {
        if(biome.getCategory() == Biome.Category.NETHER) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHER_ORE_REPLACEABLES,
                    HellishMaterials.LUSS_ORE.getDefaultState(),
                    16 //Ore vein size
               )).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                    4, //Number of veins per chunk
                    0, //Bottom Offset
                    64, //Min y level
                    128 //Max y level
            ))));
        }
    }
}