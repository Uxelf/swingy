package my.rpg.view.loadCharacter;

import my.rpg.model.hero.Hero;
import my.rpg.view.TemplateView;
import my.rpg.view.heroStats.HeroStatsView;

import java.util.List;

public class LoadCharacterView extends TemplateView {

    private final List<Hero> heroes;

    public LoadCharacterView(List<Hero> heroes){
        this.heroes = heroes;
    }

    @Override
    public void render() {
        printBold("Load a character");

        for (int i = 0; i < heroes.size(); i++){
            printOption(String.valueOf(i));
            new HeroStatsView(heroes.get(i)).render();
            System.out.println();
        }

        printOption("back");
    }
}
