package Wylaga.Rendering;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Overstate;

import java.awt.*;
import java.util.Collection;

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

        for(Collection<? extends Displayable> displayables : overstate.getDisplayables())
        {
            drawDisplayables(displayables);
        }

        drawDisplayables(overstate.getOverlays());
    }

    private void drawDisplayables(Collection<? extends Displayable> set)
    {
        for(Displayable displayable : set)
        {
            displayable.draw(g2d);
        }
    }
}
