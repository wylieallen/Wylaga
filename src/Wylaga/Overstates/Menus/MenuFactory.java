package Wylaga.Overstates.Menus;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Starfield;
import Wylaga.Overstates.Menus.Buttons.Functions.ButtonFunction;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Menus.Buttons.ButtonFactory;
import Wylaga.Util.AbstractFunction;

import java.awt.Point;
import java.awt.geom.Point2D;

public class MenuFactory
{

    public static Menu makeMainMenu(ButtonFunction startGameFunction)
    {
        Menu mainMenu = new VerticalMenu();
        //startMenu.addDisplay(new SimpleDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        mainMenu.addUnderlay(Starfield.getInstance());
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(590, 650), ImageFactory.getSmallBasePlayerImage()));
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(665, 650), ImageFactory.getSmallBasePlayerImage()));
        mainMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(615, 600), ImageFactory.getBasePlayerImage()));
        Button gameStartButton = ButtonFactory.makeStartButton(VerticalMenu.buttonPoints[1], startGameFunction);
        mainMenu.addButton(gameStartButton);
        Button exitButton = ButtonFactory.makeExitButton(VerticalMenu.buttonPoints[2]);
        mainMenu.addButton(exitButton);
        return mainMenu;
    }

    public static Menu makeStartMenu(AbstractFunction initialFunction)
    {
        Menu startMenu = new TrivialMenu(initialFunction);
        startMenu.addUnderlay(Starfield.getInstance());
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(590, 650), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(665, 650), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new SimpleDisplayable(new Point2D.Double(615, 600), ImageFactory.getBasePlayerImage()));
        return startMenu;
    }
}
