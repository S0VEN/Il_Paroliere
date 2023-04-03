package Paroliere;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameStats extends JFrame {
    ArrayList<String> words = new ArrayList<String>();
    public int score = 0;
    private TimerPanel timerPanel;
    private ScorePanel scorePanel;
    private WordListPanel wordListPanel;

    public GameStats(int diff, GameTable gTable) {
        super("GameStats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        Point framePosition = new Point(screenSize.width - frameWidth - 100, screenSize.height - frameHeight - 200);
        setLocation(framePosition);

        setVisible(true);

    }

    public void setStats(String word){
        words.add(word);
        score = score + checkScore(word);
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
        private int count;

        public TimerPanel(int diff, GameTable gTable) {
            setPreferredSize(new Dimension(200, 70));

            setLayout(new FlowLayout(FlowLayout.CENTER));

            if(diff == 1) count = 120;
            if (diff == 2) count = 75;
            if (diff == 3) count = 1;

            timerLabel = new JLabel("Timer: 00:00");
            timerLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(30, 10, 30, 10),
                    timerLabel.getBorder()));
            timer = new Timer(1000, e -> {
                if (count == 0){
                    dispose();
                    gTable.dispose();
                    GameEnd gEnd = new GameEnd();
                    timer.stop();
                }else {
                    count--;
                    int minutes = count / 60;
                    int seconds = count % 60;
                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerLabel.setText("Timer: " + timeString);
                }
            });
            timer.start();

            add(timerLabel);

            timerLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        }
    }

    private class ScorePanel extends JPanel {
        private JLabel scoreLabel;

        public ScorePanel() {
            setPreferredSize(new Dimension(300, 100));

            setLayout(new FlowLayout(FlowLayout.CENTER));

            scoreLabel = new JLabel("Score: 0");

            add(scoreLabel);

            scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        }
    }

    public class WordListPanel extends JPanel {
        private JList<String> wordList;
        private JScrollPane scrollPane;

        public WordListPanel() {
            setPreferredSize(new Dimension(400, 500));
            setLayout(new BorderLayout());

            String[] words = {"Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit.",
                    "Sed", "quis", "sem", "vitae", "diam", "tempor", "bibendum.", "Pellentesque",
                    "et", "sodales", "felis.", "Nulla", "facilisi.", "Vestibulum", "ante", "ipsum",
                    "primis", "in", "faucibus", "orci", "luctus", "et", "ultrices", "posuere",
                    "cubilia", "Curae;", "Nulla", "accumsan", "felis", "quis", "nibh", "malesuada",
                    "ac", "lobortis", "arcu", "dignissim.", "Cras", "at", "nunc", "vel", "erat",
                    "placerat", "sagittis.", "In", "semper", "tempor", "elit", "ac", "luctus."};

            wordList = new JList<>(words);
            wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            wordList.setAlignmentX(JList.CENTER_ALIGNMENT);
            wordList.setFixedCellHeight(30);
            wordList.setOpaque(false);
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) wordList.getCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);

            scrollPane = new JScrollPane(wordList);
            scrollPane.setPreferredSize(new Dimension(400, 400));

            scrollPane.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Parole Trovate",
                    TitledBorder.CENTER, TitledBorder.TOP,
                    new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.BLACK));

            scrollPane.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(10, 10, 10, 10),
                    scrollPane.getBorder()));

            add(scrollPane);
            wordList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        }
    }
}

