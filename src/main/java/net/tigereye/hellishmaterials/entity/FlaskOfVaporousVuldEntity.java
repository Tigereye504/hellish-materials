package net.tigereye.hellishmaterials.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tigereye.hellishmaterials.HellishMaterials;
import net.tigereye.hellishmaterials.registration.HMEntities;
import net.tigereye.hellishmaterials.registration.HMItems;

import java.util.UUID;

public class FlaskOfVaporousVuldEntity extends ThrownItemEntity {

    public static final Identifier SPAWN_PACKET = new Identifier(HellishMaterials.MODID, "flask_of_vaporized_vuld_spawn_packet");

    public FlaskOfVaporousVuldEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FlaskOfVaporousVuldEntity(World world, LivingEntity owner) {
        super(HMEntities.FLASK_OF_VAPOROUS_VULD_ENTITY, owner, world);
    }

    public FlaskOfVaporousVuldEntity(World world, double x, double y, double z) {
        super(HMEntities.FLASK_OF_VAPOROUS_VULD_ENTITY, x, y, z, world);
    }

    @Environment(EnvType.CLIENT)
    public FlaskOfVaporousVuldEntity(World world, double x, double y, double z, int id, UUID uuid) {
        super(HMEntities.FLASK_OF_VAPOROUS_VULD_ENTITY, x, y, z, world);
        setId(id);
        setUuid(uuid);
    }

    @Override
    protected Item getDefaultItem() {
        return HMItems.FLASK_OF_VAPOROUS_VULD;
    }

    @Override
    public Packet<?> createSpawnPacket(){
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());

        // entity position
        packet.writeDouble(getX());
        packet.writeDouble(getY());
        packet.writeDouble(getZ());
        // entity id & uuid
        packet.writeInt(getId());
        packet.writeUuid(getUuid());

        return ServerPlayNetworking.createS2CPacket(SPAWN_PACKET, packet);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        // called on entity hit. Create Vaporous Vuld on the hit location!
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        world.setBlockState(new BlockPos(this.getPos()), HMItems.VAPOROUS_VULD.getDefaultState());
        this.discard();
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if(blockHitResult.isInsideBlock()){
            world.setBlockState(blockHitResult.getBlockPos(), HMItems.VAPOROUS_VULD.getDefaultState());
        }
        else{
            world.setBlockState(blockHitResult.getBlockPos().offset(blockHitResult.getSide()), HMItems.VAPOROUS_VULD.getDefaultState());
        }
        this.discard();
    }
}
