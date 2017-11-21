package Wylaga.Overstates.Menus;

import Wylaga.Util.AbstractFunction;
import Wylaga.Util.KeyRole;

public class TrivialMenu extends Menu
{
    private AbstractFunction function;

    public TrivialMenu(AbstractFunction function)
    {
        this.function = function;
    }

    public void parseKeyRelease(KeyRole keyRole) {}

    public void parseKeyPress(KeyRole keyRole)
    {
        function.execute();
    }
}
