package net.tigereye.luss;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;

import net.tigereye.luss.items.*;
import net.tigereye.luss.mechanics.LussLuck;
import net.tigereye.luss.armor.BaseArmor;
import net.tigereye.luss.armor.VuldArmorMaterial;
import net.tigereye.luss.blocks.*;
import net.tigereye.luss.events.BlockDropStacksCallback;
import net.tigereye.luss.events.LivingEntityDropLootCallback;

public class LussGamblersOre implements ModInitializer{
    
    public static final String MODID = "luss-gamblers-ore";

    public static final Item LUSS_DUST = new LussDust(new Item.Settings().group(ItemGroup.MISC));
    public static final Item LUSS_INGOT = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Block LUSS_ORE = new LussOre();
    public static final Block LUSS_BLOCK = new LussBlock();

    public static final ArmorMaterial VULD_ARMOR = new VuldArmorMaterial();

    @Override
    public void onInitialize() {
        //register objects
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_dust"), LUSS_DUST);
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_ingot"), LUSS_INGOT);
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_axe"), new LussAxe());
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_hoe"), new LussHoe());
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_pickaxe"), new LussPickaxe());
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_shovel"), new LussShovel());
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_sword"), new LussSword());
        Registry.register(Registry.BLOCK, new Identifier(MODID, "luss_ore"), LUSS_ORE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_ore"),
            new BlockItem(LUSS_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "luss_block"), LUSS_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MODID, "luss_block"),
            new BlockItem(LUSS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier(MODID, "vuld_helm"), new BaseArmor(VULD_ARMOR, EquipmentSlot.HEAD));
        Registry.register(Registry.ITEM, new Identifier(MODID, "vuld_chestplate"), new BaseArmor(VULD_ARMOR, EquipmentSlot.CHEST));
        Registry.register(Registry.ITEM, new Identifier(MODID, "vuld_leggings"), new BaseArmor(VULD_ARMOR, EquipmentSlot.LEGS));
        Registry.register(Registry.ITEM, new Identifier(MODID, "vuld_boots"), new BaseArmor(VULD_ARMOR, EquipmentSlot.FEET));

        Registry.BIOME.forEach(LussOre::SpawnLussInBiome);
        
        //Install Listeners
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> LussOre.SpawnLussInBiome(biome));

        BlockDropStacksCallback.EVENT.register((state, world, pos, blockEntity, entity, stack, stacksToDrop) -> {
            if (entity instanceof PlayerEntity) {
                if(stack.getItem() instanceof LussAxe ||
                stack.getItem() instanceof LussHoe ||
                stack.getItem() instanceof LussPickaxe ||
                stack.getItem() instanceof LussShovel ||
                stack.getItem() instanceof LussSword) //replace this with something like hasTag(TAG.LUSSTOOL) once I learn how those work
                {
                    stacksToDrop = LussLuck.ToolListItemStackRandomizer(stacksToDrop, stack, ((PlayerEntity)entity).getLuck());
                }
            }
            return TypedActionResult.pass(stacksToDrop);
        });
        
        LivingEntityDropLootCallback.EVENT.register((source, causedByPlayer, loot) -> {
            if (source.getAttacker() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) source.getAttacker();
                ItemStack stack = player.getStackInHand(player.getActiveHand());
                if(stack.getItem() instanceof LussAxe ||
                stack.getItem() instanceof LussHoe ||
                stack.getItem() instanceof LussPickaxe ||
                stack.getItem() instanceof LussShovel ||
                stack.getItem() instanceof LussSword) //replace this with something like hasTag(TAG.LUSSTOOL) once I learn how those work
                {
                    loot = LussLuck.ToolListItemStackRandomizer(loot, stack, player.getLuck());
                }
            }
            return TypedActionResult.pass(loot);
        });
    }
}