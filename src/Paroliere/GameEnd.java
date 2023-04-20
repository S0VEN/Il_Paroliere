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

        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Paroliere/icona.jpeg"));


        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setResizable(false);
        setTitle("Game End");
        LocalDate oggi = LocalDate.now();
        String dataStringa = oggi.toString();

        MySQLConnection o = new MySQLConnection();

        if(o.Connection()==null){
            JOptionPane.showMessageDialog(null, "Looks like the attempt to connect to the database has failed. \n Your match will not be recorded.", "Database Error", JOptionPane.ERROR_MESSAGE);

        }else{

            o.Add(score,nWordsi,diff,size,dataStringa);
        }


        setSize(700, 450);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        timeUpLabel = new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<s><i>Oh no! Time's up!</i><s></html>");
        timeUpLabel.setFont(new Font("tahoma", Font.BOLD, 50));
        timeUpLabel.setForeground(Color.black);
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
                new Font("tahoma", Font.BOLD, 40), Color.BLACK));
        scoreLabel.setPreferredSize(scorePanel.getPreferredSize());

        if(score == 0){
            scoreLabel.setForeground(Color.BLACK);
        } else if (score < 500) {
            scoreLabel.setForeground(Color.GREEN);
        } else if (score < 1000) {
            scoreLabel.setForeground(Color.BLUE);
        } else if (score < 1500) {
            scoreLabel.setForeground(new Color(148, 0, 211)); // Viola
        } else {
            scoreLabel.setForeground(Color.ORANGE); 
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        menuButton = new JButton("Menu");
        exitButton = new JButton("Exit");
        menuButton.setFont(new Font("Tahoma",Font.BOLD + Font.ITALIC,23));
        exitButton.setFont(new Font("Tahoma",Font.BOLD+ Font.ITALIC,23));
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