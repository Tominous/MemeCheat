package cc.ghast.memecheat.api.data;

import cc.ghast.memecheat.api.check.Check;
import lombok.Getter;
import lombok.Setter;

public class Verbose {
    @Getter private final Check check;
    @Getter @Setter private int verboseAmount;

    public Verbose(Check check, int verboseAmount){
        this.check = check;
        this.verboseAmount = verboseAmount;
    }

}
