package net.tigereye.hellishmaterials.registration;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect.Quality;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect.Weight;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak.*;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onbreak.LussBreakEffect;

public class HMLussRandomBreakEffects {

    //Extremely Lucky
    public static LussBreakEffect EMBED_DIAMOND_ORE = new LussBreakEmbedBlocks(Quality.EXTREMELY_LUCKY,Weight.UNCOMMON, Blocks.DIAMOND_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_DIAMOND_ORE = new LussBreakEmbedBlocks(Quality.EXTREMELY_LUCKY,Weight.UNCOMMON, Blocks.DEEPSLATE_DIAMOND_ORE);
    public static LussBreakEffect EMBED_RAW_IRON_BLOCK = new LussBreakEmbedBlocks(Quality.EXTREMELY_LUCKY,Weight.UNCOMMON, Blocks.RAW_IRON_BLOCK);
    public static LussBreakEffect EMBED_RAW_GOLD_BLOCK = new LussBreakEmbedBlocks(Quality.EXTREMELY_LUCKY,Weight.UNCOMMON, Blocks.RAW_GOLD_BLOCK);
    public static LussBreakEffect SPAWN_SHULKER = new LussBreakSpawnMob(Quality.EXTREMELY_LUCKY,Weight.RARE, EntityType.SHULKER);
    public static LussBreakEffect THREE_VERY_LUCKY_EFFECTS = new LussBreakThreeEffects(Quality.EXTREMELY_LUCKY,Weight.COMMON,Quality.VERY_LUCKY);

    //Very Lucky
    public static LussBreakEffect EMBED_IRON_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.IRON_ORE);
    public static LussBreakEffect EMBED_EMERALD_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.EMERALD_ORE);
    public static LussBreakEffect EMBED_GOLD_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.GOLD_ORE);
    public static LussBreakEffect EMBED_REDSTONE_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.REDSTONE_ORE);
    public static LussBreakEffect EMBED_LAPIS_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.UNCOMMON, Blocks.LAPIS_ORE);
    public static LussBreakEffect EMBED_BATET_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, HMItems.BATET_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_IRON_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.DEEPSLATE_IRON_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_EMERALD_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.DEEPSLATE_EMERALD_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_GOLD_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.DEEPSLATE_GOLD_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_REDSTONE_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.DEEPSLATE_REDSTONE_ORE);
    public static LussBreakEffect EMBED_NETHER_GOLD_ORE = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.NETHER_GOLD_ORE);
    public static LussBreakEffect EMBED_RAW_COPPER_BLOCK = new LussBreakEmbedBlocks(Quality.VERY_LUCKY,Weight.RARE, Blocks.RAW_COPPER_BLOCK);
    public static LussBreakEffect SCATTER_GOLD = new LussBreakScatterGold(Quality.VERY_LUCKY,Weight.COMMON);
    public static LussBreakEffect THREE_LUCKY_EFFECTS = new LussBreakThreeEffects(Quality.VERY_LUCKY,Weight.COMMON,Quality.LUCKY);

    //Lucky
    public static LussBreakEffect EMBED_COPPER_ORE = new LussBreakEmbedBlocks(Quality.LUCKY,Weight.UNCOMMON, Blocks.COPPER_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_COPPER_ORE = new LussBreakEmbedBlocks(Quality.LUCKY,Weight.UNCOMMON, Blocks.DEEPSLATE_COPPER_ORE);
    public static LussBreakEffect EMBED_COAL_ORE = new LussBreakEmbedBlocks(Quality.LUCKY,Weight.UNCOMMON, Blocks.COAL_ORE);
    public static LussBreakEffect EMBED_DEEPSLATE_COAL_ORE = new LussBreakEmbedBlocks(Quality.LUCKY,Weight.UNCOMMON, Blocks.DEEPSLATE_COAL_ORE);
    public static LussBreakEffect EMBED_CLAY = new LussBreakEmbedBlocks(Quality.LUCKY, Weight.UNCOMMON, Blocks.CLAY,4);
    public static LussBreakEffect SPAWN_CHICKEN = new LussBreakSpawnMob(Quality.LUCKY, Weight.RARE,EntityType.CHICKEN);
    public static LussBreakEffect SPAWN_TINY_SLIME = new LussBreakSpawnSlime(Quality.LUCKY, Weight.COMMON,1);
    public static LussBreakEffect SPAWN_COOKIE = new LussBreakSpawnCookie(Quality.LUCKY, Weight.COMMON);

    //Unlucky
    public static LussBreakEffect EXPLODE = new LussBreakExplode(Quality.VERY_UNLUCKY, Weight.COMMON,3f, false);
    public static LussBreakEffect SPAWN_SILVERFISH = new LussBreakSpawnMob(Quality.UNLUCKY, Weight.COMMON, EntityType.SILVERFISH);
    public static LussBreakEffect SPAWN_BABY_ZOMBIE = new LussBreakSpawnMob(Quality.UNLUCKY, Weight.COMMON, EntityType.ZOMBIE,true);
    public static LussBreakEffect EMBED_ACACIA_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.ACACIA_PLANKS,16,3);
    public static LussBreakEffect EMBED_BIRCH_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.BIRCH_PLANKS,16,3);
    public static LussBreakEffect EMBED_DARK_OAK_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.DARK_OAK_PLANKS,16,3);
    public static LussBreakEffect EMBED_OAK_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.OAK_PLANKS,16,3);
    public static LussBreakEffect EMBED_CRIMSON_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.CRIMSON_PLANKS,16,3);
    public static LussBreakEffect EMBED_JUNGLE_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.JUNGLE_PLANKS,16,3);
    public static LussBreakEffect EMBED_SPRUCE_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.SPRUCE_PLANKS,16,3);
    public static LussBreakEffect EMBED_WARPED_PLANKS = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.VERY_RARE, Blocks.WARPED_PLANKS,16,3);
    public static LussBreakEffect EMBED_ANDESITE = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.ANDESITE,16,3);
    public static LussBreakEffect EMBED_GRANITE = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.GRANITE,16,3);
    public static LussBreakEffect EMBED_DIORITE = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.DIORITE,16,3);
    public static LussBreakEffect EMBED_DRIPSTONE_BLOCK = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.DRIPSTONE_BLOCK,16,3);
    public static LussBreakEffect EMBED_BASALT = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.BASALT,16,3);
    public static LussBreakEffect EMBED_NETHERRACK = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.NETHERRACK,16,3);
    public static LussBreakEffect EMBED_BLACKSTONE = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.BLACKSTONE,16,3);
    public static LussBreakEffect EMBED_SOUL_SAND = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.RARE, Blocks.SOUL_SAND,16,3);
    public static LussBreakEffect EMBED_GRAVEL = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.UNCOMMON, Blocks.GRAVEL,16,3);
    public static LussBreakEffect EMBED_DIRT = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.UNCOMMON, Blocks.DIRT,16,3);
    public static LussBreakEffect EMBED_SAND = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.UNCOMMON, Blocks.SAND,16,3);
    public static LussBreakEffect EMBED_DEEPSLATE = new LussBreakEmbedBlocks(Quality.UNLUCKY, Weight.COMMON, Blocks.DEEPSLATE,16,3);

    //Very Unlucky
    public static LussBreakEffect EXPLODE_VIOLENTLY = new LussBreakExplode(Quality.VERY_UNLUCKY, Weight.COMMON,4.5f, true);
    public static LussBreakEffect EMBED_INFESTED_STONE = new LussBreakEmbedBlocks(Quality.VERY_UNLUCKY, Weight.UNCOMMON, Blocks.INFESTED_STONE,64,4);
    public static LussBreakEffect EMBED_INFESTED_DEEPSLATE = new LussBreakEmbedBlocks(Quality.VERY_UNLUCKY, Weight.UNCOMMON, Blocks.INFESTED_DEEPSLATE,64,4);
    public static LussBreakEffect EMBED_LAVA = new LussBreakEmbedBlocks(Quality.VERY_UNLUCKY, Weight.COMMON, Blocks.LAVA,32,4);
    public static LussBreakEffect THREE_UNLUCKY_EFFECTS = new LussBreakThreeEffects(Quality.VERY_UNLUCKY,Weight.COMMON,Quality.UNLUCKY);

    //Extremely Unlucky
    public static LussBreakEffect EMBED_VAPOROUS_VULD = new LussBreakEmbedBlocks(Quality.EXTREMELY_UNLUCKY, Weight.RARE, HMItems.VAPOROUS_VULD,2,2);
    public static LussBreakEffect THREE_VERY_UNLUCKY_EFFECTS = new LussBreakThreeEffects(Quality.EXTREMELY_UNLUCKY,Weight.COMMON,Quality.VERY_UNLUCKY);

    public static void register(){

        LussBreakEffectManager.registerEffect(EMBED_DIAMOND_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_DIAMOND_ORE);
        LussBreakEffectManager.registerEffect(EMBED_RAW_IRON_BLOCK);
        LussBreakEffectManager.registerEffect(EMBED_RAW_GOLD_BLOCK);
        LussBreakEffectManager.registerEffect(SPAWN_SHULKER);
        LussBreakEffectManager.registerEffect(THREE_VERY_LUCKY_EFFECTS);

        LussBreakEffectManager.registerEffect(EMBED_IRON_ORE);
        LussBreakEffectManager.registerEffect(EMBED_EMERALD_ORE);
        LussBreakEffectManager.registerEffect(EMBED_GOLD_ORE);
        LussBreakEffectManager.registerEffect(EMBED_REDSTONE_ORE);
        LussBreakEffectManager.registerEffect(EMBED_LAPIS_ORE);
        LussBreakEffectManager.registerEffect(EMBED_BATET_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_IRON_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_EMERALD_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_GOLD_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_REDSTONE_ORE);
        LussBreakEffectManager.registerEffect(EMBED_NETHER_GOLD_ORE);
        LussBreakEffectManager.registerEffect(EMBED_RAW_COPPER_BLOCK);
        LussBreakEffectManager.registerEffect(SCATTER_GOLD);
        LussBreakEffectManager.registerEffect(THREE_LUCKY_EFFECTS);

        LussBreakEffectManager.registerEffect(EMBED_COPPER_ORE);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_COPPER_ORE);
        LussBreakEffectManager.registerEffect(EMBED_COAL_ORE);
        LussBreakEffectManager.registerEffect(EMBED_CLAY);
        LussBreakEffectManager.registerEffect(SPAWN_CHICKEN);
        LussBreakEffectManager.registerEffect(SPAWN_TINY_SLIME);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE_COAL_ORE);
        LussBreakEffectManager.registerEffect(SPAWN_COOKIE);

        LussBreakEffectManager.registerEffect(EXPLODE);
        LussBreakEffectManager.registerEffect(SPAWN_SILVERFISH);
        LussBreakEffectManager.registerEffect(SPAWN_BABY_ZOMBIE);
        LussBreakEffectManager.registerEffect(EMBED_ACACIA_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_BIRCH_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_DARK_OAK_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_OAK_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_CRIMSON_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_JUNGLE_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_SPRUCE_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_WARPED_PLANKS);
        LussBreakEffectManager.registerEffect(EMBED_ANDESITE);
        LussBreakEffectManager.registerEffect(EMBED_GRANITE);
        LussBreakEffectManager.registerEffect(EMBED_DIORITE);
        LussBreakEffectManager.registerEffect(EMBED_DRIPSTONE_BLOCK);
        LussBreakEffectManager.registerEffect(EMBED_BASALT);
        LussBreakEffectManager.registerEffect(EMBED_NETHERRACK);
        LussBreakEffectManager.registerEffect(EMBED_BLACKSTONE);
        LussBreakEffectManager.registerEffect(EMBED_SOUL_SAND);
        LussBreakEffectManager.registerEffect(EMBED_GRAVEL);
        LussBreakEffectManager.registerEffect(EMBED_DIRT);
        LussBreakEffectManager.registerEffect(EMBED_SAND);
        LussBreakEffectManager.registerEffect(EMBED_DEEPSLATE);

        LussBreakEffectManager.registerEffect(EXPLODE_VIOLENTLY);
        LussBreakEffectManager.registerEffect(EMBED_INFESTED_STONE);
        LussBreakEffectManager.registerEffect(EMBED_INFESTED_DEEPSLATE);
        LussBreakEffectManager.registerEffect(EMBED_LAVA);
        LussBreakEffectManager.registerEffect(THREE_UNLUCKY_EFFECTS);

        LussBreakEffectManager.registerEffect(EMBED_VAPOROUS_VULD);
        LussBreakEffectManager.registerEffect(THREE_VERY_UNLUCKY_EFFECTS);

    }
}
