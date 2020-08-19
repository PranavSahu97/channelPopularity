package channelpopularity.context;

import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;
import java.io.IOException;

public interface ContextI {
   public void setCurrentState(StateName nextState) throws IOException;
   public Results getResults();
   public String getVideo();
   public AbstractState getCurrentState();
}
