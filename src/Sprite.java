import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Sprite {
	
	private TiledMap map;
	
	// Every sprite has its image
	private Image sprite;
	
	// Every sprite has its speed
	private float speed;
	
	// Every sprite has its position
	// Current position in the world perspective
	private Vector2f pos;
	
	// Angle from current to destination position
	// I assume every character move based on an angle so I put this here instead of the child class
	private double angle;
	
	public Sprite(float x, float y, TiledMap map, Image image, float speed) throws SlickException {
		pos = new Vector2f(x, y);
		this.map = map;
		this.sprite = image;
		this.speed = speed;
	}

	// Differnt sprites can have different update method so make this abstract
	public abstract void update(int delta, Vector2f dest);

	public void render(Graphics g) {
		sprite.drawCentered(pos.x, pos.y);
	}

	// Check if the current tile is solid or not
	public boolean isSolid(Vector2f pos) {
		int xTile = (int)(pos.x/map.getTileWidth());
		int yTile = (int)(pos.y/map.getTileHeight());
		int tileID = map.getTileId(xTile, yTile, World.LAYER_INDEX);
		
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
	
	public double getAngle() {
		return this.angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
