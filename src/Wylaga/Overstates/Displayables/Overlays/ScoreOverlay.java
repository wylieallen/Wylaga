package Wylaga.Overstates.Displayables.Overlays;

import Wylaga.Overstates.Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScoreOverlay extends Overlay
{
    private static BufferedImage background = makeBackground();

    private Game game;
    private Graphics2D g2d;
    private int prevScore;

    public ScoreOverlay(Point position, Game game)
    {
        super(position, background);
        this.game = game;
        prevScore = -1;
        g2d = super.getGraphics();
    }

    public void drawImage()
    {
        g2d.setColor(Color.BLACK);
        String string = "SCORE: " + game.getScore();
        g2d.drawString(string,5, 12);
    }

    private static BufferedImage makeBackground()
    {
        BufferedImage background = new BufferedImage(100, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D backg2d = background.createGraphics();
        backg2d.setColor(Color.WHITE);
        backg2d.fillRect(0, 0, 100, 20);
        backg2d.setColor(Color.GRAY);
        backg2d.drawRect(0, 0, 99, 19);
        return background;
    }

    protected boolean stateChanged()
    {
        if(game.getScore() != prevScore)
        {
            prevScore = game.getScore();
            return true;
        }
        else
        {
            return false;
        }
    }
}