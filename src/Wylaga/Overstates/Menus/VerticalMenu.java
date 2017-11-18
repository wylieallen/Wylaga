package Wylaga.Overstates.Menus;

import Wylaga.Util.KeyRole;

import java.awt.Point;

public class VerticalMenu extends Menu
{
    public static final Point[] buttonPoints = new Point[]
            {
                    new Point(440, 128),
                    new Point(440, 256),
                    new Point(440, 384),
                    new Point(440, 512)
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
