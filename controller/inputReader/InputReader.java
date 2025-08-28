package my.rpg.controller.inputReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputReader {

    private Map<String, ActionListener> bindings;
    private InputType inputType;
    private Scanner terminalScanner;

    private InputReader(Map<String, ActionListener> bindings, InputType inputType){
        this.bindings = bindings;
        this.inputType = inputType;
        terminalScanner = new Scanner(System.in);
    }

    public void readInput(){
        switch (inputType){
            case Text -> {readTerminalInput();}
            case GUI -> {readGUIInput();}
        }
    }

    private void readTerminalInput(){
        String input = "";
        while (true){
            input = terminalScanner.nextLine().toLowerCase();

            if (bindings.containsKey(input)){
                bindings.get(input).act();
                break;
            }
            else {
                System.out.println("Unrecognized command");
            }
        }
    }

    private void readGUIInput(){
        System.out.println("Reading GUI");
    }



    public static class InputReaderBuilder{
        private final Map<String, ActionListener> bindings = new HashMap<>();
        private InputType inputType = InputType.Text;

        public InputReaderBuilder bind(String command, ActionListener action){
            bindings.put(command.toLowerCase(), action);
            return this;
        }

        public InputReaderBuilder setInputType(InputType inputType){
            this.inputType = inputType;
            return this;
        }

        public InputReader build(){
            return new InputReader(bindings, inputType);
        }
    }
}
