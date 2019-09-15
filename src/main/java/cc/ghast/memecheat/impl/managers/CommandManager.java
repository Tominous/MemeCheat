package cc.ghast.memecheat.impl.managers;

import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.impl.commands.MemeCommand;

public class CommandManager extends Manager {

    public void init(){
        new MemeCommand(memeCheat);
    }

    public void disinit(){

    }
}
