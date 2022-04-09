package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.items.BaseArmor;
import net.tigereye.hellishmaterials.items.batet.BatetArmorMaterial;
import net.tigereye.hellishmaterials.items.vuld.armor.*;
import net.tigereye.hellishmaterials.blocks.*;
import net.tigereye.hellishmaterials.blocks.entity.VaporousVuldBlockEntity;
import net.tigereye.hellishmaterials.items.*;
import net.tigereye.hellishmaterials.items.batet.BatetMaterial;
import net.tigereye.hellishmaterials.items.batet.ExplodingDice;
import net.tigereye.hellishmaterials.items.batet.Moratorium;
import net.tigereye.hellishmaterials.items.luss.*;
import net.tigereye.hellishmaterials.items.vuld.*;
import net.tigereye.hellishmaterials.items.vuld.tools.*;

public class HMItems {

    public static final Item LUSS_DUST = new LussDust(new Item.Settings().group(ItemGroup.MISC));
    public static final Item LUSS_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item LUSS_AXE = new BaseAxe(new LussMaterial());
    public static final Item LUSS_HOE = new BaseHoe(new LussMaterial());
    public static final Item LUSS_PICKAXE = new BasePickaxe(new LussMaterial());
    public static final Item LUSS_SHOVEL = new BaseShovel(new LussMaterial());
    public static final Item LUSS_SWORD = new BaseSword(new LussMaterial());
    public static final Block LUSS_ORE = new LussOre();
    public static final Block LUSS_BLOCK = new LussBlock();

    public static final ArmorMaterial VULD_ARMOR = new VuldArmorMaterial();
    public static final Item VULD_HELM = new VuldHelmet(VULD_ARMOR, EquipmentSlot.HEAD);
    public static final Item VULD_CHESTPLATE = new VuldChestplate(VULD_ARMOR, EquipmentSlot.CHEST);
    public static final Item VULD_LEGGINGS = new VuldLeggings(VULD_ARMOR, EquipmentSlot.LEGS);
    public static final Item VULD_BOOTS = new VuldBoots(VULD_ARMOR, EquipmentSlot.FEET);
    public static final Item VULD = new Vuld(new Item.Settings().group(ItemGroup.MISC));
    public static final Item VULD_DROP = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item VULD_AXE = new VuldAxe(new VuldMaterial());
    public static final Item VULD_HOE = new VuldHoe(new VuldMaterial(), -8, 0);
    public static final Item VULD_PICKAXE = new VuldPickaxe(new VuldMaterial());
    public static final Item VULD_SHOVEL = new VuldShovel(new VuldMaterial());
    public static final Item VULD_SWORD = new VuldSword(new VuldMaterial());
    public static final Block VULD_ORE = new VuldOre();
    public static final Block CORRUPTED_BONE = new VuldOre();
    public static final Item FLASK_OF_VAPOROUS_VULD = new FlaskOfVaporousVuld(new Item.Settings().group(ItemGroup.TOOLS).maxCount(16));
    public static final Block VAPOROUS_VULD = new VaporousVuld();
    public static BlockEntityType<VaporousVuldBlockEntity> VAPOROUS_VULD_BLOCK_ENTITY;

    public static final ArmorMaterial BATET_ARMOR = new BatetArmorMaterial();
    public static final Item BATET_HELM = new BaseArmor(BATET_ARMOR, EquipmentSlot.HEAD);
    public static final Item BATET_CHESTPLATE = new BaseArmor(BATET_ARMOR, EquipmentSlot.CHEST);
    public static final Item BATET_LEGGINGS = new BaseArmor(BATET_ARMOR, EquipmentSlot.LEGS);
    public static final Item BATET_BOOTS = new BaseArmor(BATET_ARMOR, EquipmentSlot.FEET);
    public static final Item BATET_GEM = new Item(new Item.Settings().group(ItemGroup.MISC).fireproof());
    public static final Item BATET_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BATET_AXE = new BaseAxe(new BatetMaterial(), 3, -3.1f);
    public static final Item BATET_HOE = new BaseHoe(new BatetMaterial(), -5, -1);
    public static final Item BATET_PICKAXE = new BasePickaxe(new BatetMaterial());
    public static final Item BATET_SHOVEL = new BaseShovel(new BatetMaterial());
    public static final Item BATET_SWORD = new BaseSword(new BatetMaterial());
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

