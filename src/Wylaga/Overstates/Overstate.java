package Wylaga.Overstates;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Util.KeyRole;

import java.awt.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Overstate
{
    private Set<Collection<? extends Displayable>> displayables;
    private Set<Displayable> underlays;
    private Set<Displayable> overlays;

    private int displayCount;

    protected Overstate()
    {
        // Underlays are drawn first, followed by displayables, followed by overlays.
        // HashSet doesn't preserve insertion order, so there's no guarantee of draw order within a given layer.
        underlays = Collections.newSetFromMap(new ConcurrentHashMap<Displayable, Boolean>());
        overlays = Collections.newSetFromMap(new ConcurrentHashMap<Displayable, Boolean>());

        // LinkedHashSet is used for displayables to preserve insertion order
        // Concrete Overstates can add collections to displayables to expand layering beyond just underlays and overlays.
        displayables = new LinkedHashSet<>();
    }

    public Set<Displayable> getUnderlays() {return underlays;}
    public Set<Displayable> getOverlays() {return overlays;}
    public Set<Collection<? extends Displayable>> getDisplayables() {return displayables;}

    public void addOverlay(Displayable displayable) {overlays.add(displayable);}
    public void addUnderlay(Displayable displayable) {underlays.add(displayable);}

    public void updateView()
    {
        displayCount = 0;

        updateDisplayables(underlays);

        for(Collection<? extends Displayable> set : displayables)
        {
            updateDisplayables(set);
        }

        updateDisplayables(overlays);

        //System.out.println(displayCount + " displays updated this frame");
    }

    public void updateModel() {}

    private void updateDisplayables(Collection<? extends Displayable> set)
    {
        //Set<Displayable> expireds = new HashSet<>();

        for(Displayable displayable : set)
        {
            //System.out.println("updating " + displayable.toString());
            displayable.update();
            ++displayCount;
            //if(displayable.expired())
            //{
                //expireds.add(displayable);
            //}
        }

        //set.removeAll(expireds);
    }

    public void addDisplays(Collection<? extends Displayable> set)
    {
        displayables.add(set);
    }

    public abstract void parseKeyPress(KeyRole role);
    public abstract void parseKeyRelease(KeyRole role);

    public void draw(Graphics2D g2d)
    {

        underlays.forEach(displayable -> displayable.draw(g2d));

        for(Collection<? extends Displayable> displayableSet : displayables)
        {
            for(Displayable displayable : displayableSet)
            {
                displayable.draw(g2d);
            }
        }


        for(Displayable overlay : overlays)
        {
            overlay.draw(g2d);
        }

    }
}
