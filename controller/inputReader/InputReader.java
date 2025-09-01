package my.rpg.controller.inputReader;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class InputReader {

    private final Map<String, MyActionListener> bindings;
    private final InputType inputType;
    private final Scanner terminalScanner;
    private static JFrame frame;
    private static JPanel panel;

    private InputReader(Map<String, MyActionListener> bindings, InputType inputType){
        this.bindings = bindings;
        this.inputType = inputType;
        terminalScanner = new Scanner(System.in);

        if (frame == null){
            frame = new JFrame("Swingui");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1));
            frame.add(panel);

            frame.setVisible(true);
        }
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
        String input;
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

    private void readGUIInput() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            for (Map.Entry<String, MyActionListener> entry : bindings.entrySet()) {
                if (entry.getKey().length() == 1 && !entry.getKey().matches("\\d"))
                    continue;
                JButton button = new JButton(entry.getKey());
                button.addActionListener(e -> {
                    entry.getValue().act();
                    latch.countDown();
                });
                panel.add(button);
            }

            panel.revalidate();
            panel.repaint();
        });

        latch.await();

        SwingUtilities.invokeLater(() -> panel.removeAll());
    }



    public static class InputReaderBuilder{
        private final Map<String, MyActionListener> bindings = new LinkedHashMap<>();
        private InputType inputType = InputType.GUI;

        public InputReaderBuilder bind(String command, MyActionListener action){
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
