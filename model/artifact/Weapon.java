package my.rpg.model.artifact;

public class Weapon extends Artifact{

    private Weapon(int modifier){
        super(modifier);
    }

    @Override
    public int getAttack(){return modifier;}

    @Override
    public int getDefense(){return 0;}

    @Override
    public int getHp(){return 0;}

    public static class ArtifactBuilder{
        private int modifier;

        public Weapon.ArtifactBuilder setModifier(int modifier){
            this.modifier = modifier;
            return this;
        }

        public Artifact build(){
            return new Weapon(modifier);
        }
    }
}
