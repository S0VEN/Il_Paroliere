package Paroliere;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel layout = new JPanel();
        JPanel tabella = new JPanel(new GridLayout(10, 10, 10, 10));

        frame.setSize(700, 900);

        char[][] grid = new char[10][10];
        Random r = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = (char) (r.nextInt(26) + 'a');
                grid[i][j] = c;
                JButton button = new JButton(String.valueOf(c));
                button.setPreferredSize(new Dimension(50, 50));
                tabella.add(button);
                button.setEnabled(false);
            }
        }

        /*for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)        visualizza tabella
                System.out.print(grid[i][j] + " ");*/

        layout.add(tabella);
        JPanel in = new JPanel();
        JTextField input = new JTextField();
        input.setColumns(20);
        input.setPreferredSize(new Dimension(200, 24));
        in.setBorder(new EmptyBorder(50, 50, 50, 50));
        in.add(input);
        layout.add(in);

        JButton invio = new JButton(" Vai ");
        invio.setPreferredSize(new Dimension(60, 20));
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = input.getText();

                if (isThere(grid, in)) System.out.println("True");
                else System.out.println("false");

                input.setText("");

            }
        });
        layout.add(invio);

        //input.getText() prendere il valore dell'input

        frame.add(layout);


        //Double.parseDouble(input.getText()) get text from textfield
        frame.setVisible(true);
    }

    public static boolean isThere(char[][] grid, String word) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (checkMatrix(grid, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkMatrix(char[][] grid, String word, int i, int j, int x) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        if (grid[i][j] == word.charAt(x)) {
            char tmp = grid[i][j];
            grid[i][j] = '#';
            if (x == word.length() - 1) {
                return true;
            } else if (checkMatrix(grid, word, i - 1, j, x + 1)
                    || checkMatrix(grid, word, i + 1, j, x + 1)
                    || checkMatrix(grid, word, i, j - 1, x + 1)
                    || checkMatrix(grid, word, i, j + 1, x + 1)) {
                return true;
            }
            grid[i][j] = tmp;
        }
        return false;
    }
}

