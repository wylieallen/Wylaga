package Wylaga.Overstates.Menus.Buttons;

public class NonButton extends Button
{
    private static NonButton instance = new NonButton();

    private NonButton()
    {
        super(null, null, null, null);
    }

    public static NonButton getInstance() {return instance;}
}
