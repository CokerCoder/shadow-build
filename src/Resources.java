import org.newdawn.slick.SlickException;

/**
 * A recource mine could be a metal mine or a unobtainium mine
 *
 */
public abstract class Resources extends Objects {

	private int amount;

	/**
	 * @param x
	 * @param y
	 * @throws SlickException
	 * Constructor, call when initialze the world
	 */
	public Resources(float x, float y) throws SlickException {
		super(x, y);
	}

	/* (non-Javadoc)
	 * @see Objects#update(World)
	 */
	public void update(World world) {

	}

	/* (non-Javadoc)
	 * @see Objects#render()
	 */
	public void render() {
		if (amount > 0) {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		}
	}

	/**
	 * @return the current amount of this mine
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 * Set the current amount of this mine, must decrease
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
