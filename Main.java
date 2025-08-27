package my.rpg;

import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.controller.scene.SceneManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SceneManager sceneManager = new SceneManager(new MainMenuScene());
        sceneManager.start();
    }
}