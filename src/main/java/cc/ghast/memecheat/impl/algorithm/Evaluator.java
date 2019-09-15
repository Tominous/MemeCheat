package cc.ghast.memecheat.impl.algorithm;

import cc.ghast.memecheat.api.check.Check;
import cc.ghast.memecheat.api.data.PlayerData;
import cc.ghast.memecheat.api.data.Violation;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
    /*
    DEFINITELY HACKING: >10good, <2bad, BLATANT possibility
    HACKING:


     */


    public static PossibilityEnum isHacking(PlayerData data){
        // Percentage of accuracy
        int percentage = 100;
        // Percentage of possibility of falsing on each check
        double falsePossibility = 0;
        // Amount of violations needed
        int gatheredCheckAmount = data.getViolations().size();
        // Amount of total violations
        int gatheredData = 0;
        PossibilityEnum result = PossibilityEnum.LOW;
        List<Check> validChecks = new ArrayList<>();

        int good = 2;
        int bad = 0;

        // Update the gatheredData to get total violations + falsePosibility to get total percentage
        for (Violation vl : data.getViolations()) {
            gatheredData = +vl.getViolationAmount();
            falsePossibility = +vl.getCheck().getFalsePosibility();
            if (vl.getViolationAmount() > 15) validChecks.add(vl.getCheck());
            if (vl.getViolationAmount() > 20 && vl.getViolationAmount() < 30){
                good++;
            }
            if (vl.getViolationAmount() > 30 && vl.getViolationAmount() < 50){
                good =+ 2;
            }
            if (vl.getViolationAmount() > 50){
                good =+ (int) Math.ceil((double) vl.getViolationAmount()/10);
            }
        }

        for (int i = 1; i < 15; i++) if (gatheredData / gatheredCheckAmount < 5*i) bad=+ (int) Math.ceil((double) 3/i);

        // Mean of all false possibility
        falsePossibility = falsePossibility/gatheredCheckAmount;
        // Minimum checks
        if (gatheredData < 10) result = PossibilityEnum.NOT;
        if (validChecks.size() < 1) result =  PossibilityEnum.NOT;

        // Init good and bad. If it passed the two previous checks it gains 2 good elements


        // Add the amount of checks violated as a good
        //good = good + validChecks.size() - 2;

        PossibilityEnum highestPossibility = PossibilityEnum.NOT;
        for (PossibilityEnum value : PossibilityEnum.values()) {
            if (value.falsePossibility > falsePossibility && value.falsePossibility < highestPossibility.falsePossibility){
                highestPossibility = value;
            }
        }
        // Violation class not added in floobits
        if (good > 5 && bad < 5 && highestPossibility.falsePossibility <= 10.0) result = PossibilityEnum.SECURE;
        if (good > 8 && bad < 5 && highestPossibility.falsePossibility <= 10.0) result = PossibilityEnum.HIGH;
        if (good > 10 && bad < 3 && highestPossibility.falsePossibility <= 5.0) result = PossibilityEnum.BLATANT;
        return result;

    }
}
