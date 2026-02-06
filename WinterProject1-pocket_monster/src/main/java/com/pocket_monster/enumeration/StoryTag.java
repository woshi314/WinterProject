package com.pocket_monster.enumeration;

public enum StoryTag {
    ADVENTURE("冒险"),
    GROWTH("成长"),
    FRIENDSHIP("友情"),
    DAILY_LIFE("日常"),
    TRAGEDY("悲剧");

    public String getTagName() {
        return tagName;
    }

    private final String tagName;

    private StoryTag(String tagName) {
        this.tagName = tagName;
    }
}
