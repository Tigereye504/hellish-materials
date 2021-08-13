package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class HMLootTables {
    
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    private static final Identifier WITHER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither");
    private static final Identifier NETHER_FORTRESS_LOOT_TABLE_ID = new Identifier("minecraft", "chests/nether_bridge");
    private static final Identifier BASTION_TREASURE_TABLE_ID = new Identifier("minecraft", "chests/bastion_treasure");
    private static final Identifier BASTION_OTHER_TABLE_ID = new Identifier("minecraft", "chests/bastion_other");
    //TODO: add luss dice, exploding dice, luckstone, luss, and batet to bastion loot table
    //TODO: add vuld drops, luckstone, and moratorium to nether fortress loot table
    public static void register(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (WITHER_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(HMItems.VULD))
                    .conditionally(KilledByPlayerLootCondition.builder());
                supplier.pool(poolBuilder);
            }
            if (WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(HMItems.VULD_DROP))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithLootingLootCondition.builder(.05f, .02f));
                supplier.pool(poolBuilder);
            }
            if (NETHER_FORTRESS_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(20,.15f))
                        .with(ItemEntry.builder(HMItems.LUSS_INGOT).weight(10))
                        .with(ItemEntry.builder(HMItems.BATET_GEM).weight(10))
                        .with(ItemEntry.builder(HMItems.VULD_DROP).weight(2))
                        .with(ItemEntry.builder(HMItems.LUCKSTONE).weight(1))
                        .with(ItemEntry.builder(HMItems.MORATORIUM).weight(1));
                supplier.pool(poolBuilder);
                supplier.pool(poolBuilder);
            }
            if (BASTION_TREASURE_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder;
                poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(HMItems.EXPLODING_DICE))
                        .with(ItemEntry.builder(HMItems.LUCKSTONE))
                        .with(ItemEntry.builder(HMItems.MORATORIUM));
                supplier.pool(poolBuilder);
                poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(20,.1f))
                        .with(ItemEntry.builder(HMItems.BATET_HELM).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_CHESTPLATE).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_LEGGINGS).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_BOOTS).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_SWORD).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_PICKAXE).weight(1).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.BATET_AXE).weight(1).apply(new EnchantRandomlyLootFunction.Builder()));
                supplier.pool(poolBuilder);
            }
            if (BASTION_OTHER_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(BinomialLootNumberProvider.create(10,.3f))
                        .with(ItemEntry.builder(HMItems.LUSS_INGOT).weight(10))
                        .with(ItemEntry.builder(HMItems.BATET_GEM).weight(1))
                        .with(ItemEntry.builder(HMItems.LUSS_DICE).weight(6))
                        .with(ItemEntry.builder(HMItems.LUSS_AXE).weight(2).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.LUSS_HOE).weight(2).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.LUSS_PICKAXE).weight(2).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.LUSS_SHOVEL).weight(2).apply(new EnchantRandomlyLootFunction.Builder()))
                        .with(ItemEntry.builder(HMItems.LUSS_SWORD).weight(2).apply(new EnchantRandomlyLootFunction.Builder()));
                supplier.pool(poolBuilder);
            }
        });
    }
}