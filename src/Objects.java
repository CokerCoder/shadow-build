import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

/* This class is the top class, containing three sub-classes: Units, Buildings and Resources, each of these contains different sub-classes. */

public abstract class Objects {
	
	// Every object defined should have these attributes, map is here in case we need to use map to calculate things
	private TiledMap map;
	private Image image;
	private Vector2f pos;
	
	public abstract void update(int delta, Vector2f dest);
	
	public abstract void render(Graphics g);
	
	
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
