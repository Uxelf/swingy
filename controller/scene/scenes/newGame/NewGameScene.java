package my.rpg.controller.scene.scenes.newGame;

import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.controller.scene.Scene;
import my.rpg.controller.textInputReader.TextInputReader;
import my.rpg.view.newGame.NewGameView;

public class NewGameScene extends Scene {

    public NewGameScene(){
        view = new NewGameView();
    }

    @Override
    public void update() {
        view.display();
        String input = TextInputReader.readTerminalInput();
        manageInput(input);
    }

    private void manageInput(String input){

        switch (input){
            case "back", "b" -> {
                sceneManager.changeScene(new MainMenuScene());
            }
            default -> {
                System.out.println("Command [" + input + "] not recognized");
            }
        }

    }
}
