package my.rpg.controller.scene.scenes.loadCharacter;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.scene.Scene;
import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.model.hero.Hero;
import my.rpg.model.hero.SaveHero;
import my.rpg.view.loadCharacter.LoadCharacterView;

import java.util.List;

public class LoadCharacterScene extends Scene {

    List<Hero> loadedHeroes;

    public LoadCharacterScene(){
        loadedHeroes = SaveHero.getSavedHeroes();
        view = new LoadCharacterView(loadedHeroes);

        InputReader.InputReaderBuilder builder = new InputReader.InputReaderBuilder()
                .bind("back", () -> sceneManager.changeScene(new MainMenuScene()))
                .bind("b", () -> sceneManager.changeScene(new MainMenuScene()));

        for (int i = 0; i < loadedHeroes.size(); i++){
            int finalI = i;
            builder.bind(String.valueOf(finalI), () -> loadCharacter(finalI));
        }
        inputReader = builder.build();

    }

    @Override
    public void update() {
        view.render();
        inputReader.readInput();
    }

    private void loadCharacter(int index){
        sceneManager.changeScene(new GameScene(loadedHeroes.get(index)));
    }
}
