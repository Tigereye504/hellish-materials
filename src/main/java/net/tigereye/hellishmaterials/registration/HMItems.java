package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.armor.BaseArmor;
import net.tigereye.hellishmaterials.armor.BatetArmorMaterial;
import net.tigereye.hellishmaterials.armor.VuldArmorMaterial;
import net.tigereye.hellishmaterials.blocks.*;
import net.tigereye.hellishmaterials.blocks.entity.VaporousVuldBlockEntity;
import net.tigereye.hellishmaterials.items.*;
import net.tigereye.hellishmaterials.items.batet.BatetMaterial;
import net.tigereye.hellishmaterials.items.batet.ExplodingDice;
import net.tigereye.hellishmaterials.items.batet.Moratorium;
import net.tigereye.hellishmaterials.items.luss.*;
import net.tigereye.hellishmaterials.items.vuld.*;

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
    public static final Item VULD_HELM = new VuldArmor(VULD_ARMOR, EquipmentSlot.HEAD);
    public static final Item VULD_CHESTPLATE = new VuldArmor(VULD_ARMOR, EquipmentSlot.CHEST);
    public static final Item VULD_LEGGINGS = new VuldArmor(VULD_ARMOR, EquipmentSlot.LEGS);
    public static final Item VULD_BOOTS = new VuldArmor(VULD_ARMOR, EquipmentSlot.FEET);
    public static final Item VULD = new Vuld(new Item.Settings().group(ItemGroup.MISC));
    public static final Item VULD_DROP = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item VULD_AXE = new VuldAxe(new VuldMaterial());
    public static final Item VULD_HOE = new VuldHoe(new VuldMaterial());
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
    public static final Item BATET_AXE = new BaseAxe(new BatetMaterial());
    public static final Item BATET_HOE = new BaseHoe(new BatetMaterial());
    public static final Item BATET_PICKAXE = new BasePickaxe(new BatetMaterial());
    public static final Item BATET_SHOVEL = new BaseShovel(new BatetMaterial());
    public static final Item BATET_SWORD = new BaseSword(new BatetMaterial());
    public static final Block BATET_ORE = new BatetOre();
    public static final Block BATET_BLOCK = new BatetBlock();

    public static final Item LUSS_DICE = new LussDice();
    public static final Item EXPLODING_DICE = new ExplodingDice();
    public static final Item LUCKSTONE = new Luckstone();
    public static final Item MORATORIUM = new Moratorium();

    public static final Potion LONG_LUCK = new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, 9600));
    public static final Potion STRONG_LUCK = new Potion("luck", new StatusEffectInstance(StatusEffects.LUCK, 1800, 1));

    public static final Tag<Item> TAG_LUSS = TagFactory.ITEM.create(new Identifier(HellishMaterials.MODID,"luss"));
    public static final Tag<Item> TAG_VULD = TagFactory.ITEM.create(new Identifier(HellishMaterials.MODID,"vuld"));
    public static final Tag<Item> TAG_BATET = TagFactory.ITEM.create(new Identifier(HellishMaterials.MODID,"batet"));
    public static final Tag<Item> ITEM_TAG_IMMUNE_TO_VULD = TagFactory.ITEM.create(new Identifier(HellishMaterials.MODID,"immune_to_vuld"));
    public static final Tag<Block> TAG_REPLACEABLE_VULD_ORE = TagFactory.BLOCK.create(new Identifier(HellishMaterials.MODID,"replaceable_vuld_ore"));
    public static final Tag<Block> BLOCK_TAG_IMMUNE_TO_VULD = TagFactory.BLOCK.create(new Identifier(HellishMaterials.MODID,"immune_to_vuld"));
    
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

        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "moratorium"), MORATORIUM);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luckstone"), LUCKSTONE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "luss_dice"), LUSS_DICE);
        Registry.register(Registry.ITEM, new Identifier(HellishMaterials.MODID, "exploding_dice"), EXPLODING_DICE);

        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "long_luck"), LONG_LUCK);
        Registry.register(Registry.POTION, new Identifier(HellishMaterials.MODID, "strong_luck"), STRONG_LUCK);

        DispenserBlock.registerBehavior(HMItems.FLASK_OF_VAPOROUS_VULD, new FlaskOfVaporousVuld.DispenserBehaviour());

    }
}