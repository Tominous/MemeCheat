package cc.ghast.memecheat;

import cc.ghast.memecheat.api.API;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class MemeCheat extends JavaPlugin {
    @Getter private static MemeCheat instance;
    @Getter private API api;

    public void onEnable(){
        instance = this;
        api = new API();
    }

    public void onDisable(){
        api.disinitManagers();
    }
}
