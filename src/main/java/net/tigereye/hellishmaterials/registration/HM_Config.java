package net.tigereye.hellishmaterials.registration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.loader.api.FabricLoader;

public class HM_Config {
    public static final float ZOMBIE_PIGLIN_BATET_TOOL_CHANCE;
    public static final float ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE;
    public static final float ZOMBIE_PIGLIN_BATET_CHAMPION_CHANCE;
    public static final float PIGLIN_LUSS_TOOL_CHANCE;
    public static final float WITHER_SKELETON_VULD_TOOL_CHANCE;
    public static final float WITHER_SKELETON_VULD_ARMOR_CHANCE;
    public static final float WITHER_SKELETON_VULD_CHAMPION_CHANCE;
    
	public static final Logger LOGGER = LogManager.getLogger();

    static {
		File configDir = new File(FabricLoader.getInstance().getConfigDir().toFile(), "hellish_materials");

		if (!configDir.exists()) {
            LOGGER.info("[Hellish Materials] creating configuration directory: " + configDir.getAbsolutePath());
			if (!configDir.mkdir()) {
				LOGGER.warn("[Hellish Materials] Could not create configuration directory: " + configDir.getAbsolutePath());
			}
		}

		File configFile = new File(configDir, "hellish-materials.properties");
		Properties properties = new Properties();

		if (configFile.exists()) {
			try (FileInputStream stream = new FileInputStream(configFile)) {
				properties.load(stream);
			} catch (IOException e) {
				LOGGER.warn("[Hellish Materials] Could not read property file '" + configFile.getAbsolutePath() + "'", e);
			}
		}

		 ZOMBIE_PIGLIN_BATET_TOOL_CHANCE = Float.parseFloat(properties.getProperty("zombie-piglin-batet-tool-chance", "0.02"));
        ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE = Float.parseFloat(properties.getProperty("zombie-piglin-batet-armor-chance", "0.02"));
        ZOMBIE_PIGLIN_BATET_CHAMPION_CHANCE = Float.parseFloat(properties.getProperty("zombie-piglin-batet-champion-chance", "0.001"));
        PIGLIN_LUSS_TOOL_CHANCE = Float.parseFloat(properties.getProperty("piglin-luss-tool-chance", "0.02"));
        WITHER_SKELETON_VULD_TOOL_CHANCE = Float.parseFloat(properties.getProperty("wither-skeleton-vuld-tool-chance", "0.02"));
        WITHER_SKELETON_VULD_ARMOR_CHANCE = Float.parseFloat(properties.getProperty("wither-skeleton-vuld-armor-chance", "0.02"));
        WITHER_SKELETON_VULD_CHAMPION_CHANCE = Float.parseFloat(properties.getProperty("wither-skeleton-vuld-champion-chance", "0.001"));
        //save the freshly loaded properties so that any defaults will be saved
        properties.setProperty("zombie-piglin-batet-tool-chance", String.valueOf(ZOMBIE_PIGLIN_BATET_TOOL_CHANCE));
        properties.setProperty("zombie-piglin-batet-armor-chance", String.valueOf(ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE));
        properties.setProperty("zombie-piglin-batet-champion-chance", String.valueOf(ZOMBIE_PIGLIN_BATET_CHAMPION_CHANCE));
        properties.setProperty("piglin-luss-tool-chance", String.valueOf(PIGLIN_LUSS_TOOL_CHANCE));
        properties.setProperty("wither-skeleton-vuld-tool-chance", String.valueOf(WITHER_SKELETON_VULD_TOOL_CHANCE));
        properties.setProperty("wither-skeleton-vuld-armor-chance", String.valueOf(WITHER_SKELETON_VULD_ARMOR_CHANCE));
        properties.setProperty("wither-skeleton-vuld-champion-chance", String.valueOf(WITHER_SKELETON_VULD_CHAMPION_CHANCE));

		try (FileOutputStream stream = new FileOutputStream(configFile)) {
			properties.store(stream, "Hellish Materials properties file");
		} catch (IOException e) {
			LOGGER.warn("[Hellish Materials] Could not store property file '" + configFile.getAbsolutePath() + "'", e);
		}
    }
    
    public static float poke(){return ZOMBIE_PIGLIN_BATET_ARMOR_CHANCE;}
}
