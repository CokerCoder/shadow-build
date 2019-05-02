import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class Unobtainium extends Resources{
	
	public static final String imageLocation = "assets/resources/unobtainium_mine.png";
	
	public static final int initialAmount = 50;
	
	public Unobtainium(float x, float y, TiledMap map) throws SlickException {
		super(x, y, map);
		super.setImage(new Image(imageLocation));
		super.setAmount(initialAmount);
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}

}
