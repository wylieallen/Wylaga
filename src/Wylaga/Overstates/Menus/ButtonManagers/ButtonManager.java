package Wylaga.Overstates.Menus.ButtonManagers;

import Wylaga.Control.KeyRole;
import Wylaga.Overstates.Menus.Buttons.Button;
import Wylaga.Overstates.Menus.Buttons.NonButton;
import Wylaga.Util.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ButtonManager
{
    private static HashMap<KeyRole, InputResponse> pressMap = makePressMap();
    private static HashMap<KeyRole, InputResponse> releaseMap = makeReleaseMap();

    private static NullResponse nullResponse = new NullResponse();
    private static SelectResponse selectResponse = new SelectResponse();

    private static HashMap<KeyRole, InputResponse> makePressMap()
    {
        HashMap<KeyRole, InputResponse> map = new HashMap<KeyRole, InputResponse>();
        map.put(KeyRole.SELECT, selectResponse);
        map.put(KeyRole.ACTION, selectResponse);
        map.put(KeyRole.LEFT, new DirectionResponse(Direction.LEFT));
        map.put(KeyRole.RIGHT, new DirectionResponse(Direction.RIGHT));
        map.put(KeyRole.UP, new DirectionResponse(Direction.UP));
        map.put(KeyRole.DOWN, new DirectionResponse(Direction.DOWN));
        map.put(KeyRole.PAUSE, new PauseResponse());
        return map;
    }

    private static HashMap<KeyRole, InputResponse> makeReleaseMap()
    {
        HashMap<KeyRole, InputResponse> map = new HashMap<KeyRole, InputResponse>();

        return map;
    }

    private ArrayList<Button> buttons;
    private Button activeButton = NonButton.getInstance();

    public ButtonManager()
    {
        buttons = new ArrayList<>();
    }

    public void addButton(Button button)
    {
        buttons.add(button);
    }

    public ArrayList<Button> getButtons() {return buttons;}

    public void parseKeyPress(KeyRole role)
    {
        pressMap.getOrDefault(role, nullResponse).execute(this);
    }

    public void parseKeyRelease(KeyRole role)
    {
        releaseMap.getOrDefault(role, nullResponse).execute(this);
    }

    protected void pressActiveButton()
    {
        activeButton.press();
    }

    protected abstract void processDirection(Direction direction);

    protected abstract void processPause();

    private interface InputResponse
    {
        public void execute(ButtonManager buttonManager);
    }

    private static class NullResponse implements InputResponse
    {
        public void execute(ButtonManager buttonManager) {}
    }

    private static class SelectResponse implements InputResponse
    {
        public void execute(ButtonManager buttonManager)
        {
            buttonManager.pressActiveButton();
        }
    }

    private static class DirectionResponse implements InputResponse
    {
        private Direction direction;

        public DirectionResponse(Direction direction)
        {
            this.direction = direction;
        }

        public void execute(ButtonManager buttonManager)
        {
            buttonManager.processDirection(direction);
        }
    }

    private static class PauseResponse implements InputResponse
    {
        public void execute(ButtonManager buttonManager)
        {
            buttonManager.processPause();
        }
    }


}
