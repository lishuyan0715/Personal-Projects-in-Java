import java.util.Random;

/**
 * A Defector never cooperates with other organisms.
 */
public class Defector extends Organism {
	/**
	 * Constructs a Defector, which inherits the properties of Organism
	 */
	public Defector() {
		super();
	}

	/**
	 * @return the string "Defector" (the Organism's type)
	 */
	public String getType() {
		return "Defector";
	}

	/**
	 * Reduces a Defector's energy and reproduces.
	 * 
	 * @return offspring, a new Defector organism
	 */
	public Organism reproduce() {
		this.energy -= 10;
		Defector offspring = new Defector();
		return offspring;
	}

	/**
	 * Reduces a Defector's energy and reproduces with a chance to mutate.
	 * 
	 * @return offspring, a new Organism with a slight chance of being a Cooperator
	 *         or PartialCooperator
	 */
	public Organism reproduceMutation(Random random) {
		this.energy -= 10;
		int mutChance = random.nextInt(101);
		if (mutChance <= 5) {
			Cooperator offspring = new Cooperator();
			return offspring;
		} else if (mutChance >= 95) {
			PartialCooperator offspring = new PartialCooperator();
			return offspring;
		} else {
			Defector offspring = new Defector();
			return offspring;
		}
	}

	/**
	 * @return 0, the probability that the Defector will cooperate
	 */
	public double getCooperationProbability() {
		return 0;
	}

	/**
	 * @return false, since the Defector will never cooperate.
	 */
	public boolean cooperates(Random random) {
		return false;
	}

}
