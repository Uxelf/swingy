package my.rpg.controller.inputReader;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class InputReader {

    private final Map<String, MyActionListener> bindings;
    private static InputType inputType = InputType.Text;
    private final Scanner terminalScanner;
    ValidatorFactory factory;
    Validator validator;
    private static JFrame frame;
    private static JPanel panel;

    private InputReader(Map<String, MyActionListener> bindings){
        this.bindings = bindings;
        terminalScanner = new Scanner(System.in);
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        if (frame == null){
            frame = new JFrame("Swingui");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
            frame.add(panel);

            if (inputType == InputType.GUI)
                frame.setVisible(true);
        }
    }

    public static void setInputType(InputType inputType){
        InputReader.inputType = inputType;
    }

    public void readInput(){
        try {
            switch (inputType){
                case Text ->readTerminalInput();
                case GUI -> readGUIInput();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void readTerminalInput(){
        CommandInput cmd;
        while (true){
            if (!terminalScanner.hasNextLine()){
                System.exit(0);
                return;
            }
            else {
                cmd = new CommandInput(terminalScanner.nextLine().toLowerCase());
            }

            Set<ConstraintViolation<CommandInput>> violations = validator.validate(cmd);


            if (!violations.isEmpty()) {
                for (ConstraintViolation<CommandInput> violation : violations) {
                    System.out.println("Input error: " + violation.getMessage());
                }
                continue;
            }

            if (bindings.containsKey(cmd.getCommand())){
                bindings.get(cmd.getCommand()).act();
                break;
            }
            else {
                System.out.println("Unrecognized command");
            }
        }
    }

    private void readGUIInput() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            panel.removeAll();
            for (Map.Entry<String, MyActionListener> entry : bindings.entrySet()) {
                if (entry.getKey().length() == 1 && !entry.getKey().matches("\\d"))
                    continue;
                JButton button = new JButton(entry.getKey());
                button.addActionListener(_ -> {
                    entry.getValue().act();
                    latch.countDown();
                });
                panel.add(button);
            }

            panel.revalidate();
            panel.repaint();
        });

        latch.await();
    }

    public static class InputReaderBuilder{
        private final Map<String, MyActionListener> bindings = new LinkedHashMap<>();

        public InputReaderBuilder bind(String command, MyActionListener action){
            bindings.put(command.toLowerCase(), action);
            return this;
        }

        public InputReader build(){
            return new InputReader(bindings);
        }
    }
}
