package cc.ghast.memecheat.api.manager;

import cc.ghast.memecheat.api.data.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataManager extends Manager{
    private HashMap<Player, PlayerData> playerData = new HashMap<>();

    public PlayerData getData(Player player){
        return playerData.computeIfAbsent(player, PlayerData::new);
    }
}
