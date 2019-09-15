package cc.ghast.memecheat.api.manager;

import cc.ghast.memecheat.MemeCheat;

public abstract class Manager {
    public static MemeCheat memeCheat = MemeCheat.getInstance();

    public abstract void init();
    public abstract void disinit();

}
