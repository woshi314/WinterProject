package com.pocket_monster.entity;

import com.pocket_monster.enumeration.StoryTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    /**
     * 故事信息
     * 标题,内容,创建日期,故事标签
     */
    private String title;
    private String content;
    private LocalDateTime happenTime;
    private ArrayList<StoryTag> StoryTags;

    @Override
    public String toString() {
        return String.format("""
               标题:%s  内容:%s
               """
                , title, content);
    }
}
