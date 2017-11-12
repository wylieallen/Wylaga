package Wylaga.Overstates;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Displayables.Displayable;

import java.util.ArrayList;

public abstract class Overstate
{
    private ArrayList<ArrayList<? extends Displayable>> displayables;
    private ArrayList<Displayable> underlays;
    private ArrayList<Displayable> overlays;

    public Overstate()
    {
        displayables = new ArrayList<>();
        underlays = new ArrayList<>();
        overlays = new ArrayList<>();
    }

    public ArrayList<Displayable> getUnderlays() {return underlays;}
    public ArrayList<Displayable> getOverlays() {return overlays;}
    public ArrayList<ArrayList<? extends Displayable>> getDisplayables() {return displayables;}

    public void addOverlay(Displayable displayable) {overlays.add(displayable);}
    public void addUnderlay(Displayable displayable) {underlays.add(displayable);}

    public void update()
    {
        updateDisplayables(underlays);

        for(ArrayList<? extends Displayable> list : displayables)
        {
            updateDisplayables(list);
        }

        updateDisplayables(overlays);
    }

    private void updateDisplayables(ArrayList<? extends Displayable> list)
    {
        for(Displayable displayable : list)
        {
            displayable.update();
        }
    }

    public void addDisplayList(ArrayList<? extends Displayable> list)
    {
        displayables.add(list);
    }

    public abstract void parseKeyPress(KeyRole role);
    public abstract void parseKeyRelease(KeyRole role);
}
