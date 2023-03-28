package Paroliere;
import java.awt.event.*;
import javax.swing.*;

public class test extends JFrame {

    private Timer timer;
    private JLabel timerLabel;
    private int counter = 10;

    public test() {
        super("Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        JFrame m = new JFrame();
        // Crea un JLabel per mostrare il tempo rimanente
        timerLabel = new JLabel("Tempo rimanente: " + counter + " secondi");
        getContentPane().add(timerLabel);

        // Crea un Timer che viene eseguito ogni secondo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                timerLabel.setText("Tempo rimanente: " + counter + " secondi");
                if (counter == 0) { // Quando il Timer arriva a 0, ferma il Timer
                    timer.stop();
                }
            }
        });
    }

    public void startTimer(final int size, final int diff,boolean contro) {
        // Avvia il Timer
        // Crea un JLabel per mostrare il tempo rimanente
        timerLabel = new JLabel("Tempo rimanente: " + counter + " secondi");

        // Crea un Timer che viene eseguito ogni secondo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                timerLabel.setText("Tempo rimanente: " + counter + " secondi");
                if (counter == 0) { // Quando il Timer arriva a 0, ferma il Timer
                    Main u = new Main();
                    u.createTable(size,diff,false);
                    timer.stop();
                    dispose();

                }
            }
        });
        timer.start();
        JFrame frame = new JFrame();
        frame.setSize(300,100);
        frame.add(timerLabel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }

    public static void main(String[] args) {
        // Crea un'istanza di TimerFrame e lo visualizza

    }
}