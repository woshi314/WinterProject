package com.pocket_monster.service.impl;

import com.pocket_monster.entity.Monster;
import com.pocket_monster.entity.MonsterSpecies;
import com.pocket_monster.entity.Result;
import com.pocket_monster.enumeration.NatureType;
import com.pocket_monster.repository.Repository;
import com.pocket_monster.service.IMonsterService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MonsterServiceImpl implements IMonsterService {

    @Override
    public Result addMonster(MonsterSpecies monsterSpecies, String nickname, Integer age, NatureType nature) {
        int id = 0;
        ArrayList<Monster> monsters = com.pocket_monster.repository.Repository.monsters;
        if (monsters == null) {
            return Result.fail("怪物合集不存在");
        }
        if (!monsters.isEmpty()) {
            id = monsters.get(monsters.size() - 1).getId() + 1;
        }
        Monster monster = new Monster(
                id,
                monsterSpecies,
                nickname,
                age,
                nature,
                LocalDateTime.now(),
                new ArrayList<>());
        Repository.monsters.add(monster);
        return Result.ok();
    }


    @Override
    public Result deleteMonster(String nickname) {
        boolean success = false;
        //获取全局实例
        ArrayList<Monster> monsters = com.pocket_monster.repository.Repository.monsters;
        //遍历查找指定名字的怪兽
        for(Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                monsters.remove(monster);
                success=true;
            }
        }
        return success?Result.ok():Result.fail("没有找到指定名字的怪兽");
    }

    @Override
    public Result readMonster(String nickname) {
        boolean success = false;
        //获取全局实例
        ArrayList<Monster> monsters = com.pocket_monster.repository.Repository.monsters;
        //遍历查找指定名字的怪兽
        for(Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                System.out.println(monster);
                success=true;
            }
        }
        return success?Result.ok():Result.fail("没有找到指定名字的怪兽");
    }

    @Override
    public Result updateMonster(String nickname,Monster monster){
        boolean success = false;
        //获取全局实例
        ArrayList<Monster> monsters = com.pocket_monster.repository.Repository.monsters;
        //遍历查找指定名字的怪兽
        for(Monster monster1 : monsters) {
            if (monster1.getNickname().equals(nickname)) {
                monster1=monster;
                success=true;
            }
        }
        return success?Result.ok():Result.fail("没有找到指定名字的怪兽");
    }


}
