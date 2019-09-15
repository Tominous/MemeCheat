package cc.ghast.memecheat;

import cc.ghast.memecheat.MemeCheat;
import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.managers.CommandManager;
import cc.ghast.memecheat.managers.ConfigManager;
import cc.ghast.memecheat.managers.PlayerDataManager;
import cc.ghast.memecheat.managers.ProtocolManager;
import cc.ghast.memecheat.metrics.Metrics;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class API {

    @Getter private PlayerDataManager playerDataManager;
    @Getter private ConfigManager configManager;
    @Getter private ProtocolManager protocolManager;
    @Getter private CommandManager commandManager;
    @Getter private List<Manager> managers = new ArrayList<>();
    @Getter private List<Class> checks = new ArrayList<>();

    public API(){
        initManagers();
    }

    private void initManagers(){
        Metrics metrics = new Metrics(MemeCheat.getInstance());
        playerDataManager = new PlayerDataManager();
        commandManager = new CommandManager();
        protocolManager = new ProtocolManager();
        configManager = new ConfigManager();
        managers.addAll(Arrays.asList(playerDataManager, configManager, commandManager, protocolManager));
        managers.forEach(manager -> manager.init());
    }

    public void disinitManagers(){
        managers.forEach(manager -> manager.disinit());
    }
}
