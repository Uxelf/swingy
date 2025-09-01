package my.rpg.controller.scene.scenes.mainMenu;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.scene.scenes.loadCharacter.LoadCharacterScene;
import my.rpg.controller.scene.scenes.newGame.NewGameScene;
import my.rpg.controller.scene.Scene;
import my.rpg.view.menu.MainMenuView;

public class MainMenuScene extends Scene {

    public MainMenuScene(){
        view = new MainMenuView();
        inputReader = new InputReader.InputReaderBuilder()
                .bind("new game", this::newGame)
                .bind("n", this::newGame)
                .bind("load", this::loadCharacter)
                .bind("l", this::loadCharacter)
                .bind("exit", this::exit)
                .build();
    }

    @Override
    public void update() {
        view.render();
        inputReader.readInput();
    }

    private void newGame(){
        sceneManager.changeScene(new NewGameScene());
    }

    private void loadCharacter(){ sceneManager.changeScene(new LoadCharacterScene());}

    private void exit(){ sceneManager.stop();}
}
