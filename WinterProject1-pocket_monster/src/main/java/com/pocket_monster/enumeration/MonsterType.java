package com.pocket_monster.enumeration;

public enum MonsterType {
    /**
     * 共18种属性
     */
    NULL("无"),
    NORMAL("一般"),
    FIRE("火"),
    WATER("水"),
    GRASS("草"),
    ELECTRIC("电"),
    ICE("冰"),
    FIGHTING("格斗"),
    POISON("毒"),
    GROUND("地面"),
    FLYING("飞行"),
    PSYCHIC("超能力"),
    BUG("虫"),
    ROCK("岩石"),
    GHOST("幽灵"),
    DRAGON("龙"),
    DARK("恶"),
    STEEL("钢"),
    FAIRY("妖精");

    public String getTypeName() {
        return typeName;
    }

    private final String typeName;

    private MonsterType(String typeName) {
        this.typeName = typeName;
    }
}
