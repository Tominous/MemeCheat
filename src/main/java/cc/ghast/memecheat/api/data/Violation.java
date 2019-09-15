package cc.ghast.memecheat.api.data;

import cc.ghast.memecheat.api.check.Check;
import lombok.Getter;
import lombok.Setter;

public class Violation {
    @Getter private final Check check;
    @Getter @Setter private int violationAmount;

    public Violation(Check check, int violationAmount){
        this.check = check;
        this.violationAmount = violationAmount;
    }
}
