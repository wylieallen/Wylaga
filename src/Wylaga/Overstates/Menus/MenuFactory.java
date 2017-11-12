package Wylaga.Overstates.Menus;

import Wylaga.InterfacePanel;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Displayables.NonUpdatingDisplayable;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Menus.Buttons.ButtonFactory;

import java.awt.Point;

public class MenuFactory
{
    public static Menu makeStartMenu(InterfacePanel panel)
    {
        Menu startMenu = new VerticalMenu();
        startMenu.addDisplay(new NonUpdatingDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        Button gameStartButton = ButtonFactory.makeStartButton(VerticalMenu.buttonPoints[1], panel::startGame);
        startMenu.addButton(gameStartButton);
        Button exitButton = ButtonFactory.makeExitButton(VerticalMenu.buttonPoints[2]);
        startMenu.addButton(exitButton);
        return startMenu;
    }
}
