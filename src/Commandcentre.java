import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Commandcentre extends Buildings {
	
	public static final String imageLocation = "assets/buildings/command_centre.png";
	
	public Commandcentre(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
	}

	@Override
	public void update(World world) {
		// TODO Auto-generated method stub
		
	}

}
