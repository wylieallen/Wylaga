package Wylaga.Overstates.Menus.Buttons;

import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Menus.Buttons.Functions.ButtonFunction;

import java.awt.*;

public class ButtonFactory
{
    public static Button makeExitButton(Point point) {
        Button button = new Button(point, ImageFactory.makeExitButtonBase(), ImageFactory.makeExitButtonHover(), ImageFactory.makeExitButtonPress());
        button.setFunction(() -> System.exit(0));
        return button;
    }

    public static Button makeStartButton(Point buttonPoint, ButtonFunction startGameFunction) {
        Button button = new Button(buttonPoint, ImageFactory.makeStartButtonBase(), ImageFactory.makeStartButtonHover(), ImageFactory.makeStartButtonPress());
        button.setFunction(startGameFunction);
        return button;
    }
}
