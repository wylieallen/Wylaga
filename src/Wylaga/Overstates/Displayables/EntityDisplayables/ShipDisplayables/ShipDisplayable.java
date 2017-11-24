package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.CompositeEntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;
import java.util.Set;

public class ShipDisplayable extends CompositeEntityDisplayable {

    public ShipDisplayable(Ship ship, Displayable successor, Set<Displayable> components)
    {
        super(ship, successor, components);
    }
}
