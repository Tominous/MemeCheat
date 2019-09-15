package cc.ghast.memecheat.managers;

import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.commands.MemeCommand;

public class CommandManager extends Manager {

    public void init(){
        new MemeCommand(memeCheat);
    }

    public void disinit(){

    }
}
