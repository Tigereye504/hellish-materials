package net.tigereye.hellishmaterials;

import net.fabricmc.api.ModInitializer;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.tigereye.hellishmaterials.registration.HM_Listeners;
import net.tigereye.hellishmaterials.registration.HM_StatusEffects;

public class HellishMaterials implements ModInitializer{
    
    public static final String MODID = "hellish-materials";

    @Override
    public void onInitialize() {
        HM_Items.registerItems();
        HM_Listeners.registerListeners();
        HM_StatusEffects.registerStatusEffects();
    }
}