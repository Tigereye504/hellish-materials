package net.tigereye.hellishmaterials.registration;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.entity.FlaskOfVaporousVuldEntity;

public class HMEntities {

    public static final EntityType<FlaskOfVaporousVuldEntity> FLASK_OF_VAPOROUS_VULD_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(HellishMaterials.MODID, "flask_of_vaporous_vuld"),
            FabricEntityTypeBuilder.<FlaskOfVaporousVuldEntity>create(SpawnGroup.MISC, FlaskOfVaporousVuldEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(64).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build()
    );

    public static void register(){

    }
}
