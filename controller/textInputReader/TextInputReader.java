package my.rpg.controller.textInputReader;

import java.util.Scanner;

public class TextInputReader {

    public static String readTerminalInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
