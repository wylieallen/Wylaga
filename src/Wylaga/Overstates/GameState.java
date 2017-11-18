package Wylaga.Overstates;

import Wylaga.Util.KeyRole;
import Wylaga.Overstates.Game.Control.PlayerController;
import Wylaga.Overstates.Displayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.PrimitiveEDFactory;
import Wylaga.Overstates.Displayables.Overlays.FuelOverlay;
import Wylaga.Overstates.Displayables.Overlays.HealthOverlay;
import Wylaga.Overstates.Displayables.Overlays.ScoreOverlay;
import Wylaga.Overstates.Game.Game;
import Wylaga.Rendering.ImageFactory;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameState extends Overstate
{
    private PausedGameSubstate pausedGameState = new PausedGameSubstate();
    private GameSubstate activeState;

    private Game game;
    private PlayerController playerController;

    private Set<EntityDisplayable> entityDisplayables;
    private EntityDisplayableFactory entityDisplayableFactory;

    public GameState()
    {
        entityDisplayableFactory = new PrimitiveEDFactory();
        game = new Game();
        playerController = new PlayerController(game.getPlayerShip());

        super.addUnderlay(new NonUpdatingDisplayable(new Point(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        super.addOverlay(new HealthOverlay(new Point(10, 10), game.getPlayerShip()));
        super.addOverlay(new ScoreOverlay(new Point(10, 40), game));
        super.addOverlay(new FuelOverlay(new Point(10, 70), game.getPlayerShip()));

        entityDisplayables = new HashSet<>();
        super.addDisplays(entityDisplayables);
        addNewEntityDisplayables();

        activeState = new PreWaveSubstate();
        activeState.swapIn();
    }

    public void update()
    {
        playerController.update();
        activeState.updateState();
        super.update();

        removeExpiredEntityDisplayables();
        addNewEntityDisplayables();
    }

    private void removeExpiredEntityDisplayables()
    {
        List<EntityDisplayable> expiredEntityDisplayables = new ArrayList<>();

        for(EntityDisplayable entityDisplayable : entityDisplayables)
        {
            if(entityDisplayable.expired())
            {
                expiredEntityDisplayables.add(entityDisplayable);
            }
        }

        entityDisplayables.removeAll(expiredEntityDisplayables);
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
            changeState(pausedGameState.previousState);
        }
        else
        {
            pausedGameState.previousState = activeState;
            changeState(pausedGameState);
        }
    }

    private void changeState(GameSubstate nextState)
    {
        activeState.swapOut();
        activeState = nextState;
        activeState.swapIn();
    }

    private abstract class GameSubstate
    {
        private List<Displayable> substateDisplays = new ArrayList<>();

        public void addDisplay(Displayable overlay) { substateDisplays.add(overlay); }

        public void updateState()
        {
            advanceGame();

            if(readyToTransitionState())
            {
                changeState(getNextState());
            }
        }

        protected abstract GameSubstate getNextState();
        protected abstract boolean readyToTransitionState();

        public void swapIn() { getOverlays().addAll(substateDisplays); }
        public void swapOut()
        {
            getOverlays().removeAll(substateDisplays);
        }
    }

    private class PreWaveSubstate extends GameSubstate
    {
        private int counter;

        public PreWaveSubstate()
        {
            counter = 0;
            this.addDisplay(new GetReadyDisplay(new Point(440, 320))); // prev x: 320, 375
            this.addDisplay(new NextWaveDisplay(new Point(490, 200), game));
        }

        protected boolean readyToTransitionState()
        {
            return ++counter >= 180;
        }

        protected GameSubstate getNextState()
        {
            return new MidWaveSubstate();
        }
    }

    private class MidWaveSubstate extends GameSubstate
    {
        public MidWaveSubstate()
        {
            game.nextWave();
            // this.addDisplay(...);
        }

        protected boolean readyToTransitionState()
        {
            return (game.waveOver() || !game.getPlayerShip().isAlive());
        }

        protected GameSubstate getNextState()
        {
            if(game.getPlayerShip().isAlive())
                return new PostWaveSubstate();
            else return new GameOverSubstate();
        }
    }

    private class PostWaveSubstate extends GameSubstate
    {
        private int counter;

        public PostWaveSubstate()
        {
            counter = 0;
            this.addDisplay(new WaveCompleteDisplay(new Point(440, 320)));
        }

        protected boolean readyToTransitionState()
        {
            return ++counter >= 90;
        }

        protected GameSubstate getNextState()
        {
            return new PreWaveSubstate();
        }
    }

    private class GameOverSubstate extends GameSubstate
    {
        public GameOverSubstate()
        {
            this.addDisplay(new GameOverDisplay(new Point(440, 320)));
        }

        protected boolean readyToTransitionState()
        {
            return false;
        }

        protected GameSubstate getNextState()
        {
            return null;
        }
    }

    private class PausedGameSubstate extends GameSubstate
    {
        public GameSubstate previousState;

        public PausedGameSubstate()
        {
            // this.addDisplay(...);
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
