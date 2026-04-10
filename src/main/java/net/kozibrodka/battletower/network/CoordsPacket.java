package net.kozibrodka.battletower.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.battletower.entity.EntityGolem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.ClientWorld;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CoordsPacket extends Packet implements ManagedPacket<CoordsPacket> {

    public static final PacketType<CoordsPacket> TYPE = PacketType.builder(true, true, CoordsPacket::new).build();

    private int x;
    private int y;
    private int z;
    private int entityId;

    public CoordsPacket() {
    }

    public CoordsPacket(int posX, int posY, int posZ, int id) {
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.entityId = id;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.x = stream.readInt();
            this.y = stream.readInt();
            this.z = stream.readInt();
            this.entityId = stream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.x);
            stream.writeInt(this.y);
            stream.writeInt(this.z);
            stream.writeInt(this.entityId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void apply(NetworkHandler arg) {
        switch (FabricLoader.INSTANCE.getEnvironmentType()) {
            case CLIENT -> handleClient(arg);
            case SERVER -> handleServer(arg);
        }
    }

    @Environment(EnvType.CLIENT)
    public void handleClient(NetworkHandler networkHandler) {
        ClientPlayerEntity player = (ClientPlayerEntity) PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(player == null){
            return;
        }
        EntityGolem golem1 = (EntityGolem) ((ClientWorld)player.world).getEntity(this.entityId);
        if(golem1 != null){
            golem1.towerTopCoord = new Vec3i(this.x,this.y,this.z);
        }
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public @NotNull PacketType<CoordsPacket> getType() {
        return TYPE;
    }
}
