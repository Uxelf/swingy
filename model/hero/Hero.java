package my.rpg.model.hero;

import my.rpg.Main;
import my.rpg.model.artifact.Artifact;
import my.rpg.model.artifact.Helm;
import my.rpg.model.entity.Entity;
import my.rpg.model.hpComponent.HpComponent;

public class Hero extends Entity {

    private final HeroClass heroClass;
    private int experience;
    private Artifact artifact;

    private Hero(String name, HeroClass heroClass, int level, int attack, int defense, int maxHp, Artifact artifact){
        super(name, level, attack, defense, maxHp);
        this.heroClass = heroClass;
        equipArtifact(artifact);
    }

    public HeroClass getHeroClass() { return heroClass;}
    public int getExperience() { return experience;}

    @Override
    public int getAttack() {
        return attack + (artifact != null? artifact.getAttack() : 0);
    }

    @Override
    public int getDefense() {
        return defense + (artifact != null? artifact.getDefense() : 0);
    }

    @Override
    protected void onDeath() {
        System.out.println("Player is dead");
    }

    public void equipArtifact(Artifact newArtifact){
        if (artifact != null)
            hpComponent.addMaxHp(-artifact.getHp());
        if (newArtifact != null)
            hpComponent.addMaxHp(newArtifact.getHp());
        artifact = newArtifact;
    }

    private int nextLevelNeededExp(){ return level * 1000 + (int)Math.pow(level - 1, 2) * 450; }
    public void gainExperience(int expGained){
        experience += expGained;
        if (experience > nextLevelNeededExp())
            LevelUp();
    }
    private void LevelUp(){
        experience -= nextLevelNeededExp();
        level += 1;
    }

    public static class HeroBuilder{
        private String name;
        private HeroClass heroClass;
        private int level;
        private int attack;
        private int defense;
        private int maxHp;
        private Artifact artifact = null;


        public HeroBuilder setArtifact(Artifact artifact) {
            this.artifact = artifact;
            return this;
        }

        public HeroBuilder setAttack(int attack) {
            this.attack = attack;
            return this;
        }

        public HeroBuilder setDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public HeroBuilder setHeroClass(HeroClass heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        public HeroBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public HeroBuilder setMaxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public HeroBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Hero Build(){
            return new Hero(name, heroClass, level, attack, defense, maxHp, artifact);
        }
    }
}
