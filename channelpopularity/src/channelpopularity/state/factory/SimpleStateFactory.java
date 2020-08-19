package channelpopularity.state.factory;
import channelpopularity.context.ContextI;
import channelpopularity.state.Unpopular;
import channelpopularity.state.MildlyPopular;
import channelpopularity.state.HighlyPopular;
import channelpopularity.state.UltraPopular;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleStateFactory implements SimpleStateFactoryI {
    StateName stateName;
    ContextI contextI;
    private Map<StateName, AbstractState> availableStates= new HashMap<>();

    //Return an object of state statename.
    public SimpleStateFactory(StateName stateName){
        this.stateName=stateName;
    }

    public void setContextI(ContextI contextI) {
        this.contextI = contextI;
    }

   public void createState(List<StateName> stateNames) {
        AbstractState abstractState = null;
        for(StateName stateName: stateNames){
            switch (stateName) {
                case UNPOPULAR:
                    abstractState = new Unpopular(contextI);
                    break;
                case MILDLY_POPULAR:
                    abstractState = new MildlyPopular(contextI);
                    break;
                case HIGHLY_POPULAR:
                    abstractState = new HighlyPopular(contextI);
                    break;
                case ULTRA_POPULAR:
                    abstractState = new UltraPopular(contextI);
            }
            availableStates.put(stateName, abstractState);
        }
    }


    public Map<StateName, AbstractState> getStates(){
        return availableStates;
    }

}
