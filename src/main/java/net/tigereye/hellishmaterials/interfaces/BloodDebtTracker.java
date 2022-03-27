package net.tigereye.hellishmaterials.interfaces;

public interface BloodDebtTracker {
    boolean isBloodDebtTick();
    float getBloodDebt();
    void setBloodDebt(float debt);
}
