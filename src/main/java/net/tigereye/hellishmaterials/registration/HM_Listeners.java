package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.tigereye.modifydropsapi.api.BlockDropStacksCallback_ModifyDrops;
import net.tigereye.modifydropsapi.api.LivingEntityDropLootCallback_ModifyDrops;
import net.tigereye.hellishmaterials.mechanics.LussLuck;


//registers listeners for events
//this includes ore generation, as it turns out
public class HM_Listeners {
    
    public static void register(){
        BlockDropStacksCallback_ModifyDrops.EVENT.register((state, world, pos, blockEntity, entity, stack, stacksToDrop) -> {
            if (entity instanceof PlayerEntity) {
                if(stack.getItem().isIn(HM_Items.TAG_LUSS))
                {
                    stacksToDrop = LussLuck.ToolListItemStackRandomizer(stacksToDrop, stack, ((PlayerEntity)entity).getLuck());
                }
                else if(stack.getItem().isIn(HM_Items.TAG_VULD))
                {
                    stacksToDrop.clear();
                }
            }
            return stacksToDrop;
        });
        
        LivingEntityDropLootCallback_ModifyDrops.EVENT.register((entity, source, causedByPlayer, loot) -> {
            if (source == DamageSource.WITHER) {
                loot.add(new ItemStack(HM_Items.VULD_DROP));
            }
            if (source.getAttacker() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) source.getAttacker();
                ItemStack stack = player.getStackInHand(player.getActiveHand());
                if(stack.getItem().isIn(HM_Items.TAG_LUSS))
                {
                    loot = LussLuck.ToolListItemStackRandomizer(loot, stack, player.getLuck());
                }
                else if(stack.getItem().isIn(HM_Items.TAG_VULD))
                {
                    loot.clear();
                }
            }
            return loot;
        });
    }
}