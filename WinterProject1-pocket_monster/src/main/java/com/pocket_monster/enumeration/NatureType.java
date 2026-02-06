package com.pocket_monster.enumeration;

public enum NatureType {
    HARDY("勤奋"),
    LONELY("怕寂寞"),
    ADAMANT("固执"),
    NAUGHTY("顽皮"),
    BRAVE("勇敢"),
    BOLD("大胆"),
    DOCILE("坦率"),
    IMPISH("淘气"),
    LAX("乐天"),
    RELAXED("悠闲"),
    MODEST("内敛"),
    MILD("慢吞吞"),
    BASHFUL("害羞"),
    RASH("马虎"),
    QUIET("冷静"),
    CALM("温和"),
    GENTLE("温顺"),
    CAREFUL("慎重"),
    QUIRKY("浮躁"),
    SASSY("自大"),
    TIMID("胆小"),
    HASTY("急躁"),
    JOLLY("爽朗"),
    NAIVE("天真"),
    SERIOUS("认真");

    public String getNatureName() {
        return natureName;
    }

    private final String natureName;

    private NatureType(String natureName) {
        this.natureName = natureName;
    }
}
