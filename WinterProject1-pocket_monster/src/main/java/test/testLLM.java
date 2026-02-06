package test;

import com.pocket_monster.ai.LLM;
import com.pocket_monster.repository.Repository;

public class testLLM {
    public static void main(String[] args) {
        LLM llm = new LLM();
        System.out.println(llm.AIGenerate(Repository.monsters.toString()));
    }
}
