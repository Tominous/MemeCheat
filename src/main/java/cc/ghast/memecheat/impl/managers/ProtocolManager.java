package cc.ghast.memecheat.impl.managers;


import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.api.packet.EventAdapterListener;
import cc.ghast.memecheat.api.packet.PacketAdapterListener;
import com.comphenix.protocol.ProtocolLibrary;
import lombok.Getter;

public class ProtocolManager extends Manager {
    @Getter private EventAdapterListener eventAdapterListener;
    public ProtocolManager(){

    }

    public void init(){
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapterListener(memeCheat));
        eventAdapterListener = new EventAdapterListener(memeCheat);
    }

    public void disinit(){

    }
}
