package net.tigereye.hellishmaterials.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

public class LussOre extends Block {
    
    public LussOre(){
        super(FabricBlockSettings.copyOf(Blocks.GLASS)
            .strength(0.5f, 0.3f)
            .luminance(14)
            .sounds(BlockSoundGroup.GLASS));
    }

    /*
    public static void SpawnLussInBiome(Biome biome) {
        if(biome.getCategory() == Biome.Category.NETHER) {
            biome.addFeature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    Feature.ORE.configure(
                new OreFeatureConfig(
                    OreFeatureConfig.Target.NETHER_ORE_REPLACEABLES,
                    HM_Items.LUSS_ORE.getDefaultState(),
                    16 //Ore vein size
               )).createDecoratedFeature(
                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                    2, //Number of veins per chunk
                    0, //Bottom Offset
                    64, //Min y level
                    128 //Max y level
            ))));
        }
    }
    */
}