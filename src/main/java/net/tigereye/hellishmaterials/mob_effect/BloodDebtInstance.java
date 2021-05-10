package net.tigereye.hellishmaterials.mob_effect;

public interface BloodDebtInstance{
    
    //public static StatusEffectInstance newBloodDebtStatusEffectInstance(float debt){return null;}
    float drawRepayment();
    float getDebt();
    void setDebt(float debt);
    float addDebt(float amount);
    float removeDebt(float amount);
}
