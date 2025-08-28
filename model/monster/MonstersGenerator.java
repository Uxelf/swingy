package my.rpg.model.monster;

import my.rpg.model.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MonstersGenerator {

    public static List<Monster> generateMonsters(int level){
        int amount = ((level - 1 ) * 5 + 11 - (level%2)) / 2;
        int points = 10 + level * 2;
        int variance = 4;

        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int attack = points / 3 + Utils.randomInRange(-variance / 2, variance / 2);
            int defense = points / 3 + Utils.randomInRange(-variance / 2, variance / 2);
            int hp = points - attack - defense;
            Monster monster = (Monster) new Monster.MonsterBuilder()
                    .setAttack(attack)
                    .setDefense(defense)
                    .setMaxHp(hp)
                    .setName(generateName())
                    .setLevel(level)
                    .build();
            monsters.add(monster);
        }

        return monsters;
    }

    private static String generateName(){
        String[] names = {"Joe", "Vampire", "Slime", "Lion", "Goblin", "Angry bunny", "Door", "The Bus Driver", "ඞ", "The President"};
        int rng = (int)(Math.random() * names.length);
        return names[rng];
    }
}

