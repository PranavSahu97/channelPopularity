package channelpopularity.driver;

import channelpopularity.context.Channel;
import channelpopularity.context.ContextI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.Results;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		String input = args[0];
		String output = args[1];

		List<StateName> stateNameList=new ArrayList<>();
		stateNameList.add(StateName.UNPOPULAR);
		stateNameList.add(StateName.MILDLY_POPULAR);
		stateNameList.add(StateName.HIGHLY_POPULAR);
		stateNameList.add(StateName.ULTRA_POPULAR);

		//helper class
		Results result= new Results(output);
		SimpleStateFactory simpleStateFactory= new SimpleStateFactory(StateName.UNPOPULAR);
		ContextI contextI = new Channel(simpleStateFactory, stateNameList, result, input, output);

	}
}
