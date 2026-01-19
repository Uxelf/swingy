package my.rpg.model.combatSystem;

import my.rpg.model.hero.Hero;
import my.rpg.model.monster.Monster;
import my.rpg.model.movementComponent.MovementComponent;

import java.util.List;

public class CombatManager {

    public static void checkForCombat(Hero player, List<Monster> monstersList){
        int playerX = player.getMovementComponent().getX();
        int playerY = player.getMovementComponent().getY();

        for (Monster monster : monstersList){
            MovementComponent mMovComp = monster.getMovementComponent();
            if (mMovComp.getX() == playerX && mMovComp.getY() == playerY){
                CombatSystem.startCombat(player, monster);
            }
        }
    }
}
