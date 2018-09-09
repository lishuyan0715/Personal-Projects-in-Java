import java.util.*;
import java.util.Random;

/**
 * A Population, which is an ArrayList and each element in the list is an
 * Organism
 */
public class Population {
	public ArrayList<Organism> orgArray;

	/**
	 * Constructor for the Population object, initializing a new ArrayList with keys
	 * and values from the input counts
	 * 
	 * @param counts
	 * @param random
	 * @throws IllegalArgumentException
	 */
	public Population(Map<String, Integer> counts, Random random) throws IllegalArgumentException {

		int totalOrgs = counts.get("Cooperators") + counts.get("Defectors") + counts.get("PartialCooperators");
		orgArray = new ArrayList<Organism>(totalOrgs);

		// Fill the new ArrayList with bacteria
		for (int i = 0; i < counts.get("Cooperators"); i++) {
			orgArray.add(new Cooperator());
		}
		for (int i = 0; i < counts.get("Defectors"); i++) {
			orgArray.add(new Defector());
		}
		for (int i = 0; i < counts.get("PartialCooperators"); i++) {
			orgArray.add(new PartialCooperator());
		}
	}

	/**
	 * Loop through all the organisms in the population and update them with
	 * consideration of whether they cooperate or reproduce
	 * 
	 * @param mutationSwitch
	 *            a boolean for enabling mutations
	 * @param random
	 *            used to select a random member of the population
	 */
	public void update(boolean mutationSwitch, Random random) {
		Iterator<Organism> mutatorIterator = orgArray.iterator();
		while (mutatorIterator.hasNext()) {
			Organism curr = mutatorIterator.next();
			curr.update();
			if (curr.cooperates(random)) {
				curr.decrementEnergy();
				// distribute 8 energy to other bacteria
				int energyToShare = 8;
				Iterator<Organism> sharingIterator = orgArray.iterator();
				Organism temp = sharingIterator.next();
				while (energyToShare != 0) {
					if (temp != curr) {
						temp.incrementEnergy();
						energyToShare--;
						temp = sharingIterator.next();
					} else {
						temp = sharingIterator.next();
					}
				}
			}

			if (curr.getEnergy() >= 10) {
				if (mutationSwitch) {
					Organism newChild = curr.reproduceMutation(random);
					int randomMember = random.nextInt(orgArray.size());
					orgArray.set(randomMember, newChild);
				} else {
					Organism newChild = curr.reproduce();
					int randomMember = random.nextInt(orgArray.size());
					orgArray.set(randomMember, newChild);
				}
			}
		}
	}

	/**
	 * Calculate the mean cooperation probability of all organism in the population
	 * 
	 * @return a double
	 */
	public double calculateCooperationMean() {
		Iterator<Organism> meanIterator = orgArray.iterator();
		double cooperationSum = 0;
		while (meanIterator.hasNext()) {
			cooperationSum += meanIterator.next().getCooperationProbability();
		}
		return cooperationSum / orgArray.size();
	}

	/**
	 * Convert counts of each Population in the form Map<String, Integer>, where
	 * String is the name of each type of organism Integer is the count of each type
	 * of organism
	 * 
	 * @return counts a Map<String, Integer>
	 */
	public Map<String, Integer> getPopulationCounts() {
		Map<String, Integer> counts = new HashMap<>();
		Iterator<Organism> popCountIterator = orgArray.iterator();
		int CooperatorCount = 0;
		int DefectorCount = 0;
		int PartialCooperatorCount = 0;
		while (popCountIterator.hasNext()) {
			Organism curr = popCountIterator.next();
			if (curr.getType() == "Cooperator") {
				CooperatorCount++;
			}
			if (curr.getType() == "Defector") {
				DefectorCount++;
			}
			if (curr.getType() == "PartialCooperator") {
				PartialCooperatorCount++;
			}
		}
		counts.put("Cooperators", CooperatorCount);
		counts.put("Defectors", DefectorCount);
		counts.put("PartialCooperators", PartialCooperatorCount);
		return counts;

	}

}
