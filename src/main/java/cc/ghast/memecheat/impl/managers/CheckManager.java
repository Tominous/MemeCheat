package cc.ghast.memecheat.impl.managers;

import cc.ghast.memecheat.api.check.Check;
import cc.ghast.memecheat.api.manager.Manager;
import cc.ghast.memecheat.impl.checks.combat.killaura.KillAuraA;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckManager extends Manager {

    @Getter private List<Check> checks = new ArrayList<>();

    public CheckManager(){
        init();
    }

    public void init(){
        checks.addAll(Arrays.asList(
                new KillAuraA()
        ));
    }

    public void disinit(){
        checks.clear();
    }
}
