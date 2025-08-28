package my.rpg.model.entity;

import my.rpg.model.hpComponent.DeathListener;
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
        movementComponent = new MovementComponent();
    }

    public String getName() { return name;}
    public int getLevel() { return level;}
    public int getHp() {return hpComponent.getCurrentHp();}
    public int getMaxHp() {return hpComponent.getMaxHp();}

    public abstract int getAttack();
    public abstract int getDefense();

    public void takeDamage(int damage){
        int reducedDamage = Math.max(damage - getDefense(), 0);
        hpComponent.takeDamage(reducedDamage);
    }

    protected abstract void onDeath();

    public MovementComponent getMovementComponent(){ return movementComponent;}

    public static abstract class EntityBuilder {
        protected String name;
        protected int level;
        protected int attack;
        protected int defense;
        protected int maxHp;

        public EntityBuilder setAttack(int attack){
            this.attack = attack;
            return this;
        }

        public EntityBuilder setDefense(int defense) {
            this.defense = defense;
            return this;
        }

        public EntityBuilder setLevel(int level) {
            this.level = level;
            return this;
        }

        public EntityBuilder setMaxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public EntityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public abstract Entity build();
    }
}
