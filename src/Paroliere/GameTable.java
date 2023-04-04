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
    public  static int siz = 0;
    ArrayList<String> words = new ArrayList<String>();
    public GameTable(final int size, final int diff){
        super("GameTable");
        siz = size;
        GameStats gStats = new GameStats(diff,this);
        setBackground(Color.WHITE);
        JPanel layout = new JPanel();
        Border bordo = BorderFactory.createEmptyBorder(40,0,0,0);
        JPanel g = new JPanel(new GridLayout(1, 1,0,0 ));
        JPanel table = new JPanel(new GridLayout(size, size, 1, 1));
        table.setBackground(Color.WHITE);
        g.setBackground(Color.WHITE);

        layout.setBackground(Color.WHITE);

        if(size == 5){
            setSize(450, 475);
            setLocationRelativeTo(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Point framePosition = new Point(screenSize.width -1250, screenSize.height -730);
            setLocation(framePosition);
        }
        else if (size == 10){
            setSize(700, 725);
            setLocationRelativeTo(null);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Point framePosition = new Point(screenSize.width -1400, screenSize.height -840);
            setLocation(framePosition);
        }

        getRootPane().setBorder(bordo);
        setResizable(false);

        char[][] grid = new char[size][size];
        Random r = new Random();

        UIManager.put("Button.disabledText", Color.WHITE);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = (char) (r.nextInt(26) + 'a');
                grid[i][j] = c;
                JButton button = new JButton(String.valueOf(c));
                button.setPreferredSize(new Dimension(50, 50));
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                button.setBackground(Color.BLACK);
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
        input.setPreferredSize(new Dimension(200, 40));
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3),
                BorderFactory.createEmptyBorder(0, 10, 3, 0)
        ));
        input.setBackground(Color.WHITE);
        input.setForeground(Color.BLACK);
        Font font = input.getFont().deriveFont(15f);
        input.setFont(font);
        in.setBackground(Color.WHITE);
        in.add(input);
        layout.add(in);

        JButton send = new JButton("Send");
        send.setPreferredSize(new Dimension(100, 40));
        send.setBackground(Color.BLACK);
        send.setForeground(Color.WHITE);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = input.getText();
                if (isThere(grid, in)) {
                    if(isCorrect("src/Paroliere/Words.txt",in)){
                        if(!words.contains(in)){
                            words.add(in);
                            GameStats.WordListPanel.addWord(in);
                        }
                    }
                }
                input.setText("");
            }
        });

        layout.add(send);
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
int getWords(){
        return words.size();
}
    int getSiz(){
        return siz;
    }

}