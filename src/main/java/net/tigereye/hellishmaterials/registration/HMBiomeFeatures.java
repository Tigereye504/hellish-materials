package net.tigereye.hellishmaterials.registration;

//import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
//import java.util.stream.Collectors;

//import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.tigereye.hellishmaterials.HellishMaterials;
//import net.tigereye.hellishmaterials.blocks.BatetOre;
//import net.tigereye.hellishmaterials.blocks.LussOre;
//import net.tigereye.hellishmaterials.blocks.VuldOre;

public class HMBiomeFeatures {/*
    public static final ConfiguredFeature<?,?> VULD_ORE_VEIN = BuiltinRegistries.CONFIGURED_FEATURE.get(new Identifier(HellishMaterials.MODID, "vuld_ore_vein"));
    public static final ConfiguredFeature<?,?> VULD_ORE_OVERWORLD_TEST = BuiltinRegistries.CONFIGURED_FEATURE.get(new Identifier(HellishMaterials.MODID, "vuld_ore_overworld_test"));
    */
    /*
    public static void addFeatures(){
        for (Biome biome : BuiltinRegistries.BIOME){
            if (biome.getCategory() != Biome.Category.NETHER){
                //addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, LussOre.LUSS_ORE_VEIN);
                addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, VULD_ORE_OVERWORLD_TEST);
                //addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, BatetOre.BATET_ORE_VEIN);
            }
            if (biome.getCategory() == Biome.Category.NETHER){
                //addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, LussOre.LUSS_ORE_VEIN);
                addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, VULD_ORE_VEIN);
                //addFeatureToBiome(biome,GenerationStep.Feature.UNDERGROUND_ORES, BatetOre.BATET_ORE_VEIN);
            }
        }
    }*/
    /*
    public static void addFeatureToBiome(Biome biome, GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature) {
        //ConvertImmutableFeatures(biome);
        List<List<Supplier<ConfiguredFeature<?, ?>>>> biomeFeatures = biome.getGenerationSettings().getFeatures();
        while (biomeFeatures.size() <= feature.ordinal()) {
            biomeFeatures.add(Lists.newArrayList());
        }
        biomeFeatures.get(feature.ordinal()).add(() -> configuredFeature);

    }*/
    /*
    private static void ConvertImmutableFeatures(Biome biome) {
        if (biome.getGenerationSettings().getFeatures() instanceof ImmutableList) {
            List<List<Supplier<ConfiguredFeature<?,?>>>> newList = new ArrayList<List<Supplier<ConfiguredFeature<?,?>>>>();
            for (List<Supplier<ConfiguredFeature<?,?>>> entry : biome.getGenerationSettings().getFeatures()){
                newList.add(entry);
            }
            biome.getGenerationSettings().setFeatures(newList);
            //biome.getGenerationSettings().setFeatures(
            //    new List<List<Supplier<ConfiguredFeature<?,?>>>>
            //    biome.getGenerationSettings().getFeatures().stream().map(Lists::newArrayList).collect(Collectors.toList())
            //    );
        }
    }
    */
    
}