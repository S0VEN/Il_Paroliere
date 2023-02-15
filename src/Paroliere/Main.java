package Paroliere;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static int t = 10;
    public static void main(String[] args) {
        final int size = 15;
        createTable(size);
    }

    public static void createTable(final int size){
         final Timer timer;
         final JLabel tim;
        JFrame frame = new JFrame();
        JPanel layout = new JPanel();
        JPanel g = new JPanel(new GridLayout(0, 2,0,0 ));
        JPanel table = new JPanel(new GridLayout(size, size, 1, 1));
        tim = new JLabel(String.valueOf(t), SwingConstants.CENTER);

        frame.setSize(700, 900);

        ArrayList<String> parole = new ArrayList<String>();
        char[][] grid = new char[size][size];
        Random r = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = (char) (r.nextInt(26) + 'a');
                grid[i][j] = c;
                JButton button = new JButton(String.valueOf(c));
                button.setPreferredSize(new Dimension(50, 50));
                table.add(button);
                button.setEnabled(false);
            }
        }

        /*for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)        visualizza tabella
                System.out.print(grid[i][j] + " ");*/

        timer = new Timer(1000, e -> {

            if(t==0){
               tim.setText("0");

            }
            else{
                t--;
                tim.setText(String.valueOf(t));
            }
        });

        timer.start();
        layout.add(table);
        JPanel in = new JPanel();
        JPanel ris = new JPanel();
        JPanel risultato = new JPanel();
        JTextField input = new JTextField();
        input.setColumns(20);
        input.setPreferredSize(new Dimension(200, 24));
        in.setBorder(new EmptyBorder(50, 50, 50, 50));
        in.add(input);
        layout.add(in);


        // Crea un timer che eseguirà un'azione ripetuta ogni 1000 millisecondi


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
                        if(!parole.contains(in)){
                            ris.remove(truee);
                            parole.add(in);
                            truee.setText("Testo Trovato !");
                            ris.add(truee);
                            layout.add(ris);
                            g.add(layout);
                            frame.add(g);

                            frame.setVisible(true);
                        }else System.out.println("C'è già stupido");
                    }
                }
                else {
                    ris.remove(falsee);
                    falsee.setText("Testo Non Trovato !");
                    ris.add(falsee);
                    layout.add(ris);
                    g.add(layout);
                    frame.add(g);
                    frame.setVisible(true);
                }
                input.setText("");
            }
        });
        layout.add(invio);
        g.add(tim);
        g.add(layout);

        frame.add(g);

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

