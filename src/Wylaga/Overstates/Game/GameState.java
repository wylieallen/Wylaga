package Wylaga.Overstates.Game;

import Wylaga.Control.KeyRole;
import Wylaga.Control.PlayerController;
import Wylaga.Overstates.Displayables.Displayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.PrimitiveEDFactory;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Displayables.NonUpdatingDisplayable;
import Wylaga.Overstates.Game.Entities.Entity;
import Wylaga.Overstates.Overstate;

import java.awt.Point;
import java.util.ArrayList;

public class GameState extends Overstate
{
    private PreGameSubstate preGameState = new PreGameSubstate();
    private PausedGameSubstate pausedGameState = new PausedGameSubstate();
    private GameSubstate activeState;

    private Game game;
    private PlayerController playerController;

    private ArrayList<EntityDisplayable> entityDisplayables;
    private EntityDisplayableFactory entityDisplayableFactory;

    public GameState()
    {
        activeState = preGameState;
        activeState.swapIn();

        entityDisplayableFactory = new PrimitiveEDFactory();
        game = new Game();
        playerController = new PlayerController(game.getPlayerShip());

        super.getUnderlays().add(new NonUpdatingDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));

        entityDisplayables = new ArrayList<>();
        super.addDisplayList(entityDisplayables);
        addNewEntityDisplayables();
    }

    public void update()
    {
        playerController.update();
        activeState.updateState();
        super.update();

        ArrayList<EntityDisplayable> expiredEntityDisplayables = new ArrayList<>();
        for(EntityDisplayable entityDisplayable : entityDisplayables)
        {
            if(entityDisplayable.expired())
            {
                expiredEntityDisplayables.add(entityDisplayable);
            }
        }
        entityDisplayables.removeAll(expiredEntityDisplayables);

        addNewEntityDisplayables();
    }

    private void addNewEntityDisplayables()
    {
        for(Entity entity : game.getNewEntities())
        {
            entityDisplayables.add(entity.getDisplayable(entityDisplayableFactory));
        }
    }

    public void parseKeyPress(KeyRole keyRole)
    {
        switch(keyRole)
        {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
            case SELECT:
            case ACTION:
            case SPECIAL:
                playerController.parseKey(keyRole, true);
                break;

            case PAUSE:
                togglePause();
                break;
        }
    }

    public void parseKeyRelease(KeyRole keyRole)
    {
        switch(keyRole)
        {
            case UP:
            case DOWN:
            case LEFT:
            case RIGHT:
            case SELECT:
            case ACTION:
            case SPECIAL:
                playerController.parseKey(keyRole, false);
                break;
        }
    }

    private void advanceGame()
    {
        game.update();
    }

    private void togglePause()
    {
        if (activeState == pausedGameState)
        {
            activeState = pausedGameState.previousState;
        }
        else
        {
            pausedGameState.previousState = activeState;
            activeState = pausedGameState;
        }
    }

    private void setState(GameSubstate nextState)
    {
        activeState.swapOut();
        activeState = nextState;
        activeState.swapIn();
    }

    private abstract class GameSubstate
    {
        private ArrayList<Displayable> substateOverlays = new ArrayList<>();

        public void addOverlay(Displayable overlay) { substateOverlays.add(overlay); }
        public ArrayList<Displayable> getSubstateOverlays() {return substateOverlays;}

        public void updateState()
        {
            advanceGame();
            if(readyToTransitionState())
            {
                setState(getNextState());
            }
        }

        protected abstract GameSubstate getNextState();
        protected abstract boolean readyToTransitionState();

        public void swapIn()
        {
            getSubstateOverlays().addAll(substateOverlays);
        }
        public void swapOut()
        {
            getSubstateOverlays().removeAll(substateOverlays);
        }
    }

    private class PreGameSubstate extends GameSubstate
    {
        private int counter;

        public PreGameSubstate()
        {
            counter = 0;
            // this.addOverlay(...);
        }

        protected boolean readyToTransitionState()
        {
            return ++counter >= 180;
        }

        protected GameSubstate getNextState()
        {
            return new InGameSubstate();
        }
    }

    private class InGameSubstate extends GameSubstate
    {
        public InGameSubstate()
        {
            game.nextWave();
            // this.addOverlay(...);
        }

        protected boolean readyToTransitionState()
        {
            return game.waveOver();
        }

        protected GameSubstate getNextState()
        {
            return new PostGameSubstate();
        }
    }

    private class PostGameSubstate extends GameSubstate
    {
        private int counter;

        public PostGameSubstate()
        {
            counter = 0;
            // this.addOverlay(...);
        }

        protected boolean readyToTransitionState()
        {
            return ++counter >= 180;
        }

        protected GameSubstate getNextState()
        {
            return new PreGameSubstate();
        }
    }

    private class PausedGameSubstate extends GameSubstate
    {
        public GameSubstate previousState;

        public PausedGameSubstate()
        {
            // this.addOverlay(...);
        }

        public void updateState() {}

        protected boolean readyToTransitionState()
        {
            return false;
        }

        protected GameSubstate getNextState()
        {
            return previousState;
        }
    }


}
