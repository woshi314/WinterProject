package com.pocket_monster.service;

import com.pocket_monster.entity.Result;
import com.pocket_monster.entity.Story;

public interface IStoryService {

    Result addStory(String nickname, Story story);

    Result readStory(String nickname);
}
