package Paroliere;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel layout = new JPanel();
        JPanel tabella = new JPanel(new GridLayout(10,10,10,10));

        frame.setSize(700,900);

        for (int i = 0;i<100;i++){
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            JButton button = new JButton(String.valueOf(c));
            button.setPreferredSize(new Dimension(50,50));
            tabella.add(button);
            button.setEnabled(false);
        }
        layout.add(tabella);
        JPanel in = new JPanel();
        JTextField input = new JTextField();
        input.setColumns(20);
        input.setPreferredSize( new Dimension( 200, 24 ) );
        in.setBorder(new EmptyBorder(50, 50, 50, 50));
        in.add(input);
        layout.add(in);

        JButton invio = new JButton(" Vai ");
        invio.setPreferredSize(new Dimension(60,20));
        layout.add(invio);

        //input.getText() prendere il valore dell'input

        frame.add(layout);



        //Double.parseDouble(input.getText()) get text from textfield
        frame.setVisible(true);
    }
}