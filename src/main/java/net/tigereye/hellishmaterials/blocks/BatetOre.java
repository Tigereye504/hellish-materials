package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class BatetOre extends Block {
    
    public BatetOre(){
        super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            .strength(3f,5f)
            .requiresTool()
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