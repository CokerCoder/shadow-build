import org.newdawn.slick.SlickException;

public abstract class Resources extends Objects {

	private int amount;

	public Resources(float x, float y) throws SlickException {
		super(x, y);
	}

	public void update(World world) {

	}

	public void render() {
		if (amount > 0) {
			super.getImage().drawCentered(super.getPos().x, super.getPos().y);
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
