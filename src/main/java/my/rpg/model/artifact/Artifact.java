package my.rpg.model.artifact;

public abstract class Artifact {

    protected int modifier;
    protected ArtifactType artifactType;

    protected Artifact(int modifier){
        this.modifier = modifier;
    }

    public abstract int getAttack();
    public abstract int getDefense();
    public abstract int getHp();
    public abstract void printData();

    public ArtifactType getArtifactType(){ return artifactType;}
}
