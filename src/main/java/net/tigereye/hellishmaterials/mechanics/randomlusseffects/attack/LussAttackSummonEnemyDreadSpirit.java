package net.tigereye.hellishmaterials.mechanics.randomlusseffects.attack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.tigereye.hellishmaterials.registration.HMItems;

public class LussAttackSummonEnemyDreadSpirit implements LussAttackEffect{


    Quality quality;
    int weight;

    public LussAttackSummonEnemyDreadSpirit(Quality quality, int weight){
        this.quality = quality;
        this.weight = weight;
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
        VexEntity vex = new VexEntity(EntityType.VEX, defender.world);
        vex.setPos(defender.getX(),defender.getY(),defender.getZ());
        if(attacker != null){
            vex.setAttacker(attacker);
            vex.setTarget(attacker);
        }
        defender.world.spawnEntity(vex);
        vex.equipStack(EquipmentSlot.MAINHAND,new ItemStack(HMItems.LUSS_AXE));
        vex.equipStack(EquipmentSlot.HEAD,new ItemStack(HMItems.VULD_HELM));
        vex.equipStack(EquipmentSlot.CHEST,new ItemStack(HMItems.BATET_CHESTPLATE));
        vex.equipStack(EquipmentSlot.LEGS,new ItemStack(HMItems.BATET_LEGGINGS));
        vex.equipStack(EquipmentSlot.FEET,new ItemStack(HMItems.BATET_BOOTS));
        vex.setEquipmentDropChance(EquipmentSlot.MAINHAND,0);
        vex.setEquipmentDropChance(EquipmentSlot.HEAD,0);
        vex.setEquipmentDropChance(EquipmentSlot.CHEST,0);
        vex.setEquipmentDropChance(EquipmentSlot.LEGS,0);
        vex.setEquipmentDropChance(EquipmentSlot.FEET,0);
        vex.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,48000,0,false,true));
        vex.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,48000,0,false,true));
        vex.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK,48000,2,false,false));
    }
}
