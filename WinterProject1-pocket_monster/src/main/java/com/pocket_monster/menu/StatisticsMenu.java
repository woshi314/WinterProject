package com.pocket_monster.menu;

import com.pocket_monster.entity.Monster;
import com.pocket_monster.repository.Repository;

import java.util.ArrayList;

public class StatisticsMenu {

    public static void start() {
        System.out.println("\n===== 简单统计 =====");
        ArrayList<Monster> monsters = Repository.monsters;
        if (monsters == null || monsters.isEmpty()) {
            System.out.println("暂无小怪兽数据");
            return;
        }

        System.out.println("小怪兽总数：" + monsters.size());
        int totalStories = 0;
        for (Monster monster : monsters) {
            int storyCount = monster.getStoryCount();
            totalStories += storyCount;
            System.out.println(monster.getNickname() + " 的故事数量：" + storyCount);
        }
        System.out.println("故事总数：" + totalStories);
    }
}
