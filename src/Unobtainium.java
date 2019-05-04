import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Unobtainium extends Resources{
	
	public static final String imageLocation = "assets/resources/unobtainium_mine.png";
	
	public static final int initialAmount = 50;
	
	public Unobtainium(float x, float y) throws SlickException {
		super(x, y);
		super.setImage(new Image(imageLocation));
		super.setAmount(initialAmount);
	}

	@Override
	public void update(World world) {
		super.update(world);
	}

}
