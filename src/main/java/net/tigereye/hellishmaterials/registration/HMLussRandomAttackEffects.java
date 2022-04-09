package net.tigereye.hellishmaterials.registration;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect.Quality;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.LussRandomEffect.Weight;
import net.tigereye.hellishmaterials.mechanics.randomlusseffects.onattack.*;

public class HMLussRandomAttackEffects {

    //Extremely Lucky
    public static LussAttackEffect MULTIPLE_LIGHTNING_STRIKES = new LussAttackMultipleLightningStrikes(Quality.EXTREMELY_LUCKY,Weight.COMMON);
    public static LussAttackEffect SPAWN_SHULKER_ON_HEAD = new LussAttackShulkerOnHead(Quality.EXTREMELY_LUCKY,Weight.COMMON);
    public static LussAttackEffect THREE_VERY_LUCKY_EFFECTS = new LussAttackThreeVeryLuckyEffects(Quality.EXTREMELY_LUCKY,Weight.COMMON);

    //Very Lucky
    public static LussAttackEffect LIGHTNING_STRIKE = new LussAttackLightningStrike(Quality.VERY_LUCKY,Weight.COMMON);
    public static LussAttackEffect SCATTER_GOLD = new LussAttackScatterGold(Quality.VERY_LUCKY,Weight.COMMON);
    public static LussAttackEffect BLOW_AWAY = new LussAttackBlowAway(Quality.VERY_LUCKY,Weight.COMMON);
    public static LussAttackEffect LEVITATION_CLOUD = new LussAttackEffectCloud(Quality.VERY_LUCKY, Weight.COMMON, StatusEffects.LEVITATION,60,0, 120, 10, 1);
    public static LussAttackEffect SPAWN_CHICKEN_STACK_ON_HEAD = new LussAttackChickenStackOnHead(Quality.VERY_LUCKY, Weight.COMMON);
    public static LussAttackEffect KNOCK_HELMET_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.HEAD);
    public static LussAttackEffect KNOCK_SHIRT_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.CHEST);
    public static LussAttackEffect KNOCK_LEGGINGS_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.LEGS);
    public static LussAttackEffect KNOCK_BOOTS_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.FEET);
    public static LussAttackEffect KNOCK_SHIELD_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.OFFHAND);
    public static LussAttackEffect KNOCK_SWORD_OFF = new LussAttackKnockItemOff(Quality.VERY_LUCKY,Weight.RARE,EquipmentSlot.MAINHAND);
    public static LussAttackEffect THREE_LUCKY_EFFECTS = new LussAttackThreeLuckyEffects(Quality.VERY_LUCKY,Weight.COMMON);

    //Lucky
    public static LussAttackEffect SET_ON_FIRE = new LussAttackSetOnFire(Quality.LUCKY, Weight.COMMON);
    public static LussAttackEffect STUN_FOR_ONE_SECOND = new LussAttackOneSecondStun(Quality.LUCKY, Weight.COMMON);
    public static LussAttackEffect SPAWN_COOKIE = new LussAttackSpawnCookie(Quality.LUCKY, Weight.COMMON);
    public static LussAttackEffect SPAWN_CHICKEN_ON_HEAD = new LussAttackChickenOnHead(Quality.LUCKY, Weight.COMMON);
    public static LussAttackEffect INFLICT_WEAKNESS = new LussAttackStatusEffect(Quality.LUCKY, Weight.COMMON,StatusEffects.WEAKNESS,300,0);
    public static LussAttackEffect INFLICT_SLOWNESS = new LussAttackStatusEffect(Quality.LUCKY, Weight.COMMON,StatusEffects.SLOWNESS,300,0);
    public static LussAttackEffect INFLICT_POISON = new LussAttackStatusEffect(Quality.LUCKY, Weight.COMMON,StatusEffects.POISON,300,0);
    public static LussAttackEffect INFLICT_WITHER = new LussAttackStatusEffect(Quality.LUCKY, Weight.UNCOMMON,StatusEffects.WITHER,300,0);
    public static LussAttackEffect INFLICT_BLEEDING = new LussAttackStatusEffect(Quality.LUCKY, Weight.COMMON,HMStatusEffects.BLEEDING,300,0);

    //Unlucky
    public static LussAttackEffect GRANT_STRENGTH = new LussAttackStatusEffect(Quality.UNLUCKY, Weight.COMMON,StatusEffects.STRENGTH,300,0);
    public static LussAttackEffect GRANT_SPEED = new LussAttackStatusEffect(Quality.UNLUCKY, Weight.COMMON,StatusEffects.SPEED,300,0);
    public static LussAttackEffect GRANT_REGENERATION = new LussAttackStatusEffect(Quality.UNLUCKY, Weight.RARE,StatusEffects.REGENERATION,300,0);
    public static LussAttackEffect GRANT_GUTS = new LussAttackStatusEffect(Quality.UNLUCKY, Weight.COMMON,HMStatusEffects.GUTS,300,0);
    public static LussAttackEffect MOUNT_ON_SPIDER = new LussAttackMountOnSpider(Quality.UNLUCKY, Weight.COMMON);

    //Very Unlucky
    public static LussAttackEffect MOUNT_ON_IRON_GOLEM = new LussAttackMountOnIronGolem(Quality.VERY_UNLUCKY, Weight.COMMON);
    public static LussAttackEffect THREE_UNLUCKY_EFFECTS = new LussAttackThreeUnluckyEffects(Quality.VERY_UNLUCKY, Weight.COMMON);

    //Extremely Unlucky
    public static LussAttackEffect SUMMON_ENEMY_DREAD_SPIRIT = new LussAttackSummonEnemyDreadSpirit(Quality.EXTREMELY_UNLUCKY, Weight.COMMON);
    public static LussAttackEffect THREE_VERY_UNLUCKY_EFFECTS = new LussAttackThreeVeryUnluckyEffects(Quality.EXTREMELY_UNLUCKY, Weight.COMMON);

    public static void register(){
        LussAttackEffectManager.registerEffect(MULTIPLE_LIGHTNING_STRIKES);
        LussAttackEffectManager.registerEffect(SPAWN_SHULKER_ON_HEAD);
        LussAttackEffectManager.registerEffect(THREE_VERY_LUCKY_EFFECTS);

        LussAttackEffectManager.registerEffect(SCATTER_GOLD);
        LussAttackEffectManager.registerEffect(LIGHTNING_STRIKE);
        LussAttackEffectManager.registerEffect(BLOW_AWAY);
        LussAttackEffectManager.registerEffect(LEVITATION_CLOUD);
        LussAttackEffectManager.registerEffect(SPAWN_CHICKEN_STACK_ON_HEAD);
        LussAttackEffectManager.registerEffect(KNOCK_HELMET_OFF);
        LussAttackEffectManager.registerEffect(KNOCK_SHIRT_OFF);
        LussAttackEffectManager.registerEffect(KNOCK_LEGGINGS_OFF);
        LussAttackEffectManager.registerEffect(KNOCK_BOOTS_OFF);
        LussAttackEffectManager.registerEffect(KNOCK_SHIELD_OFF);
        LussAttackEffectManager.registerEffect(KNOCK_SWORD_OFF);
        LussAttackEffectManager.registerEffect(THREE_LUCKY_EFFECTS);

        LussAttackEffectManager.registerEffect(SET_ON_FIRE);
        LussAttackEffectManager.registerEffect(STUN_FOR_ONE_SECOND);
        LussAttackEffectManager.registerEffect(SPAWN_COOKIE);
        LussAttackEffectManager.registerEffect(SPAWN_CHICKEN_ON_HEAD);
        LussAttackEffectManager.registerEffect(INFLICT_WEAKNESS);
        LussAttackEffectManager.registerEffect(INFLICT_SLOWNESS);
        LussAttackEffectManager.registerEffect(INFLICT_POISON);
        LussAttackEffectManager.registerEffect(INFLICT_WITHER);
        LussAttackEffectManager.registerEffect(INFLICT_BLEEDING);

        LussAttackEffectManager.registerEffect(GRANT_STRENGTH);
        LussAttackEffectManager.registerEffect(GRANT_SPEED);
        LussAttackEffectManager.registerEffect(GRANT_REGENERATION);
        LussAttackEffectManager.registerEffect(GRANT_GUTS);
        LussAttackEffectManager.registerEffect(MOUNT_ON_SPIDER);

        LussAttackEffectManager.registerEffect(MOUNT_ON_IRON_GOLEM);
        LussAttackEffectManager.registerEffect(THREE_UNLUCKY_EFFECTS);
        LussAttackEffectManager.registerEffect(SUMMON_ENEMY_DREAD_SPIRIT);
        LussAttackEffectManager.registerEffect(THREE_VERY_UNLUCKY_EFFECTS);

    }
}
