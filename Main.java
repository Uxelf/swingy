package my.rpg;

import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.controller.scene.SceneManager;
import my.rpg.model.hero.Hero;
import my.rpg.model.hero.HeroClass;
import my.rpg.model.hero.HeroClassesCreator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        Hero hero = HeroClassesCreator.CreateHero(HeroClass.Warrior, "Sam");
//        GameScene gameScene = new GameScene(hero);
//        SceneManager sceneManager = new SceneManager(gameScene);
        SceneManager sceneManager = new SceneManager(new MainMenuScene());
        sceneManager.start();
    }
}