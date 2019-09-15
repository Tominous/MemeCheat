package cc.ghast.memecheat.impl.manager;

import cc.ghast.memecheat.MemeCheat;

public abstract class Manager {
    public static MemeCheat memeCheat = MemeCheat.getInstance();

    public void init(){}
    public void disinit(){}

}
