package my.rpg.view.newGame;

import my.rpg.view.GameView;

public class NewGameView extends GameView {
    @Override
    public void display() {
        printBold("New game");
        System.out.println("Choose a class");
    }
}
