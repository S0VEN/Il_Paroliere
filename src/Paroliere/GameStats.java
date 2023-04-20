package Paroliere;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GameStats extends JFrame {
    public static int score = 0;
    public static TimerPanel timerPanel;
    public static ScorePanel scorePanel;
    public static WordListPanel wordListPanel;

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

    public class TimerPanel extends JPanel {
        private JLabel timerLabel;
        private Timer timer;
        public int count;

        public TimerPanel(int diff, GameTable gTable) {
            setPreferredSize(new Dimension(250, 100));
            setLayout(new FlowLayout(FlowLayout.CENTER));

            if (diff == 1) count = 90;
            if (diff == 2) count = 60;
            if (diff == 3) count = 30;

            timerLabel = new JLabel("00:00");

            timerLabel.setOpaque(true);
            timerLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
            timerLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 5),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));


            timer = new Timer(1000, e -> {
                if (count == 0) {
                    dispose();
                    gTable.dispose();
                    GameEnd gEnd = new GameEnd(score, gTable.getWords(), diff, gTable.getSiz());
                    score = 0;
                    timer.stop();
                } else {
                    count--;
                    int minutes = count / 60;
                    int seconds = count % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerLabel.setText(timeString);
                    if (count <= 10) {
                        timerLabel.setForeground(Color.RED);
                    }else timerLabel.setForeground(Color.BLACK);
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
                    BorderFactory.createLineBorder(Color.BLACK, 3), "Score",
                    TitledBorder.CENTER, TitledBorder.TOP,
                    new Font(Font.SANS_SERIF, Font.BOLD, 25), Color.BLACK));
        }


        public void updateScore(int score) {

            scoreLabel.setText(Integer.toString(score));
            if (score < 500) {
                scoreLabel.setForeground(Color.GREEN);
            } else if (score < 1000) {
                scoreLabel.setForeground(Color.BLUE);
            } else if (score < 1500) {
                scoreLabel.setForeground(new Color(148, 0, 211)); // Viola
            } else {
                scoreLabel.setForeground(Color.ORANGE);
            }}
    }
    public class WordListPanel extends JPanel {
        private static JList<String> wordList;
        private static JScrollPane scrollPane;
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
                    BorderFactory.createLineBorder(Color.BLACK, 3), " Words found [0] ",
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
            scrollPane.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 5), " Words found ["+ words.size() +"] ",
                    TitledBorder.CENTER, TitledBorder.TOP,
                    new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.BLACK));
            scrollPane.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    scrollPane.getBorder()));
            wordList.setListData(words.toArray(new String[0]));
            score = score + checkScore(word);
            scorePanel.updateScore(score);
            timerPanel.count = timerPanel.count + (word.length() * 3);
        }

        public static void wrongWord(String word, int m){
            int t = timerPanel.count - (word.length() * m);
            if (t < 0) t = 0;
            timerPanel.count = t;
        }
    }

}