package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import java.io.IOException;

public class UltraPopular extends AbstractState {
    public static final StateName stateName= StateName.ULTRA_POPULAR;

    public UltraPopular(ContextI ctx) {
        super(ctx);
    }

    public void recalculatePopularity() throws IOException {
        //context.setCurrentState(stateName);
        Results results = context.getResults();
        results.writeToFile(stateName.toString()+"__VIDEO_ADDED::"+context.getVideo());
        //context.getCurrentState().recalculatePopularity();
        
    }

    //ad request approval
}
