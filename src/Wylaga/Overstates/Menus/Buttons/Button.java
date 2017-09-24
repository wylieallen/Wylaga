package Wylaga.Overstates.Menus.Buttons;

import Wylaga.Overstates.Menus.Buttons.Functions.AbstractFunction;
import Wylaga.Overstates.Displayables.Displayable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Button extends Displayable
{
    private BufferedImage baseImage;
    private BufferedImage hoverImage;
    private BufferedImage pressImage;
    private ArrayList<AbstractFunction> operations;

    public Button(Point position, BufferedImage baseImage, BufferedImage hoverImage, BufferedImage pressImage)
    {
        super(position, baseImage);
        this.baseImage = baseImage;
        this.hoverImage = hoverImage;
        this.pressImage = pressImage;
        operations = new ArrayList<>();
    }

    public void select() {super.setImage(hoverImage);}
    public void deselect() {super.setImage(baseImage);}

    public void press()
    {
        super.setImage(pressImage);
        for(AbstractFunction operation : operations)
        {
            operation.execute();
        }
    }

    public void addOperation(AbstractFunction operation)
    {
        operations.add(operation);
    }

    public void update() {}
}
