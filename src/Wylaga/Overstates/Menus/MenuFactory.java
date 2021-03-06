package Wylaga.Overstates.Menus;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Displayables.Underlays.Starfield;
import Wylaga.Util.ImageFactory;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Menus.Buttons.ButtonFactory;
import Wylaga.Util.AbstractFunction;
import Wylaga.WylagaApp;

import java.awt.geom.Point2D;

public class MenuFactory
{

    public static Menu makeMainMenu(AbstractFunction startGameFunction)
    {
        Menu mainMenu = new VerticalMenu();
        //startMenu.addDisplay(new SimpleDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        mainMenu.addUnderlay(Starfield.getInstance());
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - (696 / 2), 200), ImageFactory.getWylagaLogoImage()));
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50, 650 + 200), ImageFactory.getSmallBasePlayerImage()));
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50 + 75, 650 + 200), ImageFactory.getSmallBasePlayerImage())); // +75
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50 + 25, 600 + 200), ImageFactory.getBasePlayerImage())); // +25
        Button gameStartButton = ButtonFactory.makeStartButton(VerticalMenu.buttonPoints[3], startGameFunction);
        mainMenu.addButton(gameStartButton);
        Button exitButton = ButtonFactory.makeExitButton(VerticalMenu.buttonPoints[4]);
        mainMenu.addButton(exitButton);
        return mainMenu;
    }

    public static Menu makeStartMenu(AbstractFunction initialFunction)
    {
        Menu startMenu = new TrivialMenu(initialFunction);
        startMenu.addUnderlay(Starfield.getInstance());
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - (696 / 2), 200), ImageFactory.getWylagaLogoImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50, 650 + 200), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50 + 75, 650 + 200), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 50 + 25, 600 + 200), ImageFactory.getBasePlayerImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(WylagaApp.WIDTH / 2 - 280, 925), ImageFactory.getPressAnyKeyImage()));
        return startMenu;
    }
}
