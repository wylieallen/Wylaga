package Wylaga.Overstates.Menus.Buttons;

import Wylaga.Rendering.ImageFactory;
import Wylaga.Util.AbstractFunction;

import java.awt.geom.Point2D;

public class ButtonFactory
{
    public static Button makeExitButton(Point2D.Double point)
    {
        return new Button(point, ImageFactory.makeExitButtonBase(), ImageFactory.makeExitButtonHover(), ImageFactory.makeExitButtonPress(), () -> System.exit(0));
    }

    public static Button makeStartButton(Point2D.Double buttonPoint, AbstractFunction startGameFunction)
    {
        return new Button(buttonPoint, ImageFactory.makeStartButtonBase(), ImageFactory.makeStartButtonHover(), ImageFactory.makeStartButtonPress(), startGameFunction);
    }
}
