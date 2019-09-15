package cc.ghast.memecheat.managers;

import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.api.utils.configuration.Configuration;
import lombok.Getter;

public class ConfigManager extends Manager {
    @Getter
    private static Configuration settings;

    public void init() {
        settings = new Configuration("settings.yml", memeCheat);
    }

    public void disinit(){
        settings.save();
    }

    public void reload(){
        settings.save();
        init();
    }
}
