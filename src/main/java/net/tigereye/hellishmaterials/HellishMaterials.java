package net.tigereye.hellishmaterials;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;
import net.tigereye.hellishmaterials.datapack.LussDustLootManager;
import net.tigereye.hellishmaterials.registration.*;
import net.tigereye.hellishmaterials.registration.HMItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HellishMaterials implements ModInitializer{
    
    public static final String MODID = "hellish-materials";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final boolean DEBUG = false;

    @Override
    public void onInitialize() {
        HMConfig.init();
        HMEntities.register();
        HMItems.register();
        HMListeners.register();
        HMStatusEffects.register();
        HMLootTables.register();
        HMLussRandomAttackEffects.register();
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new LussDustLootManager());
    }
}