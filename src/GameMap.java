import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMap extends JPanel {
    private GameWindow gameWindow;
    private int mode;
    private int fieldSize;
    private int winningLength;

    private boolean isInIt;

    public int cellWidth;
    public int cellHeight;

    public GameMap(GameWindow gameWindow)  {
        this.gameWindow = gameWindow;

        setBackground(Color.cyan);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                click(e);
            }
        });
    }

    private void click(MouseEvent e) {
        int cellX = e.getX()/cellWidth;
        int cellY = e.getY()/cellHeight;

        if (mode  == 1) {
            if (!Logic.isFinished2) {
                Logic.humanTurn2(cellX, cellY);
            }
        }
        else if (mode == 0) {
            if (!Logic.isFinished) {
                Logic.humanTurn(cellX, cellY);
            }
        }
        repaint();
    }

    public void startNewGame (int mode, int fieldSize, int winningLength) {
        this.mode = mode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;

        isInIt = true;

        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInIt) {
            return;
        }
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight/fieldSize;
        cellWidth = panelWidth/fieldSize;

        g.setColor(Color.BLACK);

        for (int i=0; i<fieldSize; i++ ) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i=0; i<fieldSize; i++) {
            int x = i* cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (Logic.map[i] [j] == Logic.DOT_X) {
                    drawX(g, j, i);
                }
                if (Logic.map[i] [j] == Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }
    }

    private void drawX(Graphics g ,int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.YELLOW);
        g.drawLine(cellWidth * x+30, cellHeight * y+30, cellWidth * (x+1)-30, cellHeight * (y+1)-30);
        g.drawLine(cellWidth * x+30, cellHeight * (y+1)-30, cellWidth * (x+1)-30, cellHeight * y+30);
    }
    private void drawO(Graphics g ,int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawOval(cellWidth * x+20, cellHeight * y+20, cellWidth-40, cellHeight-40);
    }
}
