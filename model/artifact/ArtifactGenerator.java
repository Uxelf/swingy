package my.rpg.model.artifact;

public class ArtifactGenerator {
    public static Artifact generate(int level){
        int rngArtifact = (int)(Math.random() * 3);
        Artifact artifact;
        int rngStat = (int)((Math.random() + 1) * (level + 1) * 1.2f);
        switch (rngArtifact){
            case 0 -> artifact = new Armor.ArtifactBuilder().setModifier(level + rngStat).build();
            case 1 -> artifact = new Weapon.ArtifactBuilder().setModifier(level + rngStat).build();
            case 2 -> artifact = new Helm.ArtifactBuilder().setModifier((int)(level * 1.5) + rngStat).build();
            default -> artifact = new Armor.ArtifactBuilder().setModifier(level).build();
        }

        return artifact;
    }
}
