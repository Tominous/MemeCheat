package cc.ghast.memecheat.impl.manager;

import cc.ghast.memecheat.impl.commands.MemeCommand;

public class CommandManager extends Manager{

    public void init(){
        new MemeCommand(memeCheat);
    }
}
