package my.rpg.view.heroStats;

import my.rpg.model.artifact.ArtifactType;
import my.rpg.model.hero.Hero;
import my.rpg.view.TemplateView;

public class HeroStatsView extends TemplateView {
    Hero hero;

    public HeroStatsView(Hero hero){
        this.hero = hero;
    }

    @Override
    public void render() {
        System.out.println(hero.getName()
            + ", lvl " + hero.getLevel()
            + " [" + hero.getExperience() + "/" + hero.nextLevelNeededExp() + "]");

        System.out.print("Atk " + hero.getAttack());
        if (hero.getArtifact() != null && hero.getArtifact().getArtifactType() == ArtifactType.Weapon) {
            System.out.print(" (+" + hero.getArtifact().getAttack() + ")");
        }

        System.out.print(" | Def " + hero.getDefense());
        if (hero.getArtifact() != null && hero.getArtifact().getArtifactType() == ArtifactType.Armor) {
            System.out.print(" (+" + hero.getArtifact().getDefense() + ")");
        }

        System.out.print(" | Hp " + hero.getHp() + " / " + hero.getMaxHp());
        if (hero.getArtifact() != null && hero.getArtifact().getArtifactType() == ArtifactType.Helm) {
            System.out.print(" (+" + hero.getArtifact().getHp() + ")");
        }

        System.out.println();
    }
}
