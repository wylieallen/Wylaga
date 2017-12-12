package Wylaga.Overstates.Displayables.Overlays.HUD;

import Wylaga.Overstates.Game.Game;

import java.awt.*;
import java.awt.geom.Point2D;

public class ScoreHudOverlay extends HudOverlay
{
    private Game game;
    private int prevScore;

    public ScoreHudOverlay(Point2D.Double position, Game game)
    {
        super(position);
        this.game = game;
        prevScore = -1;
    }

    /*
    public void drawImage()
    {
        g2d.setColor(Color.WHITE);
        String string = "SCORE: " + game.getScore();
        g2d.drawString(string,5, 12);
    }
    */

    protected String makeString()
    {
        return "SCORE: " + game.getScore();
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