package my.rpg.model.monster;

import my.rpg.model.entity.Entity;
import my.rpg.model.hero.Hero;

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

    }

    public static class MonsterBuilder{
        private String name;
        private int level;
        private int attack;
        private int defense;
        private int maxHp;

        public MonsterBuilder setAttack(int attack){
            this.attack = attack;
            return this;
        }

        public MonsterBuilder setDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public MonsterBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public MonsterBuilder setMaxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public MonsterBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Monster build(){
            return new Monster(name, level, attack, defense, maxHp);
        }
    }
}
