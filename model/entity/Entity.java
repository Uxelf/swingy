package my.rpg.model.entity;

import my.rpg.model.artifact.Artifact;
import my.rpg.model.hero.HeroClass;
import my.rpg.model.hpComponent.HpComponent;
import my.rpg.model.movementComponent.MovementComponent;

public abstract class Entity {
    protected String name;
    protected int level;
    protected int attack;
    protected int defense;
    protected HpComponent hpComponent;
    protected MovementComponent movementComponent;

    protected Entity(String name, int level, int attack, int defense, int maxHp){
        this.name = name;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        hpComponent = new HpComponent(maxHp);
        hpComponent.addDeathListener(this::onDeath);
    }

    public String getName() { return name;}
    public int getLevel() { return level;}
    public int getHp() {return hpComponent.getCurrentHp();}

    public abstract int getAttack();
    public abstract int getDefense();

    public void takeDamage(int damage){
        int reducedDamage = Math.max(damage - getDefense(), 0);
        hpComponent.takeDamage(reducedDamage);
    }

    public MovementComponent getMovementComponent(){ return movementComponent;}

    protected abstract void onDeath();
}
