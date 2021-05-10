package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.tigereye.hellishmaterials.Utils;
import net.tigereye.hellishmaterials.mechanics.LussLuck;
import net.tigereye.modifydropsapi.api.GenerateBlockLootCallbackModifyLoot;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackAddLoot;
import net.tigereye.modifydropsapi.api.GenerateEntityLootCallbackModifyLoot;

import java.util.ArrayList;
import java.util.List;


//registers listeners for events
public class HMListeners {
    
    public static void register(){
        GenerateBlockLootCallbackModifyLoot.EVENT.register((type, lootContext, loot) -> {
            if(lootContext.hasParameter(LootContextParameters.TOOL)){
                PlayerEntity player = null;
                if(lootContext.hasParameter(LootContextParameters.THIS_ENTITY)){
                    Entity breakingEntity = lootContext.get(LootContextParameters.THIS_ENTITY);
                    if(breakingEntity instanceof PlayerEntity){
                        player = (PlayerEntity)breakingEntity;
                    }
                }
                ItemStack tool = lootContext.get(LootContextParameters.TOOL);
                if (tool != null) {
                    if (Utils.isLuss(tool)) {
                        loot = LussLuck.ToolListItemStackRandomizer(loot, tool, player);
                    } else if (Utils.isVuld(tool)) {
                        loot.clear();
                    }
                }
            }
            return loot;
        });

        GenerateEntityLootCallbackAddLoot.EVENT.register((type, lootContext) -> {
            List<ItemStack> loot = new ArrayList<>();
            if (lootContext.get(LootContextParameters.DAMAGE_SOURCE) == DamageSource.WITHER) {
                loot.add(new ItemStack(HMItems.VULD_DROP));
            }
            return loot;
        });

        GenerateEntityLootCallbackModifyLoot.EVENT.register((type, lootContext, loot) -> {
            if (lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) lootContext.get(LootContextParameters.KILLER_ENTITY);
                ItemStack tool;
                try {
                    tool = entity.getStackInHand(entity.getActiveHand());
                }
                catch(Exception e){
                    tool = null;
                }
                PlayerEntity player = null;
                if(entity instanceof PlayerEntity){
                    player = (PlayerEntity)entity;
                }
                if (tool != null) {
                    if (Utils.isLuss(tool)) {
                        loot = LussLuck.ToolListItemStackRandomizer(loot, tool, player);
                    } else if (Utils.isVuld(tool)) {
                        loot.clear();
                    }
                }
            }
            return loot;
        });
    }
}