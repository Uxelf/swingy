package my.rpg.view.newGame;

import my.rpg.view.TemplateView;

public class NewGameView extends TemplateView {
    @Override
    public void display() {
        printBold("New game");
        System.out.println("Choose a class");
        printOption("Warrior");
        printOption("Rogue");
        printOption("Tank");

        System.out.println("\n<- back");
    }
}
