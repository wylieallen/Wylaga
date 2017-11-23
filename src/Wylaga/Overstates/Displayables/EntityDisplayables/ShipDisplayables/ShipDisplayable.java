package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.CompositeEntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;

import java.awt.*;

public class ShipDisplayable extends CompositeEntityDisplayable {

    public ShipDisplayable(Point point, Ship ship, Displayable successor, ChassisDisplayable chassisDisplayable, EngineDisplayable engineDisplayable, WeaponDisplayable weaponDisplayable)
    {
        super(point, ship, successor, chassisDisplayable, engineDisplayable, weaponDisplayable);
    }
}
