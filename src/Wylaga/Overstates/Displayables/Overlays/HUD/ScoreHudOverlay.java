package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Game.Game;

import java.awt.*;

public class ScoreHudOverlay extends HudOverlay
{
    private Game game;
    private Graphics2D g2d;
    private int prevScore;

    public ScoreHudOverlay(Point position, Game game)
    {
        super(position);
        this.game = game;
        prevScore = -1;
        g2d = super.getGraphics();
    }

    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "SCORE: " + game.getScore();
        g2d.drawString(string,5, 12);
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