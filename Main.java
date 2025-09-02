package my.rpg;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.inputReader.InputType;
import my.rpg.controller.scene.scenes.game.GameScene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.controller.scene.SceneManager;
import my.rpg.model.hero.Hero;
import my.rpg.model.hero.HeroClass;
import my.rpg.model.hero.HeroClassesCreator;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        if (args.length != 1){
            System.out.println("Wrong number of arguments!");
            System.out.println("Please introduce only an input option: console / gui");
            return;
        }

        if (args[0].equals("console"))
            InputReader.setInputType(InputType.Text);
        else if (args[0].equals("gui"))
            InputReader.setInputType(InputType.GUI);
        else{
            System.out.println("Wrong input option! Use \"console\" or \"gui\"");
        }

        SceneManager sceneManager = new SceneManager(new MainMenuScene());
        sceneManager.start();
        System.exit(0);
    }
}