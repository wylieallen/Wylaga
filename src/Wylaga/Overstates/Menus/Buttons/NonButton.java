package Wylaga.Overstates.Menus.Buttons;

import java.awt.image.BufferedImage;

public class NonButton extends Button
{
    private static NonButton instance = new NonButton();

    private NonButton()
    {
        super(null, new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), null, null);
    }

    public static NonButton getInstance() {return instance;}
}
