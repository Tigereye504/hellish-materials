package net.tigereye.hellishmaterials;

import net.fabricmc.api.ModInitializer;
import net.tigereye.hellishmaterials.registration.HM_Config;
import net.tigereye.hellishmaterials.registration.HM_Items;
import net.tigereye.hellishmaterials.registration.HM_Listeners;
import net.tigereye.hellishmaterials.registration.HM_LootTables;
import net.tigereye.hellishmaterials.registration.HM_StatusEffects;

public class HellishMaterials implements ModInitializer{
    
    public static final String MODID = "hellish-materials";
    public static final boolean DEBUG = false;

    @Override
    public void onInitialize() {
        HM_Config.init();
        HM_Items.register();
        HM_Listeners.register();
        HM_StatusEffects.register();
        HM_LootTables.register();
    }
}