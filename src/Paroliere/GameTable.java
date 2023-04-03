package Paroliere;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class GameTable extends JFrame{
    public static int t = 10;
    public GameTable(final int size, final int diff){
        super("GameTable");

        setBackground(Color.black);
        JPanel layout = new JPanel();
        Border bordo = BorderFactory.createEmptyBorder(40,0,0,0);
        JPanel g = new JPanel(new GridLayout(1, 1,0,0 ));
        JPanel table = new JPanel(new GridLayout(size, size, 1, 1));
        table.setBackground(Color.black);
        g.setBackground(Color.black);
        layout.setBackground(Color.black);

        if(size == 5){
            setSize(450, 475);
        }
        else if (size == 10){
            setSize(700, 725);
        }

        getRootPane().setBorder(bordo);
        setResizable(false);

        ArrayList<String> words = new ArrayList<String>();
        char[][] grid = new char[size][size];
        Random r = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = (char) (r.nextInt(26) + 'a');
                grid[i][j] = c;
                JButton button = new JButton(String.valueOf(c));
                button.setPreferredSize(new Dimension(50, 50));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                table.add(button);
                button.setEnabled(false);
            }
        }

        layout.add(table);
        JPanel in = new JPanel();
        JPanel ris = new JPanel();
        JPanel risultato = new JPanel();
        JTextField input = new JTextField();
        input.setColumns(20);
        in.setBackground(Color.black);
        input.setPreferredSize(new Dimension(200, 24));
        in.setBorder(new EmptyBorder(50, 0, 50, 10));
        in.add(input);
        layout.add(in);

        JButton invio = new JButton(" Vai ");
        invio.setPreferredSize(new Dimension(60, 20));
        invio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = input.getText();
                JLabel truee = new JLabel();
                JLabel falsee = new JLabel();
                if (isThere(grid, in)) {
                    if(isCorrect("src/Paroliere/Words.txt",in)){
                        if(!words.contains(in)){
                            ris.remove(truee);
                            words.add(in);
                            truee.setText("ahahahahaha");
                            ris.add(truee);
                            layout.add(ris);
                            g.add(layout);
                            add(g);
                            setVisible(true);
                        }else System.out.println("C'è già stupido");
                    }
                }
                else {
                    ris.remove(falsee);
                    falsee.setText("Testo Non Trovato !");
                    ris.add(falsee);
                    layout.add(ris);
                    g.add(layout);
                    add(g);
                    setVisible(true);
                }
                input.setText("");
            }
        });

        layout.add(invio);
        g.add(layout);

        add(g);

        setVisible(true);
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
            if (x == word.length() - 1) {
                return true;
            } else if (checkMatrix(grid, word, i - 1, j, x + 1)
                    || checkMatrix(grid, word, i + 1, j, x + 1)
                    || checkMatrix(grid, word, i, j - 1, x + 1)
                    || checkMatrix(grid, word, i, j + 1, x + 1)
                    || checkMatrix(grid, word, i - 1, j - 1, x + 1)
                    || checkMatrix(grid, word, i + 1, j + 1, x + 1)
                    || checkMatrix(grid, word, i + 1, j - 1, x + 1)
                    || checkMatrix(grid, word, i - 1, j + 1, x + 1)){
                return true;
            }
        }
        return false;
    }
    public static boolean isCorrect(String filePath, String word) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(word)) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}

