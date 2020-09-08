package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class HM_LootTables {
    
    private static final Identifier WITHER_SKELETON_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither_skeleton");
    private static final Identifier WITHER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/wither");
    
    public static void register(){
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (WITHER_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootTableRange.create(1))
                    .with(ItemEntry.builder(HM_Items.VULD))
                    .conditionally(KilledByPlayerLootCondition.builder());
                supplier.pool(poolBuilder);
            }
            if (WITHER_SKELETON_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootTableRange.create(1))
                    .with(ItemEntry.builder(HM_Items.VULD_DROP))
                    .conditionally(KilledByPlayerLootCondition.builder())
                    .conditionally(RandomChanceWithLootingLootCondition.builder(.05f, .02f));
                supplier.pool(poolBuilder);
            }
        });
    }
}