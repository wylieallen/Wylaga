package Wylaga.Rendering;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Overstate;

import java.awt.*;
import java.util.ArrayList;

public class Renderer
{
    private Graphics2D g2d;

    public Renderer(Graphics2D g2d)
    {
        this.g2d = g2d;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void drawOverstate(Overstate overstate)
    {
        drawDisplayables(overstate.getUnderlays());

        for(ArrayList<? extends Displayable> list : overstate.getDisplayables())
        {
            drawDisplayables(list);
        }

        drawDisplayables(overstate.getOverlays());
    }

    public void drawDisplayables(ArrayList<? extends Displayable> list)
    {
        for(Displayable displayable : list)
        {
            Point position = displayable.getPosition();
            g2d.drawImage(displayable.getImage(), position.x, position.y, null);
        }
    }
}
