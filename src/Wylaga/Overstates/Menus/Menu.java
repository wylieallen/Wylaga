package Wylaga.Overstates.Menus;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Overstate;

import java.util.ArrayList;

public class Menu extends Overstate
{
    private ArrayList<Button> buttons;
    private ArrayList<Displayable> displays;

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

    public void parseKeyPress(KeyRole role)
    {

    }

    public void parseKeyRelease(KeyRole role)
    {

    }
}
