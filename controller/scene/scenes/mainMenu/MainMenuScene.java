package my.rpg.controller.scene.scenes.mainMenu;

import my.rpg.controller.scene.scenes.newGame.NewGameScene;
import my.rpg.controller.scene.Scene;
import my.rpg.controller.textInputReader.TextInputReader;
import my.rpg.view.menu.MainMenuView;

public class MainMenuScene extends Scene {

    public MainMenuScene(){
        view = new MainMenuView();
    }

    @Override
    public void update() {
        view.display();
        String input = TextInputReader.readTerminalInput();
        manageInput(input);
    }

    private void manageInput(String input){

        switch (input.toLowerCase()){
            case "new game", "n" -> {
                sceneManager.changeScene(new NewGameScene());
            }
            default -> {
                System.out.println("Command [" + input + "] not recognized");
            }
        }
    }
}
