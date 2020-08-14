import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    static final int WINDOW_X = 300;
    static final int WINDOW_Y = 300;
    static final int WINDOW_HEIGHT = 555;
    static final int WINDOW_WIDTH = 505;

    private SettingWindow settingWindow;
    private GameMap gameMap;

    Font font = new Font("arial", Font.PLAIN, 25);

    public GameWindow() {
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Моя игра");

        settingWindow = new SettingWindow(this);

        gameMap = new GameMap(this);

        add(gameMap, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(1,2));
        JButton buttonStart = new JButton("Start game");
        buttonStart.setBackground(Color.YELLOW);
        buttonStart.setFont(font);
        panel.add(buttonStart);
        JButton buttonExit = new JButton("Выйти");
        buttonExit.setBackground(Color.RED);
        buttonExit.setFont(font);
        panel.add(buttonExit);
        add(panel, BorderLayout.SOUTH);

        buttonExit.addActionListener( e -> {
            System.exit(0);
        });

        buttonStart.addActionListener( e -> {
            settingWindow.setVisible(true);
        });


        setVisible(true);
    }

    public void startNewGame (int mode, int fieldSize, int winningLength) {
        gameMap.startNewGame(mode, fieldSize, winningLength);
    }
}



