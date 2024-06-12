package net.hydra.jojomod.networking.s2c;

import net.hydra.jojomod.event.powers.TimeStop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeTimeStoppingEntityRemovalPacket {
    private final int entityID;

    public ForgeTimeStoppingEntityRemovalPacket(int entityID){
        this.entityID = entityID;
    }
    public ForgeTimeStoppingEntityRemovalPacket(FriendlyByteBuf buf){
        this.entityID = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(entityID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                ((TimeStop) player.level()).processTSRemovePacket(entityID);
            }
        });
        return true;
    }
}