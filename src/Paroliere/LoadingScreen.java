package Paroliere;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
public class LoadingScreen extends JWindow {

    private JLabel loadingLabel;
    private Timer timer;
    private int progress=0;
    private JProgressBar progressBar;

    public LoadingScreen() {
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));

        ImageIcon loadingIcon = new ImageIcon(getClass().getResource("icona.jpeg"));
        loadingLabel = new JLabel(loadingIcon);
        contentPane.add(loadingLabel, BorderLayout.CENTER);

        // Aggiungi la barra di caricamento
         progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.PLAIN, 14));
        progressBar.setString("Loading...");
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(300, 30));

        progressBar.setOpaque(false);
        progressBar.setBackground(new Color(0, 0, 0, 0));
        progressBar.setForeground(Color.black);
        JPanel progressPanel = new JPanel(new FlowLayout());
        progressPanel.add(progressBar);
        contentPane.add(progressPanel, BorderLayout.SOUTH);

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;
                progressBar.setValue(progress);
                if (progress >= 130) {
                    timer.stop();
                    dispose();
                }
            }
        });
        timer.start();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
