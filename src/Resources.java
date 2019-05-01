import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Resources extends Objects {
	
	private int amount;
	
	public Resources(float x, float y, TiledMap map) {
		super.setPos(new Vector2f(x, y));
		super.setMap(map);
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
