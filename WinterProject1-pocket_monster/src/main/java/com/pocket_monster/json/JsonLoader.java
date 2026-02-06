package com.pocket_monster.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pocket_monster.entity.MonsterSpecies;
import com.pocket_monster.entity.Result;
import com.pocket_monster.enumeration.MonsterType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonLoader {

    public static ArrayList<MonsterSpecies> monsterSpeciesList;
    
    public Result initialize(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("MonsterSpeciesList.json");
            if(inputStream == null) return Result.fail("怪兽种类json文件不存在");

            ObjectMapper objectMapper = new ObjectMapper();
            monsterSpeciesList = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, MonsterSpecies.class));

            return Result.ok();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
