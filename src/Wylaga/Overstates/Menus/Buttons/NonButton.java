package Wylaga.Overstates.Menus.Buttons;

public class NonButton extends Button
{
    private static NonButton instance = new NonButton();

    public NonButton()
    {
        super(null, null, null, null);
    }

    public static NonButton getInstance() {return instance;}
}
