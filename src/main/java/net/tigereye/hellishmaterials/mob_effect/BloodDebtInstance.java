package net.tigereye.hellishmaterials.mob_effect;

public interface BloodDebtInstance{
    
    //public static StatusEffectInstance newBloodDebtStatusEffectInstance(float debt){return null;}
    public float drawRepayment();
    public float getDebt();
    public void setDebt(float debt);
    public float addDebt(float amount);
    public float removeDebt(float amount);
}
