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

	public void move(int delta, Vector2f dest) {
		// Set the angle to the destination for this unit
		angle = calcAngle(super.getPos().x, super.getPos().y, dest.x, dest.y);
		
		// Potential position after each update
		Vector2f nextPos = new Vector2f((float)(super.getPos().x + speed * delta * Math.cos(angle)), 
				(float)(super.getPos().y + speed * delta * Math.sin(angle)));
		
		// Check if the next position is solid, if not then update the current position
		if(getPos().distance(dest)>App.MIN_DISTANCE && isSolid(nextPos)==false) {
			setPos(nextPos);
		}
	}

	// Check if the current tile is solid or not
	public boolean isSolid(Vector2f pos) {
		int xTile = (int)(pos.x/getMap().getTileWidth());
		int yTile = (int)(pos.y/getMap().getTileHeight());
		int tileID = getMap().getTileId(xTile, yTile, App.LAYER_INDEX);
		
		// Check the "solid" property, if something erroe, renturn "error"
		String isSolid = getMap().getTileProperty(tileID, "solid", "error");
		
		if(isSolid.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	// Helper method to calculate the angle that between two points
	public static double calcAngle(float x1, float y1, float x2, float y2) {
		return (Math.atan2(y2-y1, x2-x1));
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
