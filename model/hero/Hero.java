package my.rpg.model.hero;

import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.model.artifact.Artifact;
import my.rpg.model.entity.Entity;

public class Hero extends Entity {

    private final HeroClass heroClass;
    private int experience;
    private Artifact artifact;
    private final String saveFile;

    private Hero(String name, HeroClass heroClass, int level, int experience, int attack, int defense, int hp, int maxHp, Artifact artifact, String saveFile){
        super(name, level, attack, defense, maxHp);
        if (hp != -1){
            int currentHp = hpComponent.getCurrentHp();
            hpComponent.takeDamage(currentHp - hp);
        }
        this.heroClass = heroClass;
        this.saveFile = saveFile;
        this.experience = experience;
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
        if (artifact != null && newArtifact != null){
            hpComponent.addMaxHp(newArtifact.getHp() - artifact.getHp());
        }
        else if (newArtifact != null)
            hpComponent.addMaxHp(newArtifact.getHp());
        artifact = newArtifact;
    }

    public Artifact getArtifact(){ return artifact;}

    public int nextLevelNeededExp(){ return level * 1000 + (int)Math.pow(level - 1, 2) * 450; }
    public void gainExperience(int expGained){
        experience += expGained;
        if (experience >= nextLevelNeededExp())
            levelUp();
    }
    private void levelUp(){
        experience -= nextLevelNeededExp();
        level += 1;
        attack += 1;
        defense += 1;
        hpComponent.addMaxHp(2);
    }

    public String getSaveFile(){return saveFile;}

    public static class HeroBuilder extends EntityBuilder{
        private HeroClass heroClass;
        private Artifact artifact = null;
        private int experience;
        private String saveFile;
        private int hp = -1;

        public HeroBuilder setArtifact(Artifact artifact) {
            this.artifact = artifact;
            return this;
        }

        public HeroBuilder setHeroClass(HeroClass heroClass) {
            this.heroClass = heroClass;
            return this;
        }

        public HeroBuilder setExperience(int experience){
            this.experience = experience;
            return this;
        }

        public HeroBuilder setHp(int hp){
            this.hp = hp;
            return this;
        }

        public HeroBuilder setSaveFile(String saveFile){
            this.saveFile = saveFile;
            return this;
        }

        @Override
        public Hero build() {
            return new Hero(name, heroClass, level, experience, attack, defense, hp, maxHp, artifact, saveFile);
        }
    }
}
