package com.pocket_monster.service;

import com.pocket_monster.entity.Monster;
import com.pocket_monster.entity.MonsterSpecies;
import com.pocket_monster.entity.Result;
import com.pocket_monster.enumeration.NatureType;

public interface IMonsterService {

    /**
     * 初始化创建怪兽
     * @param monsterSpecies
     * @param nickname
     * @param age
     * @param nature
     * @return
     */
    public Result addMonster(MonsterSpecies monsterSpecies,
                             String nickname,
                             Integer age,
                             NatureType nature
                           );

    /**
     * 根据昵称删除怪兽
     *
     * @param nickname 要删除的怪兽名字
     * @return
     */
    public Result deleteMonster(String nickname);

    public Result readMonster(String nickname);

    Result updateMonster(String nickname,
                         Monster monster);
}
