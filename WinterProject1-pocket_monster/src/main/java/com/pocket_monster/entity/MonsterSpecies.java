package com.pocket_monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pocket_monster.enumeration.MonsterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonsterSpecies {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type1")
    private MonsterType primaryType;

    @JsonProperty("type2")
    private MonsterType secondaryType;

    @JsonProperty("description")
    private String description;

    @Override
    public String toString() {
        return String.format(
                """
                  图鉴编号:%s
                  名字:%s
                  属性:%s
                  图鉴描述:%s""",
                id,
                name,
                getPrimaryType().getTypeName() + (getSecondaryType() != null ? ","+getSecondaryType().getTypeName():""),
                description);
    }
}
