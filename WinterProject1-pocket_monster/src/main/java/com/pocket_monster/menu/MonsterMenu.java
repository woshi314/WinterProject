package com.pocket_monster.menu;

import com.pocket_monster.entity.Monster;
import com.pocket_monster.entity.MonsterSpecies;
import com.pocket_monster.entity.Result;
import com.pocket_monster.enumeration.NatureType;
import com.pocket_monster.json.JsonLoader;
import com.pocket_monster.repository.Repository;
import com.pocket_monster.service.IMonsterService;
import com.pocket_monster.service.impl.MonsterServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class MonsterMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IMonsterService monsterService = new MonsterServiceImpl();

    public static void start() {
        while (true) {
            System.out.println("\n===== 小怪兽管理 =====");
            System.out.println("1. 创建小怪兽");
            System.out.println("2. 显示所有小怪兽");
            System.out.println("3. 查找小怪兽");
            System.out.println("4. 删除小怪兽");
            System.out.println("0. 返回主菜单");
            System.out.print("请选择功能：");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createMonster();
                    break;
                case 2:
                    displayAllMonsters();
                    break;
                case 3:
                    findMonster();
                    break;
                case 4:
                    deleteMonster();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    private static void createMonster() {
        System.out.println("\n===== 创建小怪兽 =====");

        System.out.println("可选择的怪兽种类：");
        ArrayList<MonsterSpecies> monsterSpecies = JsonLoader.monsterSpeciesList;

        for (int i = 0; i < monsterSpecies.size(); i++) {
            System.out.printf("%-10s", monsterSpecies.get(i).getName()); //左对齐，占10字符宽

            if ((i + 1) % 5 == 0) {  //每5个换行
                System.out.println();
            }
        }
        if (monsterSpecies.size() % 5 != 0) {
            System.out.println();
        }

        System.out.print("请输入怪兽种类名字：");
        String speciesName = scanner.nextLine();

        MonsterSpecies species=null;
        for (MonsterSpecies species1 :monsterSpecies) {
            if(species1.getName().equals(speciesName)){
                species=species1;
                break;
            }
        }
        if(species==null){
            System.out.println("没有对应的怪兽种类");
            return;
        }

        System.out.print("请输入昵称：");
        String nickname = scanner.nextLine();

        System.out.print("请输入年龄：");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("可选择的性格：");
        NatureType[] natures = NatureType.values();
        for (int i = 0; i < natures.length; i++) {
            System.out.printf("%-6s", natures[i].getNatureName());

            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        System.out.print("请选择性格：");
        String natureStr = scanner.nextLine();

        NatureType nature = null;
        for (NatureType natureType : natures) {
            if (natureType.getNatureName().equals(natureStr)) {
                nature = natureType;
                break;
            }
        }

        if (nature == null) {
            System.out.println("无效的性格");
            return;
        }
        Result result=monsterService.addMonster(species, nickname, age, nature);
        if (result.getSuccess()) {
            System.out.println("创建成功!");;
        }else {
            System.out.println("创建失败...");
        }
    }

    private static void displayAllMonsters() {
        System.out.println("\n===== 所有小怪兽列表 =====");
        ArrayList<Monster> monsters = Repository.monsters;
        if (monsters == null || monsters.isEmpty()) {
            System.out.println("暂无小怪兽数据");
            return;
        }

        for (Monster monster : monsters) {
            System.out.println(monster);
        }
    }

    private static void findMonster() {
        System.out.println("\n===== 查找小怪兽 =====");
        System.out.print("请输入要查找的怪兽昵称：");
        String nickname = scanner.nextLine();

        Result result = monsterService.readMonster(nickname);
        if (!result.getSuccess()) {
            System.out.println(result.getErrorMsg());
        }
    }

    private static void deleteMonster() {
        System.out.println("\n===== 删除小怪兽 =====");
        System.out.print("请输入要删除的怪兽昵称：");
        String nickname = scanner.nextLine();

        Result result = monsterService.deleteMonster(nickname);
        if (result.getSuccess()) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败：" + result.getErrorMsg());
        }
    }
}
