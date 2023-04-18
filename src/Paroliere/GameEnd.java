package Paroliere;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;


public class GameEnd extends JFrame {
    private JLabel timeUpLabel;
    private JLabel scoreLabel;
    private JButton menuButton;
    private JButton exitButton;

    public GameEnd(int score, int nWordsi, int diff, int size) {
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setResizable(false);
        setTitle("Game End");
        LocalDate oggi = LocalDate.now();
        String dataStringa = oggi.toString();


        setSize(700, 450);

        MySQLConnection o = new MySQLConnection();
        o.Add(score,nWordsi,diff,size,dataStringa);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        timeUpLabel = new JLabel("Oh no! Time's up!");
        timeUpLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timeUpLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(timeUpLabel);
        timeUpLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 10, 30, 10),
                timeUpLabel.getBorder()));

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scorePanel.setPreferredSize(new Dimension(450, 150));
        add(scorePanel);

        scoreLabel = new JLabel(Integer.toString(score));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 35));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(scoreLabel);

        scoreLabel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5), "Score",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.SANS_SERIF, Font.BOLD, 40), Color.BLACK));
        scoreLabel.setPreferredSize(scorePanel.getPreferredSize());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        menuButton = new JButton("Menu");
        exitButton = new JButton("Exit");
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(200, 55));
        exitButton.setPreferredSize(new Dimension(200, 55));

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMenu gMenu = new GameMenu();
                gMenu.setVisible(true);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(menuButton);
        buttonPanel.add(exitButton);
        add(buttonPanel);
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(30, 10, 30, 10),
                buttonPanel.getBorder()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}