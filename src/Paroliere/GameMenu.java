package Paroliere;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameMenu extends JFrame {
    public int diff, siz;
    public GameMenu() {
        setTitle("Il paroliere");
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("IL PAROLIERE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titlePanel.setMaximumSize(new Dimension(700,250));
        titlePanel.add(titleLabel);

        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setMaximumSize(new Dimension(450, 175));
        difficultyPanel.setBackground(Color.WHITE);
        difficultyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5), "Difficulty",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.SANS_SERIF, Font.BOLD, 30), Color.BLACK));


        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton easyRadioButton = new JRadioButton("Easy");
        easyRadioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        easyRadioButton.setPreferredSize(new Dimension(100, 25));
        easyRadioButton.setBackground(Color.WHITE);

        JRadioButton mediumRadioButton = new JRadioButton("Normal");
        mediumRadioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        mediumRadioButton.setPreferredSize(new Dimension(100, 25));
        mediumRadioButton.setBackground(Color.WHITE);


        JRadioButton hardRadioButton = new JRadioButton("Hard");
        hardRadioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        hardRadioButton.setPreferredSize(new Dimension(100, 25));
        hardRadioButton.setBackground(Color.WHITE);

        difficultyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        difficultyGroup.add(easyRadioButton);
        difficultyGroup.add(mediumRadioButton);
        difficultyGroup.add(hardRadioButton);
        difficultyGroup.setSelected(mediumRadioButton.getModel(), true);

        JPanel difficultyRadioButtonPanel = new JPanel(new GridLayout(1, 3));
        difficultyRadioButtonPanel.setBackground(Color.WHITE);
        difficultyRadioButtonPanel.add(easyRadioButton);
        difficultyRadioButtonPanel.add(mediumRadioButton);
        difficultyRadioButtonPanel.add(hardRadioButton);
        difficultyPanel.add(difficultyRadioButtonPanel);
        JPanel emptyDifficultyPanel = new JPanel();
        emptyDifficultyPanel.setBackground(Color.WHITE);
        difficultyPanel.add(emptyDifficultyPanel);

        JPanel sizePanel = new JPanel();
        sizePanel.setMaximumSize(new Dimension(450, 175));
        sizePanel.setBackground(Color.WHITE);
        sizePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 5), "Size",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font(Font.SANS_SERIF, Font.BOLD, 30), Color.BLACK));

        ButtonGroup sizeGroup = new ButtonGroup();

        JRadioButton smallRadioButton = new JRadioButton("5x5");
        smallRadioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        smallRadioButton.setPreferredSize(new Dimension(100, 25));
        smallRadioButton.setBackground(Color.WHITE);

        JRadioButton mediumSizeRadioButton = new JRadioButton("10x10");
        mediumSizeRadioButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        mediumSizeRadioButton.setPreferredSize(new Dimension(100, 25));
        mediumSizeRadioButton.setBackground(Color.WHITE);

        sizeGroup.add(smallRadioButton);
        sizeGroup.add(mediumSizeRadioButton);
        sizeGroup.setSelected(mediumSizeRadioButton.getModel(), true);
        JPanel sizeRadioButtonPanel = new JPanel(new GridLayout(1, 3));
        sizeRadioButtonPanel.setBackground(Color.WHITE);
        sizeRadioButtonPanel.add(smallRadioButton);
        sizeRadioButtonPanel.add(mediumSizeRadioButton);
        sizePanel.add(sizeRadioButtonPanel);
        JPanel emptySizePanel = new JPanel();
        emptySizePanel.setBackground(Color.WHITE);
        sizePanel.add(emptySizePanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton startButton = new JButton("Start");
        JButton statsButton = new JButton("Stats");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        statsButton.setBackground(Color.BLACK);
        statsButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(200, 55));
        statsButton.setPreferredSize(new Dimension(200, 55));
        buttonPanel.add(startButton);
        buttonPanel.add(statsButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (easyRadioButton.isSelected()) {
                    diff = 1;
                } else if (mediumRadioButton.isSelected()) {
                    diff = 2;
                } else if (hardRadioButton.isSelected()) {
                    diff = 3;
                }

                if (smallRadioButton.isSelected()) {
                    siz = 5;
                } else if (mediumSizeRadioButton.isSelected()) {
                    siz = 10;
                }
                GameTable gTable = new GameTable(siz, diff);
                dispose();
            }
        });

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPane.add(titlePanel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 50)));
        contentPane.add(difficultyPanel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPane.add(sizePanel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 30)));
        contentPane.add(buttonPanel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 50)));

        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}