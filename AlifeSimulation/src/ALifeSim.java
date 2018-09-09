import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * Driver for the ALifeSim project
 * @author Joel Tibbetts, Shuyan Li
 *
 */
public class ALifeSim {
	// Accepts command-line arguments of the following form:
	// <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>
	// <#/Mutation enabled ("True"/"False")> <#/Random seed (long)>

	public static void main(String[] args) {
		int iterations = Integer.parseInt(args[0]);
		int numCooperators = Integer.parseInt(args[1]);
		int numDefectors = Integer.parseInt(args[2]);
		int numPartialCooperators = Integer.parseInt(args[3]);
		if (args.length < 6) {
			System.out.println("Error: Input format: Accepts input of the following form:\n"
					+ "	 <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>\n"
					+ "	 <#/Mutation enabled (\"True\"/\"False\")> <#/Random seed (long)>");
			System.exit(1);
		}

		String mutationString = args[4];
		long randomSeed = Long.parseLong(args[5]);

		// Initializing the Random object used throughout the simulation
		Random random = new Random(randomSeed);

		boolean mutationSwitch = false;

		if (mutationString.equalsIgnoreCase("true") || mutationString.equalsIgnoreCase("false")) {
			mutationSwitch = Boolean.parseBoolean(args[4]);
		} else {
			System.out.println("Error: Specify either true or false to enable or disable mutations.");
			System.exit(1);
		}

		Map<String, Integer> simulationMap = new HashMap<>();
		simulationMap.put("Cooperators", numCooperators);
		simulationMap.put("Defectors", numDefectors);
		simulationMap.put("PartialCooperators", numPartialCooperators);

		System.out.println("Mutations on? = " + mutationSwitch);

		Population simulationPopulation = new Population(simulationMap, random);

		// Sum of cooperation probabilities calculated for each tick/iteration
		double cooperationProbabilitySum = 0;

		for (int i = 0; i < iterations; i++) {
			simulationPopulation.update(mutationSwitch, random);
			cooperationProbabilitySum += simulationPopulation.calculateCooperationMean();
		}

		// Print the results of a simulation
		System.out.println("After " + iterations + " ticks:");
		System.out.println("Cooperators = " + simulationPopulation.getPopulationCounts().get("Cooperators"));
		System.out.println("Defectors   = " + simulationPopulation.getPopulationCounts().get("Defectors"));
		System.out.println("Partial     = " + simulationPopulation.getPopulationCounts().get("PartialCooperators"));
		System.out.println("\nMean Cooperation Probability = " + (cooperationProbabilitySum / iterations) + "\n");

	}

}
