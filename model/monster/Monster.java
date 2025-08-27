package my.rpg.model.monster;

import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.model.entity.Entity;
import my.rpg.model.hero.Hero;
import my.rpg.model.hpComponent.DeathListener;

public class Monster extends Entity {

    private Monster(String name, int level, int attack, int defense, int maxHp){
        super(name, level, attack, defense, maxHp);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    protected void onDeath() {
        GameScene gameScene = GameScene.getInstance();
        if (gameScene != null){
            gameScene.onMonsterDeath(this);
        }
    }

    public static class MonsterBuilder extends EntityBuilder{
        @Override
        public Monster build(){
            return new Monster(name, level, attack, defense, maxHp);
        }
    }
}
