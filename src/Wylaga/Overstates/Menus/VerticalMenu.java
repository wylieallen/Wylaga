package Wylaga.Overstates.Menus;

import Wylaga.Util.KeyRole;
import Wylaga.WylagaApp;

import java.awt.Point;
import java.awt.geom.Point2D;

public class VerticalMenu extends Menu
{
    public static final Point2D.Double[] buttonPoints = new Point2D.Double[]
            {
                    new Point2D.Double(WylagaApp.WIDTH / 2 - 200, 128),
                    new Point2D.Double(WylagaApp.WIDTH / 2 - 200, 256),
                    new Point2D.Double(WylagaApp.WIDTH / 2 - 200, 384),
                    new Point2D.Double(WylagaApp.WIDTH / 2 - 200, 512),
                    new Point2D.Double(WylagaApp.WIDTH / 2 - 200, 640)
            };

    private int buttonIndex = -1;

    public void parseKeyPress(KeyRole keyRole)
    {
        switch(keyRole)
        {
            case UP:
                buttonIndex--;
                if(buttonIndex < 0)
                {
                    buttonIndex = super.buttons.size() - 1;
                }
                super.setActiveButton(super.buttons.get(buttonIndex));
                break;

            case DOWN:
                buttonIndex++;
                if(buttonIndex > super.buttons.size() - 1)
                {
                    buttonIndex = 0;
                }
                super.setActiveButton(super.buttons.get(buttonIndex));
                break;

            //case LEFT:
            //    break;

            //case RIGHT:
            //    break;

            case ACTION:
            case SELECT:
                super.activeButton.press();
                break;

            //case ACTION:
            //    super.activeButton.press();
            //    break;

            //case PAUSE:
            //    break;

            //case SPECIAL:
            //    break;
        }
    }

    public void parseKeyRelease(KeyRole keyRole)
    {
        /*
        switch(keyRole)
        {
            case UP:
                break;

            case DOWN:
                break;

            case LEFT:
                break;

            case RIGHT:
                break;

            case SELECT:
                break;

            case ACTION:
                break;

            case PAUSE:
                break;

            case SPECIAL:
                break;
        }
        */
    }


}
