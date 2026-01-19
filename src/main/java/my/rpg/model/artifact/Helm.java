package my.rpg.model.artifact;

public class Helm extends Artifact{
    private Helm(int modifier){
        super(modifier);
        artifactType = ArtifactType.Helm;
    }

    @Override
    public int getAttack(){return 0;}

    @Override
    public int getDefense(){return 0;}

    @Override
    public int getHp(){return modifier;}

    @Override
    public void printData() {
        System.out.println("Helmet: +" + modifier + " hp");
    }

    public static class ArtifactBuilder{
        private int modifier;

        public Helm.ArtifactBuilder setModifier(int modifier){
            this.modifier = modifier;
            return this;
        }

        public Artifact build(){
            return new Helm(modifier);
        }
    }
}
