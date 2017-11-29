package Wylaga.Overstates.Menus.Buttons;

import Wylaga.Overstates.Displayables.SimpleDisplayable;
import Wylaga.Util.AbstractFunction;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Button extends SimpleDisplayable
{
    private BufferedImage baseImage;
    private BufferedImage hoverImage;
    private BufferedImage pressImage;
    private AbstractFunction function;

    public Button(Point2D.Double position, BufferedImage baseImage, BufferedImage hoverImage, BufferedImage pressImage)
    {
        this(position, baseImage, hoverImage, pressImage, () -> {});
    }

    public Button(Point2D.Double position, BufferedImage baseImage, BufferedImage hoverImage, BufferedImage pressImage, AbstractFunction function)
    {
        super(position, baseImage);
        this.baseImage = baseImage;
        this.hoverImage = hoverImage;
        this.pressImage = pressImage;
        this.function = function;
    }

    public void select() {super.setImage(hoverImage);}
    public void deselect() {super.setImage(baseImage);}

    public void press()
    {
        super.setImage(pressImage);
        function.execute();
    }

    public void setFunction(AbstractFunction function)
    {
        this.function = function;
    }

    public void update() {}
}
