package my.rpg.model.hero;

import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.model.artifact.Artifact;
import my.rpg.model.artifact.ArtifactType;
import my.rpg.model.entity.Entity;

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
        GameScene gameScene = GameScene.getInstance();
        if (gameScene != null){
            gameScene.looseGame();
        }
    }


    public void equipArtifact(Artifact newArtifact){
        if (artifact != null)
            hpComponent.addMaxHp(-artifact.getHp());
        if (newArtifact != null)
            hpComponent.addMaxHp(newArtifact.getHp());
        artifact = newArtifact;
    }

    public Artifact getArtifact(){ return artifact;}

    public int nextLevelNeededExp(){ return level * 1000 + (int)Math.pow(level - 1, 2) * 450; }
    public void gainExperience(int expGained){
        experience += expGained;
        if (experience > nextLevelNeededExp())
            LevelUp();
    }
    private void LevelUp(){
        experience -= nextLevelNeededExp();
        level += 1;
    }

    public static class HeroBuilder extends EntityBuilder{
        private HeroClass heroClass;
        private Artifact artifact = null;


        public HeroBuilder setArtifact(Artifact artifact) {
            this.artifact = artifact;
            return this;
        }

        public HeroBuilder setHeroClass(HeroClass heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        @Override
        public Hero build() {
            return new Hero(name, heroClass, level, attack, defense, maxHp, artifact);
        }
    }
}
