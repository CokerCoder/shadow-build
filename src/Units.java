import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Units extends Objects{

	// Every unit can move based on an angle and a speed
	private double angle;
	private float speed;
	
	public Units(float x, float y, TiledMap map) {
		super.setPos(new Vector2f(x, y));
		super.setMap(map);
	}
	
	public void render(Graphics g) {
		super.getImage().drawCentered(super.getPos().x, super.getPos().y);
	}

	public void move(int delta, Vector2f dest) {
		// Set the angle to the destination for this unit
		angle = Math.atan2(dest.y-super.getPos().y, dest.x-super.getPos().x);
		
		// Potential position after each update
		Vector2f nextPos = new Vector2f((float)(super.getPos().x + speed * delta * Math.cos(angle)), 
				(float)(super.getPos().y + speed * delta * Math.sin(angle)));
		
		// Check if the next position is solid, if not then update the current position
		if(getPos().distance(dest)>App.MIN_DISTANCE && isPositionFree(nextPos)==true) {
			setPos(nextPos);
		}
	}

	// Check if the current tile is solid or not
	// Copied from the model answer, slightly modified
	public boolean isPositionFree(Vector2f pos) {
		int tileId = getMap().getTileId((int)(pos.x/getMap().getTileWidth()), (int)(pos.y/getMap().getTileHeight()), 0);
		return !Boolean.parseBoolean(getMap().getTileProperty(tileId, World.SOLID_PROPERTY, "false"));
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
