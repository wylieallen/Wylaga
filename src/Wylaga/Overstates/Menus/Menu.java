package Wylaga.Overstates.Menus;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Menus.Buttons.NonButton;
import Wylaga.Overstates.Overstate;

import java.util.ArrayList;

public abstract class Menu extends Overstate
{
    protected ArrayList<Button> buttons;
    protected ArrayList<Displayable> displays;

    protected Button activeButton = NonButton.getInstance();

    public Menu()
    {
        super();

        buttons = new ArrayList<>();
        displays = new ArrayList<>();

        super.addDisplayList(displays);
        super.addDisplayList(buttons);
    }

    public void addDisplay(Displayable display) {displays.add(display);}
    public void addButton(Button button) {buttons.add(button);}

    protected void setActiveButton(Button button)
    {
        activeButton.deselect();
        activeButton = button;
        activeButton.select();
    }
}
