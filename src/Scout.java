import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

// Inherited from Unit class (scout is a unit)
public class Scout extends Units {
	
	public static final String imageLocation = "assets/units/scout.png";
	public static final float SCOUT_SPEED = 0.3f;
	
	public Scout(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		this.image = new Image(imageLocation);
		this.speed = SCOUT_SPEED;
	}

	@Override
	public void update(int delta, Vector2f dest) {
		// A scout could only move around
		super.move(delta,  dest);
	}
}
