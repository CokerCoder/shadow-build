import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Resources extends Objects {
	
	private int amount;
	
	public static final int CARRY_AMOUNT = 2;
	
	public Resources(float x, float y) throws SlickException {
		super(x, y);
	}
	
	public void update(World world) {

	}
	
	
	
	public void render(Graphics g) {
		if(amount>0) {
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
