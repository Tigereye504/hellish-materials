package net.tigereye.hellishmaterials;

import net.fabricmc.api.ModInitializer;
import net.tigereye.hellishmaterials.registration.*;
import net.tigereye.hellishmaterials.registration.HMItems;

public class HellishMaterials implements ModInitializer{
    
    public static final String MODID = "hellish-materials";
    public static final boolean DEBUG = false;

    @Override
    public void onInitialize() {
        HMConfig.init();
        HMEntities.register();
        HMItems.register();
        HMListeners.register();
        HMStatusEffects.register();
        HMLootTables.register();
    }
}