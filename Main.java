package my.rpg;

import my.rpg.controller.playerInputReader.ActionType;
import my.rpg.controller.playerInputReader.InputType;
import my.rpg.controller.playerInputReader.PlayerInputReader;

import java.util.NoSuchElementException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PlayerInputReader inputReader = new PlayerInputReader(InputType.Text);

        inputReader.setAction(ActionType.Up, Main::Up);
        inputReader.setAction(ActionType.Down, Main::Down);
        inputReader.setAction(ActionType.Left, Main::Left);
        inputReader.setAction(ActionType.Right, Main::Right);

        try{
            while (true) {
                inputReader.readInput();
            }
        }
        catch (Exception e){
            if (e instanceof NoSuchElementException){
                System.out.println("Closing game");
            }
            else{
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void Up(){
        System.out.println("UP");
    }

    public static void Down(){
        System.out.println("DOWN");
    }

    public static void Left(){
        System.out.println("LEFT");
    }

    public static void Right(){
        System.out.println("RIGHT");
    }

}