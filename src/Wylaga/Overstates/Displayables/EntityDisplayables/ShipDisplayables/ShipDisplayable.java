package Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.CompositeEntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.ChassisDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.EngineDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.SpecialDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.ShipDisplayables.Components.WeaponDisplayable;
import Wylaga.Overstates.Game.Entities.Ships.Ship;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipChassis;
import Wylaga.Overstates.Game.Entities.Ships.ShipComponents.ShipWeapon;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShipDisplayable extends CompositeEntityDisplayable {

    private Ship ship;
    private WeaponDisplayable weapon;

    public ShipDisplayable(Ship ship, Displayable successor, ChassisDisplayable chassis, EngineDisplayable engine, WeaponDisplayable weapon, SpecialDisplayable special)
    {
        super(ship, successor, makeSet(chassis, engine, weapon, special));
        this.ship = ship;
        this.weapon = weapon;
    }

    public ShipDisplayable(Ship ship, Displayable successor, ChassisDisplayable chassis, EngineDisplayable engine, WeaponDisplayable weapon)
    {
        super(ship, successor, makeSet(chassis, engine, weapon));
        this.ship = ship;
        this.weapon = weapon;
    }

    private static Set<Displayable> makeSet(Displayable... displayables)
    {
        Set<Displayable> set = new HashSet<>();
        set.addAll(Arrays.asList(displayables));
        return set;
    }

    public void update()
    {
        ShipWeapon modelWeapon = ship.getWeapon();
        ShipWeapon viewWeapon = weapon.getWeapon();
        if(modelWeapon != viewWeapon)
        {
            super.remove(weapon);
            super.add(weapon = modelWeapon.getDisplayable(EntityDisplayableFactory.getInstance()));
        }
        super.update();
    }


}
