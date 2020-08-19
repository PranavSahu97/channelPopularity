package channelpopularity.state.factory;

import channelpopularity.state.StateName;
import java.util.List;

public interface SimpleStateFactoryI {
    public void createState(List<StateName> stateNames);
}
