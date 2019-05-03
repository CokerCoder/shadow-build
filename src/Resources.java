import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Resources extends Objects {
	
	private int amount;
	
	public Resources(float x, float y) throws SlickException {
		super(x, y);
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
