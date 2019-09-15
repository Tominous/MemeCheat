package cc.ghast.memecheat.api.manager;

import cc.ghast.memecheat.impl.commands.MemeCommand;

public class CommandManager extends Manager{

    public void init(){
        new MemeCommand(memeCheat);
    }
}
