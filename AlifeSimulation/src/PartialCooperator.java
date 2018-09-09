import java.util.Random;

/**
 * A PartialCooperator sometimes cooperates with other organisms.
 */
public class PartialCooperator extends Organism {
	/**
	 * Constructs a Cooperator, which inherits the properties of Organism
	 */
	public PartialCooperator() {
		super();
	}

	/**
	 * @return the string "PartialCooperator" (the Organism's type)
	 */
	public String getType() {
		return "PartialCooperator";
	}

	/**
	 * Reduces a PartialCooperator's energy and reproduces.
	 * 
	 * @return offspring, a new PartialCooperator organism
	 */
	public Organism reproduce() {
		this.energy -= 10;
		PartialCooperator offspring = new PartialCooperator();
		return offspring;
	}

	/**
	 * Reduces a PartialCooperator's energy and reproduces with a chance to mutate.
	 * 
	 * @return offspring, a new Organism with a slight chance of being a Cooperator
	 *         or Defector
	 */
	public Organism reproduceMutation(Random random) {
		this.energy -= 10;
		int mutChance = random.nextInt(101);
		if (mutChance <= 5) {
			Defector offspring = new Defector();
			return offspring;
		} else if (mutChance >= 95) {
			Cooperator offspring = new Cooperator();
			return offspring;
		} else {
			PartialCooperator offspring = new PartialCooperator();
			return offspring;
		}
	}

	/**
	 * @return 0.5, the probability that the PartialCooperator will cooperate
	 */
	public double getCooperationProbability() {
		return 0.5;
	}

	/**
	 * @return a random boolean since a PartialCooperator sometimes cooperates.
	 */
	public boolean cooperates(Random random) {
		return random.nextBoolean();
	}

}
