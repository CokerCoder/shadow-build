import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Truck extends Units{

	public static final String imageLocation = "assets/units/truck.png";
	public static final float TRUCK_SPEED = 0.25f;
	
	public Truck(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		this.setImage(new Image(imageLocation));
		this.setSpeed(TRUCK_SPEED);
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}

}
