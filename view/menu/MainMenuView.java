package my.rpg.view.menu;

import my.rpg.view.TemplateView;

public class MainMenuView extends TemplateView {

    @Override
    public void display() {
        System.out.println("""
                 ▄▀▀▀▀▄  ▄▀▀▄    ▄▀▀▄  ▄▀▀█▀▄    ▄▀▀▄ ▀▄  ▄▀▀▀▀▄   ▄▀▀▄ ▀▀▄\s
                █ █   ▐ █   █    ▐  █ █   █  █  █  █ █ █ █        █   ▀▄ ▄▀\s
                   ▀▄   ▐  █        █ ▐   █  ▐  ▐  █  ▀█ █    ▀▄▄ ▐     █  \s
                ▀▄   █    █   ▄    █      █       █   █  █     █ █      █  \s
                 █▀▀▀      ▀▄▀ ▀▄ ▄▀   ▄▀▀▀▀▀▄  ▄▀   █   ▐▀▄▄▄▄▀ ▐    ▄▀   \s
                 ▐               ▀    █       █ █    ▐   ▐            █    \s
                                      ▐       ▐ ▐                     ▐    \s
 
                """);
        printBold("Main menu");
        printOption("New Game");
        printOption("Load Character");
        printOption("Exit");
    }
}
