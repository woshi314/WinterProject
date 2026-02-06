package com.pocket_monster;

import com.pocket_monster.json.JsonLoader;
import com.pocket_monster.menu.MainMenu;
import com.pocket_monster.repository.Repository;

public class PocketMonsterApplication {
    public static void main(String[] args) {
        JsonLoader jsonLoader = new JsonLoader();
        jsonLoader.initialize();
        Repository.monsters = new java.util.ArrayList<>();
        MainMenu.start();
    }
}
