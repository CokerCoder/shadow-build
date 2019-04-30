import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public abstract class Objects {
	
	private TiledMap map;
	private Image image;
	private Vector2f pos;
	
	public abstract void update(int delta, Vector2f dest);
	
	// Every objects in the map has the same render method, that is to display on the screen
	public void render(Graphics g) {
		image.drawCentered(pos.x, pos.y);
	}
	
	
	// Getters and setters
	public Vector2f getPos() {
		return pos;
	}
	
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}
}
