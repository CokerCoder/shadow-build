import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Units {
	
	private TiledMap map;
	
	// Every unit has its image
	protected Image image;
	
	// Every unit has its speed
	protected float speed;
	
	// Every unit has its position
	// Current position in the world perspective
	private Vector2f pos;
	
	// Angle from current to destination position
	private double angle;
	
	public Units(float x, float y, TiledMap map) throws SlickException {
		this.pos = new Vector2f(x, y);
		this.map = map;
	}

	// Differnt units can have different update method so make this abstract
	public abstract void update(int delta, Vector2f dest);
	
	public void render(Graphics g) {
		image.drawCentered(pos.x, pos.y);
	}
	
	public void move(int delta, Vector2f dest) {
		// Set the angle to the destination for this unit
		angle = getAngle(pos.x, pos.y, dest.x, dest.y);
		
		// Potential position after each update
		Vector2f nextPos = new Vector2f((float)(pos.x + speed * delta * Math.cos(angle)), 
				(float)(pos.y + speed * delta * Math.sin(angle)));
		
		// Check if the next position is solid, if not then update the current position
		if(pos.distance(dest)>App.MIN_DISTANCE && isSolid(nextPos)==false) {
			setPos(nextPos);
		}
	}

	// Check if the current tile is solid or not
	public boolean isSolid(Vector2f pos) {
		int xTile = (int)(pos.x/map.getTileWidth());
		int yTile = (int)(pos.y/map.getTileHeight());
		int tileID = map.getTileId(xTile, yTile, App.LAYER_INDEX);
		
		// Check the "solid" property, if something erroe, renturn "error"
		String isSolid = map.getTileProperty(tileID, "solid", "error");
		
		if(isSolid.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	// getters and setters
	public Vector2f getPos() {
		return this.pos;
	}
	
	public void setPos(Vector2f newPos) {
		// Check if the new position is off the map
		if(newPos.x<0||newPos.x>map.getWidth()*map.getTileWidth()
				||newPos.y<0||newPos.y>map.getHeight()*map.getTileHeight()) {
			return;
		}
		else {
			this.pos = newPos;
		}
	}	
	
	// Helper method to calculate the angle that between two points
	public static double getAngle(float x1, float y1, float x2, float y2) {
		return (Math.atan2(y2-y1, x2-x1));
	}
}
