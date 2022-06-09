package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
/*import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;*/

public class BatetOre extends Block {
    
    public BatetOre(){
        super(FabricBlockSettings.of(Material.STONE)
            .strength(3f,50f)
            //.breakByTool(FabricToolTags.PICKAXES, 2)
            .sounds(BlockSoundGroup.STONE)
            .nonOpaque());
    }
/*
    public static void SpawnBatetInBiome(Biome biome) {
        if(biome.getCategory() == Biome.Category.NETHER) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHER_ORE_REPLACEABLES,
                    HM_Items.BATET_ORE.getDefaultState(),
                    8 //Ore vein size
               )).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                    16, //Number of veins per chunk
                    0, //Bottom Offset
                    20, //Min y level
                    44 //Max y level
            ))));
        }
    }
    */
}