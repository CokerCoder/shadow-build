import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Resources extends Objects {
	
	private int amount;
	private boolean isEngineerNearby = false;
	private int nearbyTime=0;
	
	public static final int CARRY_AMOUNT = 2;
	
	public Resources(float x, float y) throws SlickException {
		super(x, y);
	}
	
	public void update(World world) {
		if(!isEngineerNearby) {
			resetTime();
		} else {
			nearbyTime += world.getDelta();
		}
		System.out.println("Time elapsed: "+nearbyTime);
		System.out.println("Engineer nearby:"+ " "+isEngineerNearby);
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

	public boolean isEngineerNearby() {
		return isEngineerNearby;
	}

	public void setEngineerNearby(boolean isEngineerNearby) {
		this.isEngineerNearby = isEngineerNearby;
	}
	
	public void resetTime() {
		this.nearbyTime = 0;
	}
	
}
