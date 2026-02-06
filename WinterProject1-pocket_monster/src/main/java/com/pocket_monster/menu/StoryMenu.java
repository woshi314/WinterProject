package com.pocket_monster.menu;

import com.pocket_monster.ai.LLM;
import com.pocket_monster.entity.Monster;
import com.pocket_monster.entity.Story;
import com.pocket_monster.enumeration.StoryTag;
import com.pocket_monster.repository.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class StoryMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("\n===== 故事管理 =====");
            System.out.println("1. 为小怪兽添加故事(支持AI生成故事内容)");
            System.out.println("2. 查看小怪兽的故事列表");
            System.out.println("0. 返回主菜单");
            System.out.print("请选择功能：");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStory();
                    break;
                case 2:
                    viewStories();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    private static void addStory() {
        System.out.println("\n===== 为小怪兽添加故事 =====");
        ArrayList<Monster> monsters = Repository.monsters;
        if (monsters == null || monsters.isEmpty()) {
            System.out.println("暂无小怪兽数据");
            return;
        }

        System.out.println("可选择的怪兽：");
        for (Monster monster : monsters) {
            System.out.println("- " + monster.getNickname());
        }

        System.out.print("请输入怪兽昵称：");
        String nickname = scanner.nextLine();

        Monster targetMonster = null;
        for (Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                targetMonster = monster;
                break;
            }
        }

        if (targetMonster == null) {
            System.out.println("未找到指定怪兽");
            return;
        }

        System.out.printf("是否使用AI生成?(Y/N)");
        String answer = scanner.nextLine();
        String content;
        LLM llm = new LLM();
        if (answer.toLowerCase().equals("y")||answer.toLowerCase().equals("Y")) {
            System.out.printf("你的提示词(比如可以简单说明故事走向):");
            String prompt = scanner.nextLine();
            System.out.println("AI开始生成,耗时可能较长!!!");
            content = llm.AIGenerate(prompt+"\n"+monsters.toString());
            System.out.println("AI已经生成故事:" + content);
        }else{
            System.out.print("请输入故事内容：");
            content = scanner.nextLine();
        }

        System.out.print("请输入故事标题：");
        String title = scanner.nextLine();

        System.out.println("可选择的标签：");
        StoryTag[] tags = StoryTag.values();
        for (int i=0;i<tags.length;i++) {
            System.out.printf("%-6s",tags[i].getTagName());

            if ((i + 1) % 5 == 0) {  // 每5个换行
                System.out.println();
            }
        }

        System.out.print("请选择标签：");
        String tagStr = scanner.nextLine();

        StoryTag selectedTag = null;
        for (StoryTag tag : tags) {
            if (tag.getTagName().equals(tagStr)) {
                selectedTag = tag;
                break;
            }
        }

        if (selectedTag == null) {
            System.out.println("无效的标签");
            return;
        }

        ArrayList<StoryTag> storyTags = new ArrayList<>();
        storyTags.add(selectedTag);

        Story story = new Story(title, content, LocalDateTime.now(), storyTags);

        if (targetMonster.getStoryList() == null) {
            targetMonster.setStoryList(new ArrayList<>());
        }

        targetMonster.getStoryList().add(story);
        System.out.println("故事添加成功！");
    }

    private static void viewStories() {
        System.out.println("\n===== 查看小怪兽的故事列表 =====");
        ArrayList<Monster> monsters = Repository.monsters;
        if (monsters == null || monsters.isEmpty()) {
            System.out.println("暂无小怪兽数据");
            return;
        }

        System.out.println("可选择的怪兽：");
        for (Monster monster : monsters) {
            System.out.println("- " + monster.getNickname() + " (故事数: " + monster.getStoryCount() + ")");
        }

        System.out.print("请输入怪兽昵称：");
        String nickname = scanner.nextLine();

        Monster targetMonster = null;
        for (Monster monster : monsters) {
            if (monster.getNickname().equals(nickname)) {
                targetMonster = monster;
                break;
            }
        }

        if (targetMonster == null) {
            System.out.println("未找到指定怪兽");
            return;
        }

        ArrayList<Story> storyList = targetMonster.getStoryList();
        if (storyList == null || storyList.isEmpty()) {
            System.out.println("该怪兽暂无故事");
            return;
        }

        System.out.println("\n" + nickname + " 的故事列表：");
        for (int i = 0; i < storyList.size(); i++) {
            Story story = storyList.get(i);
            System.out.println("\n故事 " + (i + 1) + ":");
            System.out.println("标题: " + story.getTitle());
            System.out.println("内容: " + story.getContent());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("发生时间: " + story.getHappenTime().format(formatter));
            ArrayList<StoryTag> tags = story.getStoryTags();
            if (tags != null && !tags.isEmpty()) {
                StringBuilder tagStr = new StringBuilder();
                for (StoryTag tag : tags) {
                    tagStr.append(tag.getTagName()).append(",");
                }
                System.out.println("标签: " + tagStr.substring(0, tagStr.length() - 1));
            }
        }
    }
}
