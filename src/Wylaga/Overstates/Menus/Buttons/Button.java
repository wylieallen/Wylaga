package Wylaga.Overstates.Menus.Buttons;

import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Menus.Buttons.Functions.ButtonFunction;
import Wylaga.Overstates.Menus.Buttons.Functions.NullButtonFunction;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Button extends Displayable
{
    private BufferedImage baseImage;
    private BufferedImage hoverImage;
    private BufferedImage pressImage;
    private ButtonFunction function = NullButtonFunction.instance;

    public Button(Point position, BufferedImage baseImage, BufferedImage hoverImage, BufferedImage pressImage)
    {
        super(position, baseImage);
        this.baseImage = baseImage;
        this.hoverImage = hoverImage;
        this.pressImage = pressImage;
    }

    public void select() {super.setImage(hoverImage);}
    public void deselect() {super.setImage(baseImage);}

    public void press()
    {
        super.setImage(pressImage);
        function.execute();
    }

    public void setFunction(ButtonFunction function)
    {
        this.function = function;
    }

    public void update() {}
}
