package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class LussAttackKnockItemOff implements LussAttackEffect{


    Quality quality;
    int weight;
    EquipmentSlot slot;

    public LussAttackKnockItemOff(Quality quality, int weight, EquipmentSlot slot){
        this.quality = quality;
        this.weight = weight;
        this.slot = slot;
    }
    @Override
    public Quality getQuality() {
        return this.quality;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void causeEffect(LivingEntity attacker, LivingEntity defender, float damage, float luck) {
        if(!defender.hasStackEquipped(slot)){
            return;
        }
        ItemStack item = defender.getEquippedStack(slot);
        defender.equipStack(slot,ItemStack.EMPTY);
        if(defender instanceof PlayerEntity){
            if(!((PlayerEntity) defender).getInventory().insertStack(item)){
                ((PlayerEntity) defender).dropItem(item,true,true);
            }
        }
        else{
            defender.dropStack(item,defender.getHeight()/2);
        }
    }
}
