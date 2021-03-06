package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;

import java.io.IOException;

public class MildlyPopular extends AbstractState {
    public static final StateName stateName= StateName.MILDLY_POPULAR;

    public MildlyPopular(ContextI ctx) {
        super(ctx);
    }

    public void recalculatePopularity() throws IOException {
        context.setCurrentState(HighlyPopular.stateName);
        //context.setCurrentState(stateName);
        Results results = context.getResults();
        results.writeToFile(stateName.toString()+"__VIDEO_ADDED::"+context.getVideo());
        context.getCurrentState().recalculatePopularity();
    }

    //ad request approval

}
