package channelpopularity.state;

import channelpopularity.context.ContextI;
import channelpopularity.util.Results;
import java.io.IOException;

public class Unpopular extends AbstractState {
    public static final StateName stateName= StateName.UNPOPULAR;

    public Unpopular(ContextI ctx) {
        super(ctx);
    }

    // when metrics for same video is updated, recalculate
    public void recalculatePopularity() throws IOException {
        context.setCurrentState(MildlyPopular.stateName);
        //context.setCurrentState(stateName);
        Results results = context.getResults();
        results.writeToFile(stateName.toString()+"__VIDEO_ADDED::"+context.getVideo());
        context.getCurrentState().recalculatePopularity();
    }

    //ad request approval

}
