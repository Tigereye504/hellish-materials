package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.blocks.*;
import net.tigereye.hellishmaterials.blocks.entity.VaporousVuldBlockEntity;
import net.tigereye.hellishmaterials.items.*;
import net.tigereye.hellishmaterials.items.batet.*;
import net.tigereye.hellishmaterials.items.luss.Luckstone;
import net.tigereye.hellishmaterials.items.luss.LussDice;
import net.tigereye.hellishmaterials.items.luss.LussDust;
import net.tigereye.hellishmaterials.items.luss.LussMaterial;
import net.tigereye.hellishmaterials.items.vuld.FlaskOfVaporousVuld;
import net.tigereye.hellishmaterials.items.vuld.Vuld;
import net.tigereye.hellishmaterials.items.vuld.armor.*;
import net.tigereye.hellishmaterials.items.vuld.tools.*;

public class HMItems {

    public static final Item LUSS_DUST = new LussDust(new Item.Settings());
    public static final Item LUSS_INGOT = new Item(new Item.Settings());
    public static final Item LUSS_AXE = new BaseAxe(new LussMaterial());
    public static final Item LUSS_HOE = new BaseHoe(new LussMaterial());
    public static final Item LUSS_PICKAXE = new BasePickaxe(new LussMaterial());
    public static final Item LUSS_SHOVEL = new BaseShovel(new LussMaterial());
    public static final Item LUSS_SWORD = new BaseSword(new LussMaterial());
    public static final Block LUSS_ORE = new LussOre();
    public static final Block LUSS_BLOCK = new LussBlock();

    public static final ArmorMaterial VULD_ARMOR = new VuldArmorMaterial();
    public static final Item VULD_HELM = new VuldHelmet(VULD_ARMOR, ArmorItem.Type.HELMET);
    public static final Item VULD_CHESTPLATE = new VuldChestplate(VULD_ARMOR, ArmorItem.Type.CHESTPLATE);
    public static final Item VULD_LEGGINGS = new VuldLeggings(VULD_ARMOR, ArmorItem.Type.LEGGINGS);
    public static final Item VULD_BOOTS = new VuldBoots(VULD_ARMOR, ArmorItem.Type.BOOTS);
    public static final Item VULD = new Vuld(new Item.Settings());
    public static final Item VULD_DROP = new Item(new Item.Settings());
    public static final Item VULD_AXE = new VuldAxe(new VuldMaterial());
    public static final Item VULD_HOE = new VuldHoe(new VuldMaterial(), -8, 0);
    public static final Item VULD_PICKAXE = new VuldPickaxe(new VuldMaterial());
    public static final Item VULD_SHOVEL = new VuldShovel(new VuldMaterial());
    public static final Item VULD_SWORD = new VuldSword(new VuldMaterial());
    public static final Block VULD_ORE = new VuldOre();
    public static final Block CORRUPTED_BONE = new VuldOre();
    public static final Item FLASK_OF_VAPOROUS_VULD = new FlaskOfVaporousVuld(new Item.Settings().maxCount(16));
    public static final Block VAPOROUS_VULD = new VaporousVuld();
    public static BlockEntityType<VaporousVuldBlockEntity> VAPOROUS_VULD_BLOCK_ENTITY;

    public static final ArmorMaterial BATET_ARMOR = new BatetArmorMaterial();
    public static final Item BATET_HELM = new BatetArmor(BATET_ARMOR, ArmorItem.Type.HELMET);
    public static final Item BATET_CHESTPLATE = new BatetArmor(BATET_ARMOR, ArmorItem.Type.CHESTPLATE);
    public static final Item BATET_LEGGINGS = new BatetArmor(BATET_ARMOR, ArmorItem.Type.LEGGINGS);
    public static final Item BATET_BOOTS = new BatetArmor(BATET_ARMOR, ArmorItem.Type.BOOTS);
    public static final Item BATET_GEM = new Item(new Item.Settings().fireproof());
    public static final Item BATET_FRAGMENT = new Item(new Item.Settings());
    public static final Item BATET_AXE = new BatetAxe(new BatetMaterial(), 3, -3.1f);
    public static final Item BATET_HOE = new BaseHoe(new BatetMaterial(), -5, -1);
    public static final Item BATET_PICKAXE = new BasePickaxe(new BatetMaterial());
    public static final Item BATET_SHOVEL = new BaseShovel(new BatetMaterial());
    public static final Item BATET_SWORD = new BatetSword(new BatetMaterial());
    public static final Block BATET_ORE = new BatetOre();
    public static final Block BATET_BLOCK = new BatetBlock();

