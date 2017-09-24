package Wylaga.Overstates.Menus;

import Wylaga.Overstates.Displayables.ImageFactory;
import Wylaga.Overstates.Displayables.StaticDisplayable;

import java.awt.*;

public class MenuFactory
{
    public static Menu makeStartMenu()
    {
        Menu startMenu = new Menu();
        startMenu.addDisplay(new StaticDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1024, 768)));
        return startMenu;
    }
}
