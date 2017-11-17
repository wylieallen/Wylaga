package Wylaga.Overstates;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;

import java.util.ArrayList;
import java.util.List;

public abstract class Overstate
{
    private List<List<? extends Displayable>> displayables;
    private List<Displayable> underlays;
    private List<Displayable> overlays;

    public Overstate()
    {
        displayables = new ArrayList<>();
        underlays = new ArrayList<>();
        overlays = new ArrayList<>();
    }

    public List<Displayable> getUnderlays() {return underlays;}
    public List<Displayable> getOverlays() {return overlays;}
    public List<List<? extends Displayable>> getDisplayables() {return displayables;}

    public void addOverlay(Displayable displayable) {overlays.add(displayable);}
    public void addUnderlay(Displayable displayable) {underlays.add(displayable);}

    public void update()
    {
        updateDisplayables(underlays);

        for(List<? extends Displayable> list : displayables)
        {
            updateDisplayables(list);
        }

        updateDisplayables(overlays);
    }

    private void updateDisplayables(List<? extends Displayable> list)
    {
        for(Displayable displayable : list)
        {
            displayable.update();
        }
    }

    public void addDisplayList(List<? extends Displayable> list)
    {
        displayables.add(list);
    }

    public abstract void parseKeyPress(KeyRole role);
    public abstract void parseKeyRelease(KeyRole role);
}
