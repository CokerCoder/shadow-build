import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Commandcentre extends Buildings {
	
	public static final String IMAGE_LOCATION = "assets/buildings/command_centre.png";
	
	private int trainingTime = 0;
	private boolean isTraining = false;
	
	public Commandcentre(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(IMAGE_LOCATION));
	}

	@Override
	public void update(World world) {
		

	}
}
