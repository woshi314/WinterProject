package com.pocket_monster.menu;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            System.out.println("\n===== 宝可梦管理系统 =====");
            System.out.println("1. 小怪兽管理");
            System.out.println("2. 故事管理(支持AI生成故事)");
            System.out.println("3. 简单统计");
            System.out.println("0. 退出系统");
            System.out.print("请选择功能：");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MonsterMenu.start();
                    break;
                case 2:
                    StoryMenu.start();
                    break;
                case 3:
                    StatisticsMenu.start();
                    break;
                case 0:
                    System.out.println("感谢使用，再见！");
                    return;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }
}
