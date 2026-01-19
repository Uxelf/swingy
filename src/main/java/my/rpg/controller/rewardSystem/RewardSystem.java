package my.rpg.controller.rewardSystem;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.model.artifact.Artifact;
import my.rpg.model.artifact.ArtifactGenerator;
import my.rpg.model.hero.Hero;
import my.rpg.model.utils.Utils;

public class RewardSystem {

    public static void rewardPlayer(Hero player, int rewardLevel){

        int expWon = 500 + ((rewardLevel) * 250);
        player.gainExperience(expWon);
        System.out.println("XP: +" + expWon);
        int rng = (int)(Math.random() * 2);
        if (rng == 1){
            offerArtifact(player, rewardLevel);
        }
        else{
            Utils.waitMilliseconds(500);
        }
        System.out.println();
    }

    private static void offerArtifact(Hero player, int rewardLevel){
        System.out.println("You found a new artifact");

        Artifact artifact = ArtifactGenerator.generate(rewardLevel);
        System.out.println("- NEW:");
        artifact.printData();

        System.out.println("- Current:");
        if (player.getArtifact() != null){
            player.getArtifact().printData();
        }
        else
            System.out.println("[None]");

        System.out.println(">> Equip (it will remove your current one)");
        System.out.println(">> Leave");
        InputReader inputReader = new InputReader.InputReaderBuilder()
                .bind("e", () -> player.equipArtifact(artifact))
                .bind("equip", () -> player.equipArtifact(artifact))
                .bind("l", () -> {})
                .bind("leave", () -> {})
                .build();

        inputReader.readInput();
    }
}
