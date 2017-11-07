package Wylaga.Overstates.Menus.Buttons.Functions;

public class NullButtonFunction implements ButtonFunction
{
    public static final NullButtonFunction instance = new NullButtonFunction();

    private NullButtonFunction() {}

    public void execute() {}
}
