package my.rpg.controller.inputReader;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommandInput {

    @NotNull(message = "Command can't be null")
    @NotBlank(message = "Command can't be empty")
    private String command;

    public CommandInput(String input){
        command = input;
    }

    public String getCommand(){ return command;}
}
