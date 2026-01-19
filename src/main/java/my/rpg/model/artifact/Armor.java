package my.rpg.model.artifact;

public class Armor extends Artifact{
    private Armor(int modifier){
        super(modifier);
        artifactType = ArtifactType.Armor;
    }

    @Override
    public int getAttack(){return 0;}

    @Override
    public int getDefense(){return modifier;}

    @Override
    public int getHp(){return 0;}

    @Override
    public void printData() {
        System.out.println("Armor: +" + modifier + " def");
    }

    public static class ArtifactBuilder{
        private int modifier;

        public Armor.ArtifactBuilder setModifier(int modifier){
            this.modifier = modifier;
            return this;
        }

        public Artifact build(){
            return new Armor(modifier);
        }
    }
}
