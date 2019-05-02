import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Factory extends Buildings{
	
	public static final String imageLocation = "assets/buildings/factory.png";
	
	public Factory(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		super.setImage(new Image(imageLocation));
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}

}
