package my.rpg.model.combatSystem;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.model.hero.Hero;
import my.rpg.model.monster.Monster;
import my.rpg.model.utils.Utils;

public class CombatSystem {

    public static void startCombat(Hero player, Monster monster){
        printStartingText(monster);
        readInput(player, monster);
    }

    private static void printStartingText(Monster monster){
        println("A combat started!\n");
        println("Opponent: " + monster.getName());
        println("Stats:");
        println("Level " + monster.getLevel());
        println("Hp " + monster.getHp());
        println("Atk " + monster.getAttack());
        println("Def " + monster.getDefense());

        println("\nWhat will you do?");
        println(">> Attack");
        println(">> Run away (50% chance success)");
    }

    private static void println(String str){
        System.out.println(str);
    }

    private static void readInput(Hero player, Monster monster){
        InputReader inputReader = new InputReader.InputReaderBuilder()
                .bind("a", () -> attack(player, monster))
                .bind("attack", () -> attack(player, monster))
                .bind("r", () -> tryToRun(player, monster))
                .bind("run", () -> tryToRun(player, monster))
                .build();

        inputReader.readInput();
    }

    private static void attack(Hero player, Monster monster){
        int total = player.getAttack() + monster.getAttack();
        while (player.getHp() > 0 && monster.getHp() > 0){
            int rng = (int)(Math.random() * total);
            if (rng < player.getAttack()){
                monster.takeDamage(player.getAttack());
            }
            else{
                player.takeDamage(monster.getAttack());
            }
        }
    }

    private static void tryToRun(Hero player, Monster monster){
        int rng = (int)(Math.random() * 2);
        if (rng == 0){
            println("You couldn't escape! Prepare for combat");
            Utils.waitMilliseconds(500);
            attack(player, monster);
        }
        else {
            println("You escaped!");
            Utils.waitMilliseconds(500);
        }
    }
}
