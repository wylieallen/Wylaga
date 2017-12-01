package Wylaga.Overstates.Game;

import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayable;
import Wylaga.Overstates.Displayables.Overlays.*;
import Wylaga.Overstates.Displayables.Overlays.HUD.FuelHudOverlay;
import Wylaga.Overstates.Displayables.Overlays.HUD.HealthHudOverlay;
import Wylaga.Overstates.Displayables.Overlays.HUD.ScoreHudOverlay;
import Wylaga.Overstates.Displayables.Underlays.GridVisualizer;
import Wylaga.Overstates.Displayables.Underlays.Starfield;
import Wylaga.Overstates.Overstate;
import Wylaga.Util.AbstractFunction;
import Wylaga.Util.KeyRole;
import Wylaga.Overstates.Game.Control.PlayerController;
import Wylaga.Overstates.Displayables.*;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.EntityDisplayableFactory;
import Wylaga.Overstates.Displayables.EntityDisplayables.EntityDisplayableFactories.PrimitiveEDFactory;
import Wylaga.Overstates.Game.Game;
import Wylaga.Overstates.Game.Entities.Entity;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameState extends Overstate
{
    private GameSubstate activeState;

    private Game game;
    private PlayerController playerController;

    private Set<EntityDisplayable> entityDisplayables;
    private Set<Displayable> explosions;
    private EntityDisplayableFactory entityDisplayableFactory;

    private boolean paused;

    private AbstractFunction postGameFunction;

    public GameState(AbstractFunction postGameFunction)
    {
        entityDisplayableFactory = new PrimitiveEDFactory();
        game = new Game();
        playerController = new PlayerController(game.getPlayerShip());

        paused = false;

        this.postGameFunction = postGameFunction;

        //super.addUnderlay(new SimpleDisplayable(new Point2D.Double(0, 0), ImageFactory.makeBlackRect(1280, 720)));
        super.addUnderlay(Starfield.getInstance());
        //super.addUnderlay(new GridVisualizer(new Point2D.Double(0, 0), game.getGrid()));
        super.addOverlay(new HealthHudOverlay(new Point2D.Double(10, 10), game.getPlayerShip()));
        super.addOverlay(new ScoreHudOverlay(new Point2D.Double(10, 35), game));
        super.addOverlay(new FuelHudOverlay(new Point2D.Double(10, 60), game.getPlayerShip()));

        entityDisplayables = Collections.newSetFromMap(new ConcurrentHashMap<EntityDisplayable, Boolean>());
        super.addDisplays(entityDisplayables);
        addNewEntityDisplayables();

        explosions = Collections.newSetFromMap(new ConcurrentHashMap<Displayable, Boolean>());
        super.addDisplays(explosions);

        activeState = new SandboxState();
        activeState.swapIn();
    }

    public void updateModel()
    {
        playerController.update();
        if(!paused)
            advanceGame();
    }

    public void updateView()
    {
        if(!paused)
        {
            activeState.updateState();
            super.updateView();
        }

        removeExpiredEntityDisplayables();
        addNewEntityDisplayables();
    }

    private void removeExpiredEntityDisplayables()
    {
        // Check entities:

        List<EntityDisplayable> expiredEntityDisplayables = new ArrayList<>();
        List<Displayable> successorDisplayables = new ArrayList<>();

        for(EntityDisplayable entityDisplayable : entityDisplayables)
        {
            if(entityDisplayable.expired())
            {
                System.out.println("EXPIRATION: " + entityDisplayable.toString() + " at " + entityDisplayable.getPosition().toString());
                expiredEntityDisplayables.add(entityDisplayable);
                Displayable successor = entityDisplayable.getSuccessorDisplayable();
                successor.getPosition().setLocation(successor.getPosition().x - (successor.getSize().width / 2), successor.getPosition().y - (successor.getSize().height / 2));
                successorDisplayables.add(successor);
            }
        }

        entityDisplayables.removeAll(expiredEntityDisplayables);

        // Check explosions:

        List<Displayable> expiredExplosions = new ArrayList<>();

        for(Displayable explosion : explosions)
        {
            if(explosion.expired())
            {
                expiredExplosions.add(explosion);
            }
        }

        explosions.removeAll(expiredExplosions);
        explosions.addAll(successorDisplayables);
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
        /*
        if (activeState == pausedGameState)
        {
            changeState(pausedGameState.previousState);
        }
        else
        {
            pausedGameState.previousState = activeState;
            changeState(pausedGameState);
        }
        */
        paused = !paused;
    }

    // Todo: this is basically the model bleeding into the view, exacerbates threading issues
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

    private class SandboxState extends GameSubstate
    {
        protected boolean readyToTransitionState() {return false;}

        protected GameSubstate getNextState() { return new PreWaveSubstate(); }
    }

    private class PreWaveSubstate extends GameSubstate
    {
        private int counter;

        public PreWaveSubstate()
        {
            counter = 0;
            this.addDisplay(new GetReadyDisplay(new Point2D.Double(440, 320))); // prev x: 320, 375
            this.addDisplay(new NextWaveDisplay(new Point2D.Double(490, 200), game));
        }

        protected boolean readyToTransitionState()
        {
            return ++counter >= 160;
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
            this.addDisplay(new WaveCompleteDisplay(new Point2D.Double(440, 320)));
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
        private int counter;

        public GameOverSubstate()
        {
            counter = 0;
            this.addDisplay(new GameOverDisplay(new Point2D.Double(440, 320)));
        }

        protected boolean readyToTransitionState()
        {
            if(++counter >= 300)
                postGameFunction.execute();

            return false;
        }

        protected GameSubstate getNextState()
        {
            return null;
        }
    }
}
