package com.pocket_monster.service.impl;

import com.pocket_monster.entity.Monster;
import com.pocket_monster.entity.Result;
import com.pocket_monster.entity.Story;
import com.pocket_monster.repository.Repository;
import com.pocket_monster.service.IStoryService;

import java.util.ArrayList;

public class StoryServiceImpl implements IStoryService {

    @Override
    public Result addStory(String nickname, Story story) {
        boolean success = false;
        ArrayList<Monster> monsters = Repository.monsters;
        for(Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                if (monster.getStoryList() == null) {
                    monster.setStoryList(new ArrayList<>());
                }
                monster.getStoryList().add(story);
                success = true;
            }
        }
        return success ? Result.ok() : Result.fail("没有找到指定名字的怪兽");
    }

    @Override
    public Result readStory(String nickname) {
        boolean success = false;
        ArrayList<Monster> monsters = Repository.monsters;
        for(Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                ArrayList<Story> storyList = monster.getStoryList();
                if (storyList != null && !storyList.isEmpty()) {
                    for (Story story : storyList) {
                        System.out.println(story);
                    }
                    success = true;
                } else {
                    return Result.fail("该怪兽暂无故事");
                }
            }
        }
        return success ? Result.ok() : Result.fail("没有找到指定名字的怪兽");
    }
}
