package net.tigereye.luss.mixins;

import net.tigereye.luss.events.LivingEntityDropLootCallback;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
//import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;

@Mixin(LivingEntity.class)
public class LivingEntityDropLootMixin {
    
    @Shadow
    protected LootContext.Builder getLootContextBuilder(boolean causedByPlayer, DamageSource source){return null;}

    @Inject(
        at = @At("HEAD"),
        method = "dropLoot",
        cancellable = true)
    private void ChangeEntityDrop(DamageSource source, boolean causedByPlayer, CallbackInfo info){
        
        Identifier identifier = ((LivingEntity) (Object) this).getLootTable();
        LootTable lootTable = ((LivingEntity) (Object) this).world.getServer().getLootManager().getTable(identifier);
        LootContext.Builder builder = /*((LivingEntity) (Object))*/ this.getLootContextBuilder(causedByPlayer, source);
        List<ItemStack> loot = lootTable.generateLoot(builder.build(LootContextTypes.ENTITY));
        
        TypedActionResult<List<ItemStack>> result = LivingEntityDropLootCallback.EVENT.invoker().editDroppedLoot(source, causedByPlayer, loot);
        //loot.add(new ItemStack(Items.DIAMOND, 64));
        loot = result.getValue();
        loot.forEach((stackToDrop) -> {
            ((LivingEntity) (Object) this).dropStack(stackToDrop);
        });

        info.cancel();
    }
}