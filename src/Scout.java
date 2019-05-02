import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

// Inherited from Unit class (scout is a unit)
public class Scout extends Units {
	
	public static final String imageLocation = "assets/units/scout.png";
	public static final float SCOUT_SPEED = 0.3f;
	
	public Scout(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		super.setImage(new Image(imageLocation));
		super.setSpeed(SCOUT_SPEED);
	}


	@Override
	public void update(World world) {
		// A scout could only move around
		super.move(world.getDelta(), super.getTarget());
	}
}
