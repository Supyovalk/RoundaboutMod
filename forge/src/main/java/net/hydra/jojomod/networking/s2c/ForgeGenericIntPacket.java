package net.hydra.jojomod.networking.s2c;

import net.hydra.jojomod.event.powers.StandUser;
import net.hydra.jojomod.networking.ModPacketHandler;
import net.hydra.jojomod.util.MainUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeGenericIntPacket {
    private final byte taskNo;
    private final int theInt;

    public ForgeGenericIntPacket(byte taskNo, int theInt){
        this.taskNo = taskNo;
        this.theInt = theInt;
    }
    public ForgeGenericIntPacket(FriendlyByteBuf buf){
        this.taskNo = buf.readByte();
        this.theInt = buf.readInt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeByte(taskNo);
        buf.writeInt(theInt);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()-> {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                MainUtil.handleIntPacketS2C(player,theInt,taskNo);
            }
        });
        return true;
    }
}