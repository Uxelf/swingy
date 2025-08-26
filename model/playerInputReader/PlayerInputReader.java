package my.rpg.model.playerInputReader;

import java.util.Scanner;

public class PlayerInputReader {

    private Scanner terminalScanner;
    private InputType inputType;

    private ActionListener leftAction;
    private ActionListener rightAction;
    private ActionListener upAction;
    private ActionListener downAction;
    private ActionListener changeGUIAction;

    public PlayerInputReader(InputType inputType){
        this.inputType = inputType;
        terminalScanner = new Scanner(System.in);
    }

    public void setAction(ActionType actionType, ActionListener actionListener){
        switch (actionType){
            case Left -> {leftAction = actionListener;}
            case Right -> {rightAction = actionListener;}
            case Up -> {upAction = actionListener;}
            case Down -> {downAction = actionListener;}
            case Special -> {changeGUIAction = actionListener;}
        }
    }

    public void readInput(){
        switch (inputType){
            case Text -> {readTerminalInput();}
            case GUI -> {readGUIInput();}
        }
    }

    private void readTerminalInput(){
        String input = "";
        ActionType actionType;
        while (true) {
            input = terminalScanner.nextLine();
            actionType = parseTextToActionType(input);

            switch (actionType){
                case Left -> {if (leftAction != null) {
                    leftAction.act();
                    return;
                }}
                case Right -> {if (rightAction != null) {
                    rightAction.act();
                    return;
                }}
                case Up -> {if (upAction != null) {
                    upAction.act();
                    return;
                }}
                case Down -> {if (downAction != null) {
                    downAction.act();
                    return;
                }}
                case Special -> {if (changeGUIAction != null)
                    changeGUIAction.act();
                }
                case null -> {System.out.println("Invalid input, try with: " +
                        "w / north, a / west, s / south, d / east, u / toggle GUI");}
            }
        }
    }

    private ActionType parseTextToActionType(String input){
        input = input.toLowerCase();
        return switch (input) {
            case "w", "north" -> ActionType.Up;
            case "a", "west" -> ActionType.Left;
            case "s", "south" -> ActionType.Down;
            case "d", "east" -> ActionType.Right;
            case "u", "toggle gui" -> ActionType.Special;
            default -> null;
        };
    }

    private void readGUIInput(){

    }
}
