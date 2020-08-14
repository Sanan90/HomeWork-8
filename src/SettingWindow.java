import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame {
    private GameWindow gameWindow;

    private static final int WINDOW_X = GameWindow.WINDOW_X;
    private static final int WINDOW_Y = GameWindow.WINDOW_Y;
    private static final int WINDOW_HEIGHT = GameWindow.WINDOW_HEIGHT;
    private static final int WINDOW_WIDTH = GameWindow.WINDOW_WIDTH;

    private  static final int MIN_FIELD_SIZE = 3;
    private  static final int MAX_FIELD_SIZE = 10;

    static final int GAME_MODE_H_VS_H = 1;
    static final int GAME_MODE_H_VS_AI = 0;

    private JRadioButton jrbHumanVsAI;
    private JRadioButton jrbHumanVsHuman;
    private ButtonGroup gameMode;

    private  JSlider jsFieldSize;
    private  JSlider jsWinningLength;

    public SettingWindow (GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBounds(WINDOW_X+50, WINDOW_Y+50, WINDOW_WIDTH-100, WINDOW_HEIGHT-100);
        setTitle("Настройки");

        setLayout(new GridLayout(8,1));

        add(new JLabel("Режим игры"));
        jrbHumanVsAI = new JRadioButton("HumanVsAI");
        jrbHumanVsHuman = new JRadioButton("HumanVsHuman", true);
        add(jrbHumanVsAI);
        add(jrbHumanVsHuman);
        gameMode = new ButtonGroup();
        gameMode.add(jrbHumanVsAI);
        gameMode.add(jrbHumanVsHuman);

        add(new JLabel("Выберите длину"));
        jsFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        jsFieldSize.setMajorTickSpacing(1);
        jsFieldSize.setPaintTicks(true);
        jsFieldSize.setPaintLabels(true);
        add(jsFieldSize);

        add(new JLabel("Количество для победы"));
        jsWinningLength = new JSlider(3, jsFieldSize.getValue(), MIN_FIELD_SIZE);
        jsWinningLength.setMajorTickSpacing(1);
        jsWinningLength.setPaintTicks(true);
        jsWinningLength.setPaintLabels(true);
        add(jsWinningLength);

        jsFieldSize.addChangeListener(e ->
                jsWinningLength.setMaximum(jsFieldSize.getValue())
        );

        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(e -> {
            int mode = 2;
            if (jrbHumanVsAI.isSelected()) {
                mode = GAME_MODE_H_VS_AI;
            } else {
                mode = GAME_MODE_H_VS_H;
            }
            int fieldSize = jsFieldSize.getValue();
            int winningLength = jsWinningLength.getValue();

            Logic.SIZE = fieldSize;
            Logic.DOTS_TO_WIN = winningLength;
            Logic.initMap();
            Logic.isFinished = false;

            gameWindow.startNewGame(mode, fieldSize, winningLength);
            Logic.player=3;
            setVisible(false);


        });

        add(buttonOk, BorderLayout.SOUTH);

        setVisible(false);
    }
}
