package cc.ghast.memecheat.api.packet.events;

import cc.ghast.memecheat.api.utils.location.Position;
import lombok.Getter;
import org.bukkit.entity.Player;

public class PacketPosition {
    @Getter private Player player;
    @Getter private Position to;
    @Getter private Position from;
    @Getter private long timestamp;
}
