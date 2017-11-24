package Wylaga.Overstates.Displayables.EntityDisplayables;

import Wylaga.Overstates.Displayables.Displayable;

public interface EntityDisplayable extends Displayable {
    boolean expired();
    Displayable getSuccessorDisplayable();
}
