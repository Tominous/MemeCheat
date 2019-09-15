package cc.ghast.memecheat.impl.manager;


import cc.ghast.memecheat.api.packet.EventAdapterListener;
import cc.ghast.memecheat.api.packet.PacketAdapterListener;
import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;

public class ProtocolManager extends Manager{
    @Getter private EventAdapterListener eventAdapterListener;
    public ProtocolManager(){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapterListener(memeCheat));
        eventAdapterListener = new EventAdapterListener(memeCheat);
    }
}
