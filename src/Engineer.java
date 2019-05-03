import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Engineer extends Units {

	public static final String imageLocation = "assets/units/engineer.png";
	public static final float ENGINEER_SPEED = 0.1f;
	
	public Engineer(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		this.setImage(new Image(imageLocation));
		this.setSpeed(ENGINEER_SPEED);
	}

	@Override
	public void update(World world) {
		
	}

}