    public static final Potion BLEEDING = new Potion("bleeding", new StatusEffectInstance(HMStatusEffects.BLEEDING, 3600));
    public static final Potion LONG_BLEEDING = new Potion("bleeding", new StatusEffectInstance(HMStatusEffects.BLEEDING, 9600));
    public static final Potion STRONG_BLEEDING = new Potion("bleeding", new StatusEffectInstance(HMStatusEffects.BLEEDING, 1800, 1));
    public static final Potion GUTS = new Potion("guts", new StatusEffectInstance(HMStatusEffects.GUTS, 3600));
    public static final Potion LONG_GUTS = new Potion("guts", new StatusEffectInstance(HMStatusEffects.GUTS, 9600));
    public static final Potion STRONG_GUTS = new Potion("guts", new StatusEffectInstance(HMStatusEffects.GUTS, 1800, 1));

    public static final Item LUSS_DICE = new LussDice();
    public static final Item EXPLODING_DICE = new ExplodingDice();
    public static final Item LUCKSTONE = new Luckstone();
    public static final Item MORATORIUM = new Moratorium();

    public static final Potion LONG_LUCK = new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, 9600));
    public static final Potion STRONG_LUCK = new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, 1800, 1));
    public static final Potion UNLUCK = new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 3600));
    public static final Potion LONG_UNLUCK = new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 9600));
    public static final Potion STRONG_UNLUCK = new Potion("unluck", new StatusEffectInstance(StatusEffects.UNLUCK, 1800, 1));

    public static final TagKey<Item> TAG_LUSS = TagKey.of(RegistryKeys.ITEM, new Identifier(HellishMaterials.MODID,"luss"));
    public static final TagKey<Item> TAG_VULD = TagKey.of(RegistryKeys.ITEM, new Identifier(HellishMaterials.MODID,"vuld"));
    public static final TagKey<Item> TAG_BATET = TagKey.of(RegistryKeys.ITEM, new Identifier(HellishMaterials.MODID,"batet"));
    public static final TagKey<Item> TAG_LUSS_BLACKLIST = TagKey.of(RegistryKeys.ITEM, new Identifier(HellishMaterials.MODID,"luss_blacklist"));
    public static final TagKey<Item> ITEM_TAG_IMMUNE_TO_VULD = TagKey.of(RegistryKeys.ITEM, new Identifier(HellishMaterials.MODID,"immune_to_vuld"));
    public static final TagKey<Block> TAG_REPLACEABLE_VULD_ORE = TagKey.of(RegistryKeys.BLOCK, new Identifier(HellishMaterials.MODID,"replaceable_vuld_ore"));
    public static final TagKey<Block> BLOCK_TAG_IMMUNE_TO_VULD = TagKey.of(RegistryKeys.BLOCK, new Identifier(HellishMaterials.MODID,"immune_to_vuld"));


  public static void register(){
    registerItem("luss_dust", LUSS_DUST,ItemGroups.INGREDIENTS);
    registerItem("luss_ingot", LUSS_INGOT,ItemGroups.INGREDIENTS);
    registerItem("luss_axe", LUSS_AXE,ItemGroups.TOOLS);
    registerItem("luss_hoe", LUSS_HOE,ItemGroups.TOOLS);
    registerItem("luss_pickaxe", LUSS_PICKAXE,ItemGroups.TOOLS);
    registerItem("luss_shovel", LUSS_SHOVEL,ItemGroups.TOOLS);
    registerItem("luss_sword", LUSS_SWORD,ItemGroups.COMBAT);
    registerBlock("luss_ore", LUSS_ORE);
    registerBlock("luss_block", LUSS_BLOCK);

    registerItem("vuld_helm", VULD_HELM,ItemGroups.COMBAT);
    registerItem("vuld_chestplate", VULD_CHESTPLATE,ItemGroups.COMBAT);
    registerItem("vuld_leggings", VULD_LEGGINGS,ItemGroups.COMBAT);
    registerItem("vuld_boots", VULD_BOOTS,ItemGroups.COMBAT);
    registerItem("vuld", VULD,ItemGroups.INGREDIENTS);
    registerItem("vuld_drop", VULD_DROP,ItemGroups.INGREDIENTS);
    registerItem("vuld_axe", VULD_AXE,ItemGroups.TOOLS);
    registerItem("vuld_hoe", VULD_HOE,ItemGroups.TOOLS);
    registerItem("vuld_pickaxe", VULD_PICKAXE,ItemGroups.TOOLS);
    registerItem("vuld_shovel", VULD_SHOVEL,ItemGroups.TOOLS);
    registerItem("vuld_sword", VULD_SWORD,ItemGroups.COMBAT);
    registerBlock("vuld_ore", VULD_ORE);
    registerBlock("corrupted_bone", CORRUPTED_BONE);
    registerItem("flask_of_vaporous_vuld", FLASK_OF_VAPOROUS_VULD,ItemGroups.TOOLS);

    registerBlock("vaporous_vuld", VAPOROUS_VULD);
    VAPOROUS_VULD_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
        new Identifier(HellishMaterials.MODID, "vaporous_vuld_block_entity"),
        FabricBlockEntityTypeBuilder.create(VaporousVuldBlockEntity::new, VAPOROUS_VULD).build(null));

    registerItem("batet_helm", BATET_HELM,ItemGroups.COMBAT);
    registerItem("batet_chestplate", BATET_CHESTPLATE,ItemGroups.COMBAT);
    registerItem("batet_leggings", BATET_LEGGINGS,ItemGroups.COMBAT);
    registerItem("batet_boots", BATET_BOOTS,ItemGroups.COMBAT);
    registerItem("batet_gem", BATET_GEM,ItemGroups.INGREDIENTS);
    registerItem("batet_fragment", BATET_FRAGMENT,ItemGroups.INGREDIENTS);
    registerItem("batet_axe", BATET_AXE,ItemGroups.TOOLS);
    registerItem("batet_hoe", BATET_HOE,ItemGroups.TOOLS);
    registerItem("batet_pickaxe", BATET_PICKAXE,ItemGroups.TOOLS);
    registerItem("batet_shovel", BATET_SHOVEL,ItemGroups.TOOLS);
    registerItem("batet_sword", BATET_SWORD,ItemGroups.COMBAT);
    registerBlock("batet_ore", BATET_ORE);
    registerBlock("batet_block", BATET_BLOCK);

    registerPotion("bleeding", BLEEDING);
    registerPotion("long_bleeding", LONG_BLEEDING);
    registerPotion("strong_bleeding", STRONG_BLEEDING);
    registerPotion("guts", GUTS);
    registerPotion("long_guts", LONG_GUTS);
    registerPotion("strong_guts", STRONG_GUTS);

    registerItem("moratorium", MORATORIUM, ItemGroups.COMBAT);
    registerItem("luckstone", LUCKSTONE, ItemGroups.TOOLS);
    registerItem("luss_dice", LUSS_DICE, ItemGroups.TOOLS);
    registerItem("exploding_dice", EXPLODING_DICE, ItemGroups.COMBAT);

    registerPotion("long_luck", LONG_LUCK);
    registerPotion("strong_luck", STRONG_LUCK);
    registerPotion("unluck", UNLUCK);
    registerPotion("long_unluck", LONG_UNLUCK);
    registerPotion("strong_unluck", STRONG_UNLUCK);

    DispenserBlock.registerBehavior(HMItems.FLASK_OF_VAPOROUS_VULD, new FlaskOfVaporousVuld.DispenserBehaviour());

    }
    
    public static void registerItem(String name, Item item, RegistryKey<ItemGroup> itemGroup){
        Registry.register(Registries.ITEM, new Identifier(HellishMaterials.MODID, name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
    }
    
    public static void registerBlock(String name, Block block){
        Registry.register(Registries.BLOCK, new Identifier(HellishMaterials.MODID, name), block);
        registerItem(name, new BlockItem(block, new Item.Settings()),ItemGroups.BUILDING_BLOCKS);
    }
    
    public static void registerPotion(String name, Potion potion){
        Registry.register(Registries.POTION, new Identifier(HellishMaterials.MODID, name), potion);
    }
}