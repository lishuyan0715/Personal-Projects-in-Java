import java.util.Random;

/**
 * An Organism, e.g. a Cooperator, Defector, or PartialCooperator.
 */
public abstract class Organism {
	protected int energy;

	/**
	 * Constructs an Organism, initializing its energy field to zero.
	 */
	public Organism() {
		this.energy = 0;
	}

	/* Methods with default implementation */

	/**
	 * Updates this organism's energy, increasing it by one energy point by default.
	 */
	public void update() {
		this.incrementEnergy();
	}

	/**
	 * @return the current energy of an organism
	 */
	public int getEnergy() {
		return this.energy;
	}

	/**
	 * Increments an organism's energy by one energy point.
	 */
	public void incrementEnergy() {
		this.energy += 1;
	}

	/**
	 * Decrements an organism's energy by one energy point.
	 */
	public void decrementEnergy() {
		this.energy -= 1;
	}

	/* Abstract methods */

	/**
	 * @return the type of this organism as a string
	 */
	public abstract String getType();

	/**
	 * Called by update when the organism can reproduce. Creates an offspring
	 * organism to be returned. Decreases an organism's energy by 10.
	 * 
	 * @return a new offspring organism
	 */
	public abstract Organism reproduce();

	/**
	 * Called by update when the organism can reproduce. Creates an offspring
	 * organism to be returned. Decreases an organism's energy by 10. Has a <5%
	 * chance to return a type of organism other than the parent
	 * 
	 * @return a new offspring organism
	 */
	public abstract Organism reproduceMutation(Random random);

	/**
	 * @return the cooperation probability of this organism
	 */
	public abstract double getCooperationProbability();

	/**
	 * @return whether the organism cooperates.
	 */
	public abstract boolean cooperates(Random random);

}
