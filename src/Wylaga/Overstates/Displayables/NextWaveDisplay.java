package Wylaga.Overstates.Displayables;

import Wylaga.Overstates.Game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NextWaveDisplay extends Displayable
{
    private Game game;
    private Graphics2D g2d;
    private int waveCount;

    private static BufferedImage makeImg()
    {
        BufferedImage img = new BufferedImage(384, 64, BufferedImage.TYPE_INT_ARGB);
        return img;
    }

    public NextWaveDisplay(Point pos, Game game)
    {
        super(pos, makeImg());
        this.game = game;
        g2d = super.getImage().createGraphics();
        g2d.setBackground(new Color(0, 0, 0, 0));
        g2d.setColor(Color.RED);
        Font font = new Font("Courier New", 1, 64);
        g2d.setFont(font);
        waveCount = game.getWaveCount();
        g2d.clearRect(0, 0, 384, 64);
        g2d.drawString("WAVE: " + (waveCount + 1), 0, 64);
    }

    public void update()
    {
        if(game.getWaveCount() != waveCount)
        {
            waveCount = game.getWaveCount();
            g2d.clearRect(0, 0, 384, 64);
            g2d.drawString("WAVE: " + (waveCount + 1), 0, 64);
        }
    }
}
