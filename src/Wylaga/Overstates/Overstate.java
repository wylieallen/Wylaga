package Wylaga.Overstates;

import Wylaga.Util.KeyRole;
import Wylaga.Overstates.Displayables.Displayable;

import java.util.*;

public abstract class Overstate
{
    private Set<Collection<? extends Displayable>> displayables;
    private Set<Displayable> underlays;
    private Set<Displayable> overlays;

    public Overstate()
    {
        displayables = new HashSet<>();
        underlays = new HashSet<>();
        overlays = new HashSet<>();
    }

    public Set<Displayable> getUnderlays() {return underlays;}
    public Set<Displayable> getOverlays() {return overlays;}
    public Set<Collection<? extends Displayable>> getDisplayables() {return displayables;}

    public void addOverlay(Displayable displayable) {overlays.add(displayable);}
    public void addUnderlay(Displayable displayable) {underlays.add(displayable);}

    public void updateView()
    {
        updateDisplayables(underlays);

        for(Collection<? extends Displayable> set : displayables)
        {
            updateDisplayables(set);
        }

        updateDisplayables(overlays);
    }

    private void updateDisplayables(Collection<? extends Displayable> set)
    {
        for(Displayable displayable : set)
        {
            displayable.update();
        }
    }

    public void addDisplays(Collection<? extends Displayable> set)
    {
        displayables.add(set);
    }

    public abstract void updateModel();

    public abstract void parseKeyPress(KeyRole role);
    public abstract void parseKeyRelease(KeyRole role);
}