    public static final TagKey<Item> TAG_LUSS = TagKey.of(Registry.ITEM_KEY, new Identifier(HellishMaterials.MODID,"luss"));
    public static final TagKey<Item> TAG_VULD = TagKey.of(Registry.ITEM_KEY, new Identifier(HellishMaterials.MODID,"vuld"));
    public static final TagKey<Item> TAG_BATET = TagKey.of(Registry.ITEM_KEY, new Identifier(HellishMaterials.MODID,"batet"));
    public static final TagKey<Item> TAG_LUSS_BLACKLIST = TagKey.of(Registry.ITEM_KEY, new Identifier(HellishMaterials.MODID,"luss_blacklist"));
    public static final TagKey<Item> ITEM_TAG_IMMUNE_TO_VULD = TagKey.of(Registry.ITEM_KEY, new Identifier(HellishMaterials.MODID,"immune_to_vuld"));
    public static final TagKey<Block> TAG_REPLACEABLE_VULD_ORE = TagKey.of(Registry.BLOCK_KEY, new Identifier(HellishMaterials.MODID,"replaceable_vuld_ore"));
    public static final TagKey<Block> BLOCK_TAG_IMMUNE_TO_VULD = TagKey.of(Registry.BLOCK_KEY, new Identifier(HellishMaterials.MODID,"immune_to_vuld"));
    
    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_dust"), LUSS_DUST);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_ingot"), LUSS_INGOT);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_axe"), LUSS_AXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_hoe"), LUSS_HOE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_pickaxe"), LUSS_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_shovel"), LUSS_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_sword"), LUSS_SWORD);
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "luss_ore"), LUSS_ORE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_ore"),
            new BlockItem(LUSS_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "luss_block"), LUSS_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_block"),
            new BlockItem(LUSS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_helm"), VULD_HELM);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_chestplate"), VULD_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_leggings"), VULD_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_boots"), VULD_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld"), VULD);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_drop"), VULD_DROP);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_axe"), VULD_AXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_hoe"), VULD_HOE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_pickaxe"), VULD_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_shovel"), VULD_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_sword"), VULD_SWORD);
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "vuld_ore"), VULD_ORE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vuld_ore"),
            new BlockItem(VULD_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "corrupted_bone"), CORRUPTED_BONE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "corrupted_bone"),
            new BlockItem(CORRUPTED_BONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "flask_of_vaporous_vuld"), FLASK_OF_VAPOROUS_VULD);

        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "vaporous_vuld"), VAPOROUS_VULD);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "vaporous_vuld"),
                new BlockItem(VAPOROUS_VULD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        VAPOROUS_VULD_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(HellishMaterials.MODID, "vaporous_vuld_block_entity"),
                FabricBlockEntityTypeBuilder.create(VaporousVuldBlockEntity::new, VAPOROUS_VULD).build(null));

        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_helm"), BATET_HELM);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_chestplate"), BATET_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_leggings"), BATET_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_boots"), BATET_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_gem"), BATET_GEM);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_fragment"), BATET_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_axe"), BATET_AXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_hoe"), BATET_HOE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_pickaxe"), BATET_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_shovel"), BATET_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_sword"), BATET_SWORD);
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "batet_ore"), BATET_ORE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_ore"),
            new BlockItem(BATET_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(HellishMaterials.MODID, "batet_block"), BATET_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "batet_block"),
            new BlockItem(BATET_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "bleeding"), BLEEDING);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "long_bleeding"), LONG_BLEEDING);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "strong_bleeding"), STRONG_BLEEDING);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "guts"), GUTS);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "long_guts"), LONG_GUTS);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "strong_guts"), STRONG_GUTS);

        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "moratorium"), MORATORIUM);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luckstone"), LUCKSTONE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_dice"), LUSS_DICE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "exploding_dice"), EXPLODING_DICE);

        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "long_luck"), LONG_LUCK);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "strong_luck"), STRONG_LUCK);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "unluck"), UNLUCK);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "long_unluck"), LONG_UNLUCK);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "strong_unluck"), STRONG_UNLUCK);

        DispenserBlock.registerBehavior(HMItems.FLASK_OF_VAPOROUS_VULD, new FlaskOfVaporousVuld.DispenserBehaviour());

    }
}