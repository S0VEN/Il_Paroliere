package Paroliere;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameStats extends JFrame {
    public static int score = 0;
    public static TimerPanel timerPanel;
    public static ScorePanel scorePanel;
    public WordListPanel wordListPanel;

    public GameStats(int diff, GameTable gTable) {
        super("GameStats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/Paroliere/icona.jpeg"));

        setResizable(false);

        int score = 0;
        ArrayList<String> words = new ArrayList<String>();

        timerPanel = new TimerPanel(diff, gTable);
        scorePanel = new ScorePanel();
        wordListPanel = new WordListPanel();

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(timerPanel);
        add(scorePanel);
        add(wordListPanel);

        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("List.font", font);

        setSize(575, 600);

        setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        Point framePosition = new Point(screenSize.width - frameWidth - 130, screenSize.height - frameHeight - 200);
        setLocation(framePosition);
        getContentPane().setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        timerPanel.setBackground(Color.WHITE);
        scorePanel.setBackground(Color.WHITE);
        wordListPanel.setBackground(Color.WHITE);
        timerPanel.timerLabel.setBackground(Color.WHITE);
        scorePanel.scoreLabel.setBackground(Color.WHITE);
        wordListPanel.wordList.setBackground(Color.WHITE);

        setVisible(true);

    }
    public static int checkScore(String word) {
        int length = word.length(), m = 10;
        if (length < 3) {
            return length * m;
        } else if (length < 5) {
            return length * m * 2;
        } else if (length < 7) {
            return length * m * 3;
        } else {
            return length * m * 4;
        }
    }

    private class TimerPanel extends JPanel {
        private JLabel timerLabel;
        private Timer timer;
        public int count;

        public TimerPanel(int diff, GameTable gTable) {
            setPreferredSize(new Dimension(250, 100));

            setLayout(new FlowLayout(FlowLayout.CENTER));

            if(diff == 1) count = 120;
            if (diff == 2) count = 75;
            if (diff == 3) count = 30;

            timerLabel = new JLabel("00:00");
            timerLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 5),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            timerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

            timer = new Timer(1000, e -> {
                if (count == 0) {
                    dispose();
                    gTable.dispose();
                    GameEnd gEnd = new GameEnd(score,gTable.getWords(),diff,gTable.getSiz());
                    score = 0;
                    timer.stop();
                } else {
                    count--;
                    int minutes = count / 60;
                    int seconds = count % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerLabel.setText(timeString);
                }
            });
            timer.start();

            add(timerLabel);
        }
    }

    public class ScorePanel extends JPanel {
        private JLabel scoreLabel;

        public ScorePanel() {
            setLayout(new GridLayout(1, 1));
            setMaximumSize(new Dimension(200, 100));


            scoreLabel = new JLabel("0");
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
            scoreLabel.setPreferredSize(new Dimension(150, 100));

            add(scoreLabel);

            scoreLabel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 5), "Score",
                    TitledBorder.CENTER, TitledBorder.TOP,
                    new Font(Font.SANS_SERIF, Font.BOLD, 25), Color.BLACK));
        }


        public void updateScore(int score) {
            scoreLabel.setText(Integer.toString(score));
        }
    }
    public class WordListPanel extends JPanel {
        private static JList<String> wordList;
        private JScrollPane scrollPane;
        private static ArrayList<String> words;

        public WordListPanel() {
            setPreferredSize(new Dimension(400, 500));
            setLayout(new BorderLayout());

            words = new ArrayList<>();

            wordList = new JList<>(words.toArray(new String[0]));
            wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            wordList.setAlignmentX(JList.CENTER_ALIGNMENT);
            wordList.setFixedCellHeight(30);
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) wordList.getCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);

            scrollPane = new JScrollPane(wordList);
            scrollPane.setPreferredSize(new Dimension(400, 400));
            scrollPane.setBackground(Color.WHITE);

            scrollPane.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 5), "Parole Trovate",
                    TitledBorder.CENTER, TitledBorder.TOP,
                    new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.BLACK));

            scrollPane.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    scrollPane.getBorder()));

            add(scrollPane);
            wordList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        }
        public static void addWord(String word) {
            words.add(word);
            wordList.setListData(words.toArray(new String[0]));
            score = score + checkScore(word);
            scorePanel.updateScore(score);
            timerPanel.count = timerPanel.count + (word.length() * 3);
        }
    }

}