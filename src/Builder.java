import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Builder extends Units {

	public static final String imageLocation = "assets/units/builder.png";
	public static final float BUILDER_SPEED = 0.1f;
	
	public Builder(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		this.image = new Image(imageLocation);
		this.speed = BUILDER_SPEED;
	}

	@Override
	public void update(int delta, Vector2f dest) {
		// TODO Auto-generated method stub
		
	}

}
