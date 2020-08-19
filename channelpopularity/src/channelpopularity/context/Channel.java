package channelpopularity.context;

import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.Results;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Channel implements ContextI{
   
    SimpleStateFactory factory;
    private AbstractState curState;
    private Map<StateName, AbstractState> availableStates;
    private ChannelPopularity channelPopularity;
    private String output= null;
    private int popularityScore;
    private int pointer=0;
    private int views;
    private int likes;
    private int dislikes;
    private Results results;


    public Channel(SimpleStateFactory simpleStateFactory, List<StateName> stateNames, Results results, String input, String output) throws IOException {
        // initialize states using factory instance and the provided state names.

        simpleStateFactory.setContextI(this);
        simpleStateFactory.createState(stateNames);
        availableStates=simpleStateFactory.getStates();
        curState=availableStates.get(StateName.UNPOPULAR);
        channelPopularity= new ChannelPopularity(input);
        channelPopularity.popularityCheck();
        this.results=results;
        this.views =  channelPopularity.map1.get(channelPopularity.map1.keySet().toArray()[pointer]).get(0);
        this.likes = channelPopularity.map1.get(channelPopularity.map1.keySet().toArray()[pointer]).get(1);
        this.dislikes = channelPopularity.map1.get(channelPopularity.map1.keySet().toArray()[pointer]).get(2);
        curState.recalculatePopularity();
        }
        // Called by the States based on their logic of what the machine state should change to.
        public void setCurrentState(StateName nextState) throws IOException {

            if(availableStates.containsKey(nextState)) {
                // for safety.
                curState = availableStates.get(nextState);
            }

            int score = curState.getPopularityScore(views, likes, dislikes);
            curState.changeState(score);
            if(pointer == 3){
                pointer = 0;
            }else
                pointer++;
    }

    public Results getResults(){
        return results;
    }

    public String getVideo() {
        return channelPopularity.map1.keySet().toArray()[pointer].toString();

    }
    public AbstractState getCurrentState() {
        return curState;
    }


}
