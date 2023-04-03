package Paroliere;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GameEnd extends JFrame {
    private JLabel timeUpLabel;
    private JLabel scoreLabel;
    private JButton menuButton;
    private JButton exitButton;

    public GameEnd() {
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setResizable(false);
        // Impostazione del titolo della finestra
        setTitle("Game End");

        // Impostazione della dimensione della finestra
        setSize(700, 450);

        // Impostazione del layout della finestra
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Sezione 1: Etichetta "Oh No, time's up!"
        timeUpLabel = new JLabel("Oh No, time's up!");
        timeUpLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timeUpLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(timeUpLabel);
        timeUpLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 10, 30, 10),
                timeUpLabel.getBorder()));

        // Sezione 2: Etichetta "Score: "

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setPreferredSize(new Dimension(450, 150));
        add(scorePanel);

        scoreLabel = new JLabel("1000");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 35));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER); //aggiunto
        scorePanel.add(scoreLabel);

        scoreLabel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5), "Score",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.SANS_SERIF, Font.BOLD, 40), Color.BLACK));

        scoreLabel.setPreferredSize(scorePanel.getPreferredSize());
        // Sezione 3: Pulsanti "Menu" e "Exit"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        menuButton = new JButton("Menu");
        exitButton = new JButton("Exit");
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(menuButton);
        buttonPanel.add(exitButton);
        add(buttonPanel);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 10, 30, 10),
                buttonPanel.getBorder()));

        // Impostazione della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
