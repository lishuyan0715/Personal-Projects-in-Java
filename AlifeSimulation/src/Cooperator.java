import java.util.Random;

/**
 * A Cooperator always cooperates with other organisms.
 */
public class Cooperator extends Organism {
	/**
	 * Constructs a Cooperator, which inherits the properties of Organism
	 */
	public Cooperator() {
		super();
	}

	/**
	 * @return the string "Cooperator" (the Organism's type)
	 */
	public String getType() {
		return "Cooperator";
	}

	/**
	 * Reduces a Cooperator's energy and reproduces.
	 * 
	 * @return offspring, a new Cooperator organism
	 */
	public Organism reproduce() {
		this.energy -= 10;
		Cooperator offspring = new Cooperator();
		return offspring;
	}

	/**
	 * Reduces a Cooperator's energy and reproduces with a chance to mutate.
	 * 
	 * @return offspring, a new Organism, with a slight chance of being a Defector
	 *         or PartialCooperator
	 */
	public Organism reproduceMutation(Random random) {
		this.energy -= 10;
		int mutChance = random.nextInt(101);
		if (mutChance <= 5) {
			Defector offspring = new Defector();
			return offspring;
		} else if (mutChance >= 95) {
			PartialCooperator offspring = new PartialCooperator();
			return offspring;
		} else {
			Cooperator offspring = new Cooperator();
			return offspring;
		}
	}

	/**
	 * @return 1.0, the probability that the Cooperator will cooperate
	 */
	public double getCooperationProbability() {
		return 1.0;
	}

	/**
	 * @return true, since a Cooperator will always cooperate.
	 */
	public boolean cooperates(Random random) {
		return true;
	}

}
