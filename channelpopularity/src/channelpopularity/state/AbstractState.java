package channelpopularity.state;

import channelpopularity.context.ContextI;
import java.io.IOException;

public abstract class AbstractState implements StateI {
    protected ContextI context;
    public AbstractState(ContextI context) {
        this.context = context;
    }

    public StateName changeState(int popularityScore) {
        if (0 <= popularityScore && popularityScore <= 1000)
            return StateName.UNPOPULAR;

        else if (1001 <= popularityScore && popularityScore <= 10000)
            return StateName.MILDLY_POPULAR;

        else if (10001 <= popularityScore && popularityScore <= 100000)
            return StateName.HIGHLY_POPULAR;

        else if (100001 <= popularityScore && popularityScore <= Integer.MAX_VALUE)
            return StateName.ULTRA_POPULAR;

        else
            return null;
    }

    //calculate popularity
    public int getPopularityScore(int views, int likes, int dislikes){
        return views + 2*(likes - dislikes);
    }
    public abstract void recalculatePopularity() throws IOException;

}
