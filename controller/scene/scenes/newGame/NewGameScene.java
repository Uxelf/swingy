package my.rpg.controller.scene.scenes.newGame;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.controller.scene.Scene;
import my.rpg.model.hero.Hero;
import my.rpg.model.hero.HeroClass;
import my.rpg.model.hero.HeroClassesCreator;
import my.rpg.view.newGame.NewGameView;

public class NewGameScene extends Scene {

    public NewGameScene(){
        view = new NewGameView();
        inputReader = new InputReader.InputReaderBuilder()
                .bind("back", () -> sceneManager.changeScene(new MainMenuScene()))
                .bind("b", () -> sceneManager.changeScene(new MainMenuScene()))
                .bind("warrior", () -> newGame(HeroClass.Warrior))
                .bind("tank", () -> newGame(HeroClass.Tank))
                .bind("rogue", () -> newGame(HeroClass.Rogue))
                .build();
    }

    @Override
    public void update() {
        view.render();
        inputReader.readInput();
    }

    private void newGame(HeroClass classSelected){
        Hero hero = HeroClassesCreator.CreateHero(classSelected, generateName());
        GameScene gameScene = new GameScene(hero);
        sceneManager.changeScene(gameScene);
    }

    private String generateName(){
        String[] names = {"Gustav", "Sam", "Korvo", "Pickle Rick", "Isaac", "Rose", "Grandma"};
        int rng = (int)(Math.random() * names.length);
        return names[rng];
    }
}
