package Wylaga.Overstates.Menus;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.Underlays.Starfield.Starfield;
import Wylaga.Overstates.Menus.Buttons.Functions.ButtonFunction;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Menus.Buttons.ButtonFactory;
import Wylaga.Util.AbstractFunction;

import java.awt.Point;

public class MenuFactory
{
    public static Menu makeMainMenu(ButtonFunction startGameFunction)
    {
        Menu startMenu = new VerticalMenu();
        //startMenu.addDisplay(new Displayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        startMenu.addUnderlay(Starfield.getInstance());
        Button gameStartButton = ButtonFactory.makeStartButton(VerticalMenu.buttonPoints[1], startGameFunction);
        startMenu.addButton(gameStartButton);
        Button exitButton = ButtonFactory.makeExitButton(VerticalMenu.buttonPoints[2]);
        startMenu.addButton(exitButton);
        return startMenu;
    }

    public static Menu makeStartMenu(AbstractFunction initialFunction)
    {
        Menu startMenu = new TrivialMenu(initialFunction);
        startMenu.addUnderlay(Starfield.getInstance());
        startMenu.addDisplay(new Displayable(new Point(590, 550), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new Displayable(new Point(665, 550), ImageFactory.getSmallBasePlayerImage()));
        startMenu.addDisplay(new Displayable(new Point(615, 500), ImageFactory.getBasePlayerImage()));
        return startMenu;
    }
}
