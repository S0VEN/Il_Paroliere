package Paroliere;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameMenu extends JFrame {
    public int diff, siz;
    public GameMenu() {
        // Imposta il titolo della finestra
        setTitle("Il paroliere");

        // Imposta il colore di sfondo della finestra
        getContentPane().setBackground(Color.WHITE);

        // Crea un JPanel per il titolo
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("IL PAROLIERE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titlePanel.add(titleLabel);

        // Crea un JPanel per i radio button della difficoltà
        JPanel difficultyPanel = new JPanel();
        difficultyPanel.setBackground(Color.WHITE);
        JLabel difficultyLabel = new JLabel("Seleziona la difficoltà");
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 13));
        difficultyPanel.add(difficultyLabel);

        ButtonGroup difficultyGroup = new ButtonGroup();
        JRadioButton easyRadioButton = new JRadioButton("Facile");
        JRadioButton mediumRadioButton = new JRadioButton("Medio");
        JRadioButton hardRadioButton = new JRadioButton("Difficile");
        difficultyGroup.add(easyRadioButton);
        difficultyGroup.add(mediumRadioButton);
        difficultyGroup.add(hardRadioButton);
        difficultyGroup.setSelected(mediumRadioButton.getModel(),true);
        JPanel difficultyRadioButtonPanel = new JPanel(new GridLayout(1, 3));
        difficultyRadioButtonPanel.setBackground(Color.WHITE);
        difficultyRadioButtonPanel.add(easyRadioButton);
        difficultyRadioButtonPanel.add(mediumRadioButton);
        difficultyRadioButtonPanel.add(hardRadioButton);
        difficultyPanel.add(difficultyRadioButtonPanel);
        JPanel emptyDifficultyPanel = new JPanel();
        emptyDifficultyPanel.setBackground(Color.WHITE);
        difficultyPanel.add(emptyDifficultyPanel);

        // Crea un JPanel per i radio button della dimensione
        JPanel sizePanel = new JPanel();
        sizePanel.setBackground(Color.WHITE);
        JLabel sizeLabel = new JLabel("Seleziona la dimensione");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 13));
        sizePanel.add(sizeLabel);

        ButtonGroup sizeGroup = new ButtonGroup();
        JRadioButton smallRadioButton = new JRadioButton("5x5");
        JRadioButton mediumSizeRadioButton = new JRadioButton("10x10");
        JRadioButton largeSizeRadioButton = new JRadioButton("15x15");
        sizeGroup.add(smallRadioButton);
        sizeGroup.add(mediumSizeRadioButton);
        sizeGroup.add(largeSizeRadioButton);
        sizeGroup.setSelected(mediumSizeRadioButton.getModel(),true);
        JPanel sizeRadioButtonPanel = new JPanel(new GridLayout(1, 3));
        sizeRadioButtonPanel.setBackground(Color.WHITE);
        sizeRadioButtonPanel.add(smallRadioButton);
        sizeRadioButtonPanel.add(mediumSizeRadioButton);
        sizeRadioButtonPanel.add(largeSizeRadioButton);
        sizePanel.add(sizeRadioButtonPanel);
        JPanel emptySizePanel = new JPanel();
        emptySizePanel.setBackground(Color.WHITE);
        sizePanel.add(emptySizePanel);

        // Crea un JPanel per i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton startButton = new JButton("Start");
        JButton statsButton = new JButton("Stats");
        buttonPanel.add(startButton);
        buttonPanel.add(statsButton);
        startButton.setPreferredSize(new Dimension(200, 50));
        statsButton.setPreferredSize(new Dimension(200, 50));
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
                } else if (largeSizeRadioButton.isSelected()) {
                   siz = 15;
                }
                Main u = new Main();
                u.createTable(siz,diff);

            }
        });

        // Aggiunge i componenti alla finestra
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

        // Imposta le dimensioni della finestra
        setSize(700, 500);
        setResizable(false);

        // Centra la finestra sullo schermo
        setLocationRelativeTo(null);

        // Imposta l'operazione di chiusura della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // Crea una nuova istanza della finestra del menu del gioco
        GameMenu gameMenu = new GameMenu();
        gameMenu.setVisible(true);



    }
}