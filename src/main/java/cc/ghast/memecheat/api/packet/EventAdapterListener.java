package cc.ghast.memecheat.api.packet;

import cc.ghast.memecheat.MemeCheat;
import cc.ghast.memecheat.api.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventAdapterListener implements Listener {
    public EventAdapterListener(MemeCheat memeCheat){
        Bukkit.getPluginManager().registerEvents(this, memeCheat);
    }

    @EventHandler
    public void onEvent(PlayerMoveEvent event){
        PlayerData data = MemeCheat.getInstance().getApi().getPlayerDataManager().getData(event.getPlayer());
        data.getCheckManager().getChecks().forEach(check->check.handle(data, event));
    }
}
