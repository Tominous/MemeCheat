package cc.ghast.memecheat.algorithm;

public enum PossibilityEnum {
    BLATANT(100, 5, 100),
    HIGH(90, 10, 100),
    SECURE(80, 12, 100),
    PROBABLE(70, 15, 100),
    LOW(60, 18, 100),
    FALSE(50, 20 ,100),
    NOT(30, 25, 0);

    int percentage; // Percentage of accuracy
    int falsePossibility; // Percentage of possibility of falsing on each check
    int gatheredData; // Amount of violations needed
    PossibilityEnum(int percentage, int falsePossibility, int gatheredData){
        this.percentage = percentage;
        this.falsePossibility = falsePossibility;
        this.gatheredData = gatheredData;
    }
}
