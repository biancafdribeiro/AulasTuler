//Bianca Fonseca Dantas Ribeiro - CB3025683

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private String currentInput = "";
    private double num1, num2, result;
    private String operator;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setBounds(100, 100, 250, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        textField = new JTextField();
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        textField.setColumns(10);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.setLayout(new GridLayout(5, 4, 0, 0)); 

        addButton(panel, "7");
        addButton(panel, "8");
        addButton(panel, "9");
        addButton(panel, "/");

        addButton(panel, "4");
        addButton(panel, "5");
        addButton(panel, "6");
        addButton(panel, "*");

        addButton(panel, "1");
        addButton(panel, "2");
        addButton(panel, "3");
        addButton(panel, "-");

        addButton(panel, "0");
        addButton(panel, ".");
        addButton(panel, "=");
        addButton(panel, "+");

        addButton(panel, "C");
    }

    private void addButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    handleButtonClick(text);
                } catch (Exception ex) {
                    textField.setText("Error");
                } finally {
                }
            }
        });
        panel.add(button);
    }

    private void handleButtonClick(String text) {
        if (text.equals("C")) {
            currentInput = "";
            textField.setText("");
        } else if (text.equals("=")) {
            try {
                num2 = Double.parseDouble(currentInput);
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Não é possível dividir por zero");
                        }
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                currentInput = "";
            } catch (Exception e) {
                textField.setText("Error");
            }
        } else if (text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")) {
            if (!currentInput.isEmpty()) {
                num1 = Double.parseDouble(currentInput);
                operator = text;
                currentInput = "";
            }
        } else {
            currentInput += text;
            textField.setText(currentInput);
        }
    }
}
