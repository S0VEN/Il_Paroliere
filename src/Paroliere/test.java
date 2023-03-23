package Paroliere;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {

    public test() {
        super("Finestra combinata");

        // creazione delle componenti della finestra 1
        JButton button1 = new JButton("Bottone 1");
        JTextField textField1 = new JTextField();
        JLabel label1 = new JLabel("Etichetta 1");

        // creazione delle componenti della finestra 2
        JButton button2 = new JButton("Bottone 2");
        JTextField textField2 = new JTextField();
        JLabel label2 = new JLabel("Etichetta 2");

        // creazione del contenitore per le componenti
        JPanel container = new JPanel(new BorderLayout());

        // aggiunta delle componenti al contenitore
        JPanel panel1 = new JPanel();
        panel1.add(button1);
        panel1.add(textField1);
        panel1.add(label1);
        container.add(panel1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.add(button2);
        panel2.add(textField2);
        panel2.add(label2);
        container.add(panel2, BorderLayout.WEST);

        // aggiunta del contenitore alla finestra principale
        add(container);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }
}
